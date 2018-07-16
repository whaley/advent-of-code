package main

import (
	"fmt"
)

/*Captcha function for Day01:

The captcha requires you to review a sequence of digits (your puzzle input) and find the sum of all digits that match the next digit in the list. The list is circular, so the digit after the last digit is the first digit in the list.

For example:

    1122 produces a sum of 3 (1 + 2) because the first digit (1) matches the second digit and the third digit (2) matches the fourth digit.
    1111 produces 4 because each digit (all 1) matches the next.
    1234 produces 0 because no digit matches the next.
    91212129 produces 9 because the only digit that matches the next one is the last digit, 9.
*/
func Captcha(input string) int {
	//Map input string to an []int
	ints := make([]int, len(input))
	for pos, r := range input {
		if '0' <= r && r <= '9' {
			ints[pos] = int(r - '0')
		} else {
			panic(fmt.Sprintf("Unable to parse character %v in to an int", r))
		}
	}

	if len(ints) == 1 {
		return 0
	}

	sum := 0
	for i := 0; i < len(ints)-1; i++ {
		if ints[i] == ints[i+1] {
			sum += ints[i]
		}
	}

	if ints[0] == ints[len(ints)-1] {
		sum += ints[0]
	}
	return sum
}
