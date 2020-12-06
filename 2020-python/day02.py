from dataclasses import dataclass
from typing import List, Tuple

import pytest
import re


@dataclass
class Policy:
  min: int
  max: int
  char: str


def read_input() -> List[Tuple[Policy, str]]:
  regex = re.compile(r"(\d*)-(\d*)\s(.):\s(.*)")

  def parse_line(line: str) -> Tuple[Policy, str]:
    match = regex.match(line)
    return Policy(min=int(match.group(1)), max=int(match.group(2)),
      char=match.group(3)), match.group(4)

  with open("day_02.txt") as f:
    lines = f.readlines()
    return [parse_line(line) for line in lines if line]


def is_password_valid(policy: Policy, password: str):
  return policy.min <= password.count(policy.char) <= policy.max

def is_password_valid_2(policy: Policy, password: str):
  return (password[policy.min - 1] == policy.char) ^ (password[policy.max - 1] == policy.char)

@pytest.mark.parametrize("policy, password, expected", [
  (Policy(min=1, max=3, char="a"), "abcde", True),
  (Policy(min=1, max=3, char="b"), "cdefg", False),
  (Policy(min=2, max=9, char="c"), "ccccccccc", True)
])
def test_is_password_valid(policy: Policy, password: str, expected: bool):
  assert is_password_valid(policy, password) == expected

@pytest.mark.parametrize("policy, password, expected", [
  (Policy(min=1, max=3, char="a"), "abcde", True),
  (Policy(min=1, max=3, char="b"), "cdefg", False),
  (Policy(min=2, max=9, char="c"), "ccccccccc", False)
])
def test_is_password_valid_2(policy: Policy, password: str, expected: bool):
  assert is_password_valid_2(policy, password) == expected


if __name__ == '__main__':
  input = read_input()
  print(len([pair for pair in input if is_password_valid(pair[0], pair[1])]))
  print(len([pair for pair in input if is_password_valid_2(pair[0], pair[1])]))
