import unittest

def calcFrequencies(current = 0, changes = []):
    for change in changes:
        current = current + int(change)
    return current

def firstRepeated(current = 0, changes = []):
    seenFreqs = set()
    idx = 0
    while current not in seenFreqs:
        seenFreqs.add(current)
        current = current + int(changes[idx])
        idx = idx + 1
        idx = idx if idx < len(changes) else 0
    return current

def inputToList():
    freqs = []
    with open('day01_input.txt') as f:
        for line in f:
            freqs.append(line.strip())
    return freqs


class TestDay01(unittest.TestCase):
    def test_0s(self):
        self.assertEqual(calcFrequencies(0, []), 0)

    def test_Part1_A(self):
        changes = ['+1', '+1', '+1']
        self.assertEqual(calcFrequencies(0, changes), 3)

    def test_Part1_B(self):
        changes = ['+1', '+1', '-2']
        self.assertEqual(calcFrequencies(0, changes), 0)

    def test_Part1_C(self):
        changes = ['-1', '-2', '-33']
        self.assertEqual(calcFrequencies(0, changes), -36)

    def test_Part2_A(self):
        changes = ['+1', '-1']
        self.assertEqual(firstRepeated(0, changes), 0)

    def test_Part2_B(self):
        changes = ['+3', '+3', '+4', '-2', '-4']
        self.assertEqual(firstRepeated(0, changes), 10)

    def test_Part2_C(self):
        changes = ['-6', '+3', '+8', '+5', '-6']
        self.assertEqual(firstRepeated(0, changes), 5)

    def test_Part2_C(self):
        changes = ['+7', '+7', '-2', '-7', '-4']
        self.assertEqual(firstRepeated(0, changes), 14)

if __name__ == '__main__':
    # unittest.main()
    print(calcFrequencies(0, inputToList()))
    print(firstRepeated(0, inputToList()))
