import unittest
from collections import defaultdict

def inputToList():
    lines = []
    with open('day02_input.txt') as f:
        for line in f:
            lines.append(line.strip())
    return lines


def hash(occurencesList, allowedOccurences):
    counts = defaultdict(int)
    for occurence in occurencesList:
        for key in occurence:
            if key in allowedOccurences:
                counts[key] += 1

    multiply = lambda x,y: x *y
    return reduce(multiply, counts.values(), 1)


def repeatCharsInString(inputString):
    foundTwo = False
    foundThree = False

    chars = defaultdict(int)
    for char in inputString:
        chars[char] += 1

    found = defaultdict(bool)
    for k, v in chars.items():
        found[v] = True

    return found


class TestDay02(unittest.TestCase):
    def testRepeatCharsInString2and3(self):
        self.assertEqual(repeatCharsInString("aabbb"), {2: True, 3: True})

    def testRepeatCharsInString3only(self):
        self.assertEqual(repeatCharsInString("aaabbb"), {3: True})

    def testHash(self):
        calcedHash = hash([{2:True, 3: True}, {2: True}, {3:True, 4:True}, {2: True}], [2,3])
        self.assertEqual(calcedHash, 6)

if __name__ == '__main__':
    occurences = map(repeatCharsInString, inputToList())
    print(hash(occurences, [2,3]))
