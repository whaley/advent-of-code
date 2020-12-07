from dataclasses import dataclass

import pytest


@dataclass
class Mapping:
  zero: str
  one: str


fb_mapping = Mapping(zero="F", one="B")
rl_mapping = Mapping(zero="L", one="R")


def code_to_number(code: str, m: Mapping) -> int:
  return int(code.replace(m.zero, "0").replace(m.one, "1"), 2)


def calc_seat_id(row: int, column: int) -> int:
  return row * 8 + column


def code_to_seat_id(code: str) -> int:
  row_code = code[:7]
  column_code = code[7:]
  row = code_to_number(row_code, fb_mapping)
  column = code_to_number(column_code, rl_mapping)
  return calc_seat_id(row, column)


@pytest.mark.parametrize("code, mapping, expected", [
  ("FBFBBFF", fb_mapping, 44),
  ("RLR", rl_mapping, 5)
])
def test_code_to_number(code: str, mapping: Mapping, expected: int):
  assert code_to_number(code, mapping) == expected

def test_calc_seat_id():
  assert calc_seat_id(44, 5) == 357

@pytest.mark.parametrize("code, expected", [
  ("BFFFBBFRRR", 567),
  ("FFFBBBFRRR", 119),
  ("BBFFBBFRLL", 820),
])
def test_code_to_seat_id(code, expected):
  assert code_to_seat_id(code) == expected


if __name__ == '__main__':
  with open("day05.txt") as f:
    codes = [code_to_seat_id(code.strip()) for code in f.readlines()]
    print(f"Part 1: {max(codes)}")
    sorted_codes = sorted(codes)
    for i, code in enumerate(sorted_codes[:-1]):
      if sorted_codes[i+1] - sorted_codes[i] == 2:
        print(f"Part 2: {sorted_codes[i] + 1}")
