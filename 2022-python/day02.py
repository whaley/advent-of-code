import enum

import pytest


def solve_day02():
    lines = read_input()
    mapped_lines: list[tuple[str, str]] = []
    for line in lines:
        a, b = line.split()
        mapped_lines.append((a, b))
    print(f"Part 1: {solve_pt1(mapped_lines)}")
    print(f"Part 2: {solve_pt2(mapped_lines)}")


class Result(enum.Enum):
    LOSS = 0
    DRAW = 3
    WIN = 6


class RockPaperScissors(enum.Enum):
    ROCK: int = 1
    PAPER: int = 2
    SCISSORS: int = 3

    def result_vs(self, other) -> Result:
        match self.value - other.value:
            case 0:
                return Result.DRAW
            case 1 | -2:
                return Result.WIN
            case -1 | 2:
                return Result.LOSS


def solve_pt1(guide: list[tuple[str, str]]) -> int:
    mapping = {
        "A": RockPaperScissors.ROCK,
        "X": RockPaperScissors.ROCK,
        "B": RockPaperScissors.PAPER,
        "Y": RockPaperScissors.PAPER,
        "C": RockPaperScissors.SCISSORS,
        "Z": RockPaperScissors.SCISSORS,
    }
    score = 0
    for match in guide:
        them, us = (mapping[x] for x in match)
        score += us.result_vs(them).value + us.value
    return score


def my_selection(theirs: RockPaperScissors, result: Result) -> RockPaperScissors:
    match result:
        case result.DRAW:
            return theirs
        case result.WIN:
            value = theirs.value + 1 if theirs.value + 1 <= 3 else 1
            return RockPaperScissors(value)
        case result.LOSS:
            value = theirs.value - 1 if theirs.value - 1 >= 1 else 3
            return RockPaperScissors(value)


def solve_pt2(guide: list[tuple[str, str]]) -> int:
    match_mapping = {
        "A": RockPaperScissors.ROCK,
        "B": RockPaperScissors.PAPER,
        "C": RockPaperScissors.SCISSORS,

        "X": Result.LOSS,
        "Y": Result.DRAW,
        "Z": Result.WIN,
    }

    score = 0
    for match in guide:
        them, result = (match_mapping[x] for x in match)
        score += my_selection(them, result).value + result.value
    return score


def read_input() -> list[str]:
    with open("day02.txt") as f:
        return f.readlines()


if __name__ == "__main__":
    solve_day02()


@pytest.fixture
def sample_input() -> list[tuple[str, str]]:
    return [
        ("A", "Y"),
        ("B", "X"),
        ("C", "Z"),
    ]


def test_solve_pt1(sample_input: list[tuple[str, str]]):
    assert solve_pt1(sample_input) == 15


def test_solve_pt2(sample_input: list[tuple[str, str]]):
    assert solve_pt2(sample_input) == 12
