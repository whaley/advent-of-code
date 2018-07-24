package com.morninghacks.aoc2017

import java.lang.invoke.MethodHandles

private data class NodeFromLine(val id: String, val weight: Int, val childIds: List<String>)

private data class Node(val id: String, val weight: Int, var children: List<Node> = listOf(), val parent: Node? = null) {
    fun treeWeight() : Int = weight + children.sumBy(Node::treeWeight)
    override fun toString(): String {
        return "Node(id='$id', weight=$weight, children=$children)"
    }

}


fun solve(source: String): Pair<String,Pair<String,Int>> {
    //need two maps - id -> Node for all nodes
    val allNodes: List<NodeFromLine> = source.lines()
            .filter(String::isNotBlank)
            .map(::nodeFromLine)
    val nodesById: Map<String,NodeFromLine> = allNodes.associateBy(NodeFromLine::id)
    val parentsAndChildren: Map<String,List<NodeFromLine?>> = createParentChildRelationships(nodesById)
    val allChildren = parentsAndChildren.values.flatten().filterNotNull()
    val rootId: String = parentsAndChildren.keys.minus(allChildren.map(NodeFromLine::id)).first()

    //createTree
    val rootNodeFrom = nodesById.getValue(rootId)
    val rootNode = createTree(rootNodeFrom, nodesById)

    val unbalanced = findUnbalanceNode(rootNode)
    val unbalancedParent = unbalanced.parent
    val otherWeight = unbalancedParent!!.children.minus(unbalanced).first().treeWeight()
    val difference = otherWeight - unbalanced.treeWeight()

    return Pair(rootNode.id,Pair(unbalanced.id, Math.abs(unbalanced.weight + difference)))
}

private tailrec fun findUnbalanceNode(node: Node): Node {
    val nodesByWeight = node.children.groupBy(Node::treeWeight)
    if (nodesByWeight.size == 1) {
        return node
    } else {
        for (childNodes: List<Node> in nodesByWeight.values) {
            if (childNodes.size == 1) {
                return findUnbalanceNode(childNodes[0])
            }
        }
    }
    throw IllegalStateException()
}

private fun createTree(rootNfl: NodeFromLine, nodesById: Map<String,NodeFromLine>) : Node {
    fun createSubTree(nfl: NodeFromLine, parent: Node): Node {
        val node = Node(nfl.id, nfl.weight, parent = parent)
        node.children = nfl.childIds.map { childId -> createSubTree(nodesById.getValue(childId), node) }
        return node
    }
    val rootNode = Node(rootNfl.id, rootNfl.weight, parent = null)
    rootNode.children = rootNfl.childIds.map { childId -> createSubTree(nodesById.getValue(childId),rootNode) }
    return rootNode
}

private fun createParentChildRelationships(nodesById: Map<String,NodeFromLine>) : Map<String,List<NodeFromLine?>> {
    return nodesById.filter { (id,node) -> node.childIds.isNotEmpty() }
            .mapValues { (id,node)  -> node.childIds.map(nodesById::get) }
}


private fun nodeFromLine(line: String) : NodeFromLine {
    //Sample Line:  ugml (68) -> gyxo, ebii, jptl
    val id = line.subSequence(0,line.indexOf('(')).trim().toString()
    val weight = line.subSequence(line.indexOf('(') + 1, line.indexOf(')')).toString().toInt()
    val childIds : List<String> = if (line.contains('>')) {
        val childIdSection = line.subSequence(line.indexOf('>') + 1,line.length)
        childIdSection.split(",").map { s -> s.trim() }.toList()
    } else {
        listOf()
    }
    return NodeFromLine(id,weight,childIds)
}

fun main(args: Array<String>) {
    val input = MethodHandles.lookup().lookupClass().getResourceAsStream("/Day07Input.txt").bufferedReader().readText()
    println(solve(input))

}

