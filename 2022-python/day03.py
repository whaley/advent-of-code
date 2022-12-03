import pytest

from functools import cache


def solve_day03():
    lines = read_input()
    print(f"Part 1: {solve_pt1(lines)}")
    # print(f"Part 2: {solve_pt2(lines)}")


def read_input() -> list[str]:
    with open("day03.txt") as f:
        return f.readlines()


@cache
def priority(char: str) -> int:
    code_point = ord(char)
    if ord('a') <= code_point <= ord('z'):
        return code_point - ord('a') + 1
    else:
        return code_point - ord('A') + 1 + 26


def solve_pt1(rucksacks: list[str]):
    summed_priority = 0
    for sack in rucksacks:
        midpoint = len(sack) // 2
        first_half, second_half = sack[:midpoint], sack[midpoint:]

        chars_in_first_half = {c for c in first_half}
        chars_in_second_half = {c for c in second_half}
        char_in_both = (chars_in_first_half & chars_in_second_half).pop()

        summed_priority += priority(char_in_both)
    return summed_priority


if __name__ == "__main__":
    solve_day03()


@pytest.mark.parametrize("input_char, expected", [
    ('a', 1),
    ('b', 2),
    ('z', 26),
    ('A', 27),
    ('Z', 52),
])
def test_priority(input_char: str, expected: int):
    assert priority(input_char) == expected


@pytest.fixture
def sample_input() -> list[str]:
    return [
        "vJrwpWtwJgWrhcsFMMfFFhFp",
        "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
        "PmmdzqPrVvPwwTWBwg",
        "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn",
        "ttgJtRGJQctTZtZT",
        "CrZsJsPPZsGzwwsLwLmpwMDw",
    ]


def test_solve_pt1(sample_input: list[str]):
    assert solve_pt1(sample_input) == 157
