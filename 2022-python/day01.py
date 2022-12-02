from typing import Optional
import pytest


def solve_day01():
    lines = read_input()
    mapped_lines = [int(s) if s.strip() else None for s in lines]
    print(f"Part 1: {solve_pt1(mapped_lines)}")
    print(f"Part 2: {solve_pt2(mapped_lines)}")


def foods_by_elf(input_calories: list[Optional[int]]) -> list[list[int]]:
    result: list[list[int]] = [[]]
    i = 0
    for food in input_calories:
        if food:
            result[i].append(food)
        else:
            result.append([])
            i += 1
    return result


def solve_pt1(input_calories: list[int]) -> int:
    return max([sum(foods) for foods in foods_by_elf(input_calories)])


def solve_pt2(input_calories: list[int]) -> int:
    sorted_by_sum = sorted([sum(foods) for foods in foods_by_elf(input_calories)])
    return sum(sorted_by_sum[-3:])


def read_input() -> list[str]:
    with open("day01.txt") as f:
        return f.readlines()


if __name__ == "__main__":
    solve_day01()


@pytest.fixture
def sample_input() -> list[Optional[int]]:
    return [
        1000,
        2000,
        3000,
        None,
        4000,
        None,
        5000,
        6000,
        None,
        7000,
        8000,
        9000,
        None,
        10000
    ]


def test_solve_pt1(sample_input: list[Optional[int]]):
    assert solve_pt1(sample_input) == 24_000


def test_solve_pt2(sample_input: list[Optional[int]]):
    assert solve_pt2(sample_input) == 45_000
