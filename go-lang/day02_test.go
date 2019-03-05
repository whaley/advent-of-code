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

func TestDay02Pt1(t *testing.T) {
	input := []string{
		"abcdef",
		"bababc",
		"abbcde",
		"abcccd",
		"aabcdd",
		"abcdee",
		"ababab",
	}
	expectedChecksum := 12
	assert.Equal(t, expectedChecksum, computeChecksum(input))
}

func TestDay02Pt2(t *testing.T) {

}
