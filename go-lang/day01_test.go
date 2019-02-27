package main

import (
	"fmt"
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestPt1(t *testing.T) {
	testCases := []struct {
		input    []string
		expected int
	}{
		{input: []string{"+1", "+1", "+1"}, expected: 3},
		{input: []string{"+1", "+1", "-2"}, expected: 0},
		{input: []string{"-1", "-2", "-3"}, expected: -6},
	}

	for _, tc := range testCases {
		actual := computeFrequency(tc.input)
		assert.Equal(t, tc.expected, actual,
			fmt.Sprintf("computeFrequency(%s) should have produced a frequncy of %d", tc.input, tc.expected))
	}
}

func TestPt2(t *testing.T) {
	testCases := []struct {
		input    []string
		expected int
	}{
		{input: []string{"+1", "-1"}, expected: 0},
		{input: []string{"+3", "+3", "+4", "-2", "-4"}, expected: 10},
		{input: []string{"-6", "+3", "+8", "+5", "-6" }, expected: 5},
		{input: []string{"+7", "+7", "-2", "-7", "-4" }, expected: 14},
	}

	for _, tc := range testCases {
		actual := findRepeatingFrequency(tc.input)
		assert.Equal(t, tc.expected, actual,
			fmt.Sprintf("findRepeatingFrequency(%s) should have produced output of %d", tc.input, tc.expected))
	}
}
