package main

import "fmt"

func calculateRepeats(s string) map[int]bool {
	charCounts := make(map[rune]int)
	for _, r := range s {
		charCounts[r] = charCounts[r] + 1
	}

	repeats := make(map[int]bool)
	for _, v := range charCounts {
		repeats[v] = true
	}
	return repeats
}

func computeChecksum(input []string) int {
	repeatCount := make(map[int]int)
	for _, s := range input {
		repeats := calculateRepeats(s)
		for entry := range repeats {
			repeatCount[entry] = repeatCount[entry] + 1
		}
	}

	product := 1
	for k, v := range repeatCount {
		if k != 1 {
			product *= v
		}
	}
	return product
}

func day02Pt2(input []string) string {
	correctBoxIds := findPairWithOneLetterDifference(input)
	ids := make([]string, 0)
	for k := range correctBoxIds {
		ids = append(ids, k)
	}
	commonRunes := make([]rune, 0, len(ids[0])-1)
	leftRunes := []rune(ids[0])
	rightRunes := []rune(ids[1])

	for i := range leftRunes {
		if leftRunes[i] == rightRunes[i] {
			commonRunes = append(commonRunes, leftRunes[i])
		}
	}
	return string(commonRunes)
}

func findPairWithOneLetterDifference(input []string) map[string]bool {
	for i, left := range input[:len(input)-1] {
		for _, right := range input[i+1:] {
			if len(diff(left, right)) == 1 {
				return map[string]bool{left: true, right: true}
			}
		}
	}
	return nil
}

func diff(left string, right string) []int {
	if len(left) != len(right) {
		panic(fmt.Errorf("%s and %s are not the same lengths", left, right))
	}
	diff := make([]int, 0, len(left))
	for i := range left {
		if left[i] != right[i] {
			diff = append(diff, i)
		}
	}
	return diff
}
