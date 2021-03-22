from typing import List, Set
import string

def parse_pt1(filename: str) -> List[Set[str]]:
    groups = []
    group_answers = set()
    with open(filename) as f:
        line = f.readline()
        while line:
            line = line.strip()
            if line:
                group_answers.update(list(line))
            else:
                groups.append(group_answers)
                group_answers = set()    
            line = f.readline()
        if group_answers:
            groups.append(group_answers) 
    return groups

def parse_pt2(filename: str) -> List[Set[str]]:
    groups = []
    group_answers =  set(string.ascii_lowercase)
    with open(filename) as f:
        line = f.readline()
        while line:
            line = line.strip()
            if line:
                group_answers = group_answers.intersection(set(list(line)))
            else:
                groups.append(group_answers)
                group_answers = set(string.ascii_lowercase)
            line = f.readline()
        if group_answers !=  set(string.ascii_lowercase):
            groups.append(group_answers) 
    return groups


def calc(input: List[Set[str]]) -> int:
    return sum([len(x) for x in input])


def test_parse_pt1():
    assert parse_pt1("day06_test.txt") == [{"a","b","c"},{"a","b","c"},{"a","b","c"}, {"a"}, {"b"}]

def test_calc():
    assert 11 == calc([{"a","b","c"},{"a","b","c"},{"a","b","c"}, {"a"}, {"b"}])

def test_parse_pt2():
    assert parse_pt2("day06_test.txt") == [{"a","b","c"}, set(), {"a"}, {"a"}, {"b"}]



if __name__ == "__main__":
    records = parse_pt1("day06.txt")
    print(calc(records))

    records_2 = parse_pt2("day06.txt")
    print(calc(records_2))
