import re

import pytest
from typing import Dict, List


def parse(filename: str) -> List[Dict[str, str]]:
  records = []
  current: Dict[str, str] = {}
  with open(filename) as f:
    line = f.readline()
    while line:
      line = line.strip()
      if line:  # add found fields to the current record being built
        for line in line.split():
          k, v = line.split(":")
          current[k] = v
      if not line:  # blank found, which signals the end of the current record
        if current:
          records.append(current)
          current = {}
      line = f.readline()
    records.append(current)  # add the last record
    return records


def calc_valid(records: List[Dict[str, str]]) -> int:
  all_fields = {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid", "cid"}
  valid_fields = all_fields - {"cid"}
  count = 0
  for record in records:
    if valid_fields.issubset(record):
      count = count + 1
  return count


def calc_valid_pt2(records: List[Dict[str, str]]) -> int:
  count = 0
  for record in records:
    if is_valid_record(record):
      count = count + 1
  return count


def is_valid_record(record: Dict[str, str]) -> bool:
  return is_valid_birth(record.get("byr")) and is_valid_issue(
    record.get("iyr")) and is_valid_expiration(record.get("eyr")) and is_valid_height(
    record.get("hgt")) and is_valid_haircolor(record.get("hcl")) and is_valid_eye_color(
    record.get("ecl")) and is_valid_passport_id(record.get("pid"))


def is_valid_birth(byr: str) -> bool:
  try:
    return 1920 <= int(byr) <= 2002 and len(byr) == 4
  except:
    return False


def is_valid_issue(iyr: str) -> bool:
  try:
    return 2010 <= int(iyr) <= 2020 and len(iyr) == 4
  except:
    return False


def is_valid_expiration(eyr: str) -> bool:
  try:
    return 2020 <= int(eyr) <= 2030 and len(eyr) == 4
  except:
    return False


def is_valid_height(hgt: str) -> bool:
  if not hgt:
    return False
  try:
    if hgt.endswith("cm"):
      cm = int(hgt[:-2])
      return 150 <= cm <= 193
    elif hgt.endswith("in"):
      inches = int(hgt[:-2])
      return 59 <= inches <= 76
    else:
      return False
  except ValueError:
    return False


_haircolor_re = re.compile(r'^#[0-9a-f]{6}$')


def is_valid_haircolor(hcl: str) -> bool:
  return _haircolor_re.match(hcl.strip() if hcl else "") is not None


_valid_eye_colors = {"amb", "blu", "brn", "gry", "grn", "hzl", "oth"}


def is_valid_eye_color(ecl: str) -> bool:
  return ecl.strip() in _valid_eye_colors if ecl else False


_passport_re = re.compile(r'^[0-9]{9}$')


def is_valid_passport_id(pid: str) -> bool:
  return _passport_re.match(pid.strip() if pid else "") is not None


def test_valid_records_pt1():
  passports = parse("day04_test.txt")
  assert calc_valid(passports) == 2


@pytest.mark.parametrize("record, expected", [
  ({
    "eyr": "1972", "cid": "100", "hcl": "#18171d", "ecl": "amb", "hgt": "170", "pid": "186cm",
    "iyr": "2018", "byr": "1926"
  }, False),
  ({
    "iyr": "2019",
    "hcl": "#602927", "eyr": "1967", "hgt": "170cm",
    "ecl": "grn", "pid": "012533040", "byr": "1946"
  }, False),
  ({
    "hcl": "dab227", "iyr": "2012",
    "ecl": "brn", "hgt": "182cm", "pid": "021572410", "eyr": "2020", "byr": "1992", "cid": "277"
  }, False),

  ({
    "pid": "087499704", "hgt": "74in", "ecl": "grn", "iyr": "2012", "eyr": "2030", "byr": "1980",
    "hcl": "#623a2f"
  }, True)

])
def test_records_pt2(record: Dict[str, str], expected: bool):
  assert is_valid_record(record) == expected


if __name__ == '__main__':
  records = parse("day04.txt")
  print(calc_valid(records))
  print(calc_valid_pt2(records))
