import pytest

from functools import cache

from typing import Any


def solve_day04():
    lines = read_input()
    pairs: list[tuple[str, str]] = []
    for line in lines:
        first, second = line.split(",", 1)
        pairs.append((first, second))
    print(f"Part 1: {solve_pt1(pairs)}")
    print(f"Part 2: {solve_pt2(pairs)}")


def read_input() -> list[str]:
    with open("day04.txt") as f:
        return [line.strip() for line in f.readlines()]


@cache
def priority(char: str) -> int:
    code_point = ord(char)
    if ord('a') <= code_point <= ord('z'):
        return code_point - ord('a') + 1
    else:
        return code_point - ord('A') + 1 + 26


def rangeset(s: str) -> set[int]:
    """
    Convert a string of the from N-M into a set that contains all numbers from N through M, inclusive in a set.
    Example: rangeset(2-5) returns {2,3,4,5}
    """
    first, rest = s.split("-", 1)
    start, finish = int(first), int(rest)
    return set(range(start, finish + 1))


def sets_have_containment(x: set[Any], y: set[Any]) -> bool:
    if not (x and y):
        return False

    smaller, larger = (x, y) if len(x) <= len(y) else (y, x)
    return smaller.issubset(larger)


def solve_pt1(pairs: list[tuple[str, str]]) -> int:
    num_of_inclusive_ranges = 0
    for pair in pairs:
        range_a, range_b = rangeset(pair[0]), rangeset(pair[1])
        if sets_have_containment(range_a, range_b):
            num_of_inclusive_ranges += 1
    return num_of_inclusive_ranges


def solve_pt2(pairs: list[tuple[str, str]]) -> int:
    num_of_overlapping_pairs = 0
    for pair in pairs:
        range_a, range_b = rangeset(pair[0]), rangeset(pair[1])
        if range_a & range_b:
            num_of_overlapping_pairs += 1
    return num_of_overlapping_pairs


if __name__ == "__main__":
    solve_day04()


@pytest.fixture
def sample_input() -> list[tuple[str, str]]:
    return [
        ("2-4", "6-8"),
        ("2-3", "4-5"),
        ("2-3", "4-5"),
        ("5-7", "7-9"),
        ("2-8", "3-7"),
        ("6-6", "4-6"),
        ("2-6", "4-8"),
    ]


@pytest.mark.parametrize("s, expected", [
    ('1-1', {1}),
    ('1-4', {1, 2, 3, 4}),
    ('6-8', {6, 7, 8}),
])
def test_rangeset(s: str, expected: set[int]):
    assert rangeset(s) == expected


@pytest.mark.parametrize("x, y, expected", [
    ({1}, {1}, True),
    ({1}, {1, 2}, True),
    ({1, 2}, {1}, True),
    ({1, 2, 3}, {4, 5, 6}, False),
    ({1, 2}, {2, 3}, False),
    ({2, 3}, {1, 2, 3, 4}, True),
    ({1, 2, 3, 4}, {2, 3}, True),
])
def test_sets_have_containment(x: set[int], y: set[int], expected: bool):
    assert sets_have_containment(x, y) == expected


def test_solve_pt1(sample_input: list[tuple[str, str]]):
    assert solve_pt1(sample_input) == 2


def test_solve_pt2(sample_input: list[tuple[str, str]]):
    assert solve_pt2(sample_input) == 4
