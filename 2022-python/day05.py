from collections import defaultdict

import pytest

from dataclasses import dataclass
from typing import Any


@dataclass
class Move:
    count: int
    take_from: int
    add_to: int


def solve_day05():
    lines = read_input()
    stacks, moves = map_input(lines)
    print(f"Part 1: {solve_pt1(stacks, moves)}")
    # print(f"Part 2: {solve_pt2(lines)}")


def read_input() -> list[str]:
    with open("day05.txt") as f:
        return [line.strip() for line in f.readlines()]


def map_input(lines: list[str]) -> tuple[list[list[str]], list[Move]]:
    stacks_end_idx = -1
    for i, line in enumerate(lines):
        if not line.strip().startswith("["):
            stacks_end_idx = i
            break
    stacks = generate_stacks(lines[:stacks_end_idx])

    moves_begin_idx = -1
    for i, line in enumerate(lines[stacks_end_idx:]):
        if line.strip().startswith("move"):
            moves_begin_idx = stacks_end_idx + i
            break
    moves = generate_moves(lines[moves_begin_idx:])

    return stacks, moves


def generate_stacks(lines: list[str]) -> list[list[str]]:
    # bucket the stack into a defaultdict, number of stacks isn't known ahead of time given the homogenous input
    stack_dict = defaultdict(list)

    for line in lines:
        # chunk each line into strings of length 4, the chunk index is the stack
        for i in range(0, len(line), 4):
            container = line[i:i+4].strip()
            if container:
                stack_number = (i + 4) // 4
                # these are added in reverse order, the top container per stack is actually at 0th index
                stack_dict[stack_number].append(container.lstrip("[").rstrip("]"))

    number_of_stacks = max(stack_dict.keys())
    stacks = [[]] * (number_of_stacks + 1)
    for idx, stack in stack_dict.items():
        stacks[idx] = stack[::-1]  # reverse the stack so the top container is at the last last index
    return stacks


def generate_moves(lines: list[str]) -> list[Move]:
    def line_to_move(line: str) -> Move:
        tokens = line.split()
        return Move(count=int(tokens[1]), take_from=int(tokens[3]), add_to=int(tokens[5]))

    return [line_to_move(line) for line in lines]


def solve_pt1(stacks: list[list[str]], moves: list[Move]) -> str:
    for move in moves:
        on_the_crane: list[str] = []
        for _ in range(move.count):
            on_the_crane.append(stacks[move.take_from].pop())
        stacks[move.add_to].extend(on_the_crane)
    return "".join(stack[-1] for stack in stacks[1:])


def solve_pt2(stacks: list[list[str]], moves: list[Move]) -> str:
    return ""


if __name__ == "__main__":
    solve_day05()


@pytest.fixture
def sample_starting_stacks() -> list[list[str]]:
    return [
        [],
        ["Z", "N"],
        ["M", "C", "D"],
        ["P"]
    ]


@pytest.fixture
def sample_moves() -> list[Move]:
    return [
        Move(count=1, take_from=2, add_to=1),
        Move(count=3, take_from=1, add_to=3),
        Move(count=2, take_from=2, add_to=1),
        Move(count=1, take_from=1, add_to=2)
    ]


def test_solve_pt1(sample_starting_stacks: list[list[str]], sample_moves: list[Move]):
    assert solve_pt1(sample_starting_stacks, sample_moves) == "CMZ"


def test_solve_pt2(sample_starting_stacks: list[list[str]], sample_moves: list[Move]):
    assert solve_pt2(sample_starting_stacks, sample_moves) == 4


def test_map_input(sample_starting_stacks: list[list[str]], sample_moves: list[Move]):
    input_list = """\
    [D]
[N] [C]
[Z] [M] [P]
 1   2   3
move 1 from 2 to 1
move 3 from 1 to 3
move 2 from 2 to 1
move 1 from 1 to 2\
""".split("\n")

    assert map_input(input_list) == (sample_starting_stacks, sample_moves)
