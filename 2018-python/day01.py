import unittest

def freqChangeToInt(change):
    sign = change[0]
    num = int(change[1:])
    return num if sign is not '-' else num * -1


def calcFrequencies(current = 0, changes = []):
    for change in changes:
        current = current + freqChangeToInt(change)
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

    def test_A(self):
        changes = ['+1', '+1', '+1']
        self.assertEqual(calcFrequencies(0, changes), 3)

    def test_B(self):
        changes = ['+1', '+1', '-2']
        self.assertEqual(calcFrequencies(0, changes), 0)

    def test_C(self):
        changes = ['-1', '-2', '-33']
        self.assertEqual(calcFrequencies(0, changes), -36)


if __name__ == '__main__':
    #unittest.main()
    print(calcFrequencies(0, inputToList()))
