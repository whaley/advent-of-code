from typing import List, Set
from itertools import combinations


def read_input() -> List[int]:
  with open("day_01.txt") as f:
    lines = f.readlines()
    return [int(line) for line in lines if line]

def find_pair_original(numbers: List[int], expected: int) -> Set[int]:
  # Original solution in O(n).  Ditched this since it didn't help with part 2
  numbers = sorted(numbers)
  x = 0
  y = -1
  while not (actual := numbers[x] + numbers[y]) == expected:
    if actual < expected:
      x = x + 1
    if actual > expected:
      y = y - 1
  return {numbers[x], numbers[y]}

def find_combination(numbers: List[int], expected: int, n: int = 2):
  for combo in combinations(numbers, n):
    if sum(combo) == expected:
      return set(combo)

def test_find_combination_2():
  assert find_combination([1721, 979, 366, 299, 675, 1456], 2020, 2) == {299, 1721}

def test_find_combination_3():
  assert find_combination([1721, 979, 366, 299, 675, 1456], 2020, 3) == {979, 366, 675}

if __name__ == '__main__':
  input_numbers = read_input()
  x, y = find_combination(input_numbers, 2020, 2)
  print(x * y)

  x, y, z = find_combination(input_numbers, 2020, 3)
  print(x * y * z)



