package main

import (
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestDay02CalculateRepeats(t *testing.T) {
	testCases := []struct {
		input    string
		expected map[int]bool
	}{
		{"abcdef", map[int]bool{1: true}},
		{"bababc", map[int]bool{1: true, 2: true, 3: true}},
		{"abbcde", map[int]bool{1: true, 2: true}},
		{"abcccd", map[int]bool{1: true, 3: true}},
		{"abcdee", map[int]bool{1: true, 2: true}},
		{"ababab", map[int]bool{3: true}},
	}

	for _, tc := range testCases {
		tc := tc
		assert.Equal(t, tc.expected, calculateRepeats(tc.input))
	}
}

func TestDay02FindPairWithOneLetterDifference(t *testing.T) {
	input := []string{
		"abcde",
		"fghij",
		"klmno",
		"pqrst",
		"fguij",
		"axcye",
		"wvxyz",
	}
	expected := map[string]bool{"fghij": true, "fguij": true}
	actual := findPairWithOneLetterDifference(input)
	assert.Equal(t, expected, actual)
}

func TestStringIndexDiff(t *testing.T) {
	testCases := []struct {
		left     string
		right    string
		expected []int
	}{
		{"abc", "efg", []int{0, 1, 2}},
		{"abc", "abd", []int{2}},
		{"abc", "abc", []int{}},
	}

	for _, tc := range testCases {
		tc := tc
		actual := diff(tc.left, tc.right)
		assert.Equal(t, tc.expected, actual)
	}
}