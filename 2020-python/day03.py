from typing import List, Set
import pytest


def calc_trees_hit(right: int, down: int, terrain: List[Set[int]], line_len: int) -> int:
  x = 0
  hits = 0
  for i, row in enumerate(terrain):
    if i % down == 0:
      if x in row:
        hits = hits + 1
      next_x = x + right
      x = next_x if next_x < line_len else next_x % line_len
  return hits


def line_to_set(line: str) -> Set[int]:
  tree_indicies = set()
  for i, v in enumerate(line):
    if v == '#':
      tree_indicies.add(i)
  return tree_indicies


@pytest.mark.parametrize("line, expected", [
  ("..##.......", {2, 3}),
  (".#..#...#.#", {1, 4, 8, 10})
])
def test_line_to_set(line: str, expected: Set[int]):
  assert line_to_set(line) == expected


@pytest.mark.parametrize("right, down, expected", [
  (1, 1, 2),
  (3, 1, 7),
  (5, 1, 3),
  (7, 1, 4),
  (1, 2, 2),
])
def test_slope_calc(right: int, down: int, expected: int):
  test_terrain_input = [
    "..##.......",
    "#...#...#..",
    ".#....#..#.",
    "..#.#...#.#",
    ".#...##..#.",
    "..#.##.....",
    ".#.#.#....#",
    ".#........#",
    "#.##...#...",
    "#...##....#",
    ".#..#...#.#",
  ]
  terrain = [line_to_set(line) for line in test_terrain_input]
  assert calc_trees_hit(right, down, terrain=terrain, line_len=11) == expected


if __name__ == '__main__':
  with open("day03.txt") as f:
    lines = f.readlines()
    terrain = [line_to_set(line.strip()) for line in lines]
    line_len = len(lines[0].strip())
    for right, down in [(1, 1), (3, 1), (5, 1), (7, 1), (1, 2)]:
      trees_hit = calc_trees_hit(right, down, terrain, line_len)
      print(f"Right: {right}  Down: {down}  Trees: {trees_hit}")

