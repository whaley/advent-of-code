package main

import (
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestDay03InputToRectangle(t *testing.T) {
	cases := []struct {
		input    string
		expected Rectangle
	}{
		{"#1 @ 1,3: 4x4", Rectangle{1, 1, 3, 4, 4}},
		{"#2 @ 3,1: 4x4", Rectangle{2, 3, 1, 4, 4,}},
		{"#3 @ 5,5: 2x2", Rectangle{3, 5, 5, 2, 2,}},
		{"#1 @ 604,100: 17x27", Rectangle{1, 604, 100, 17,27}},
	}

	for _, tc := range cases {
		actual, err := createRect(tc.input)
		assert.NoError(t, err)
		assert.Equal(t, tc.expected, actual, "")
	}
}

func TestDay03CalculateGrid(t *testing.T) {
	tests := []struct {
		name  string
		input []Rectangle
		grid  Grid
	}{
		{"Empty input", nil, map[Point]map[int]bool{}},
		{"Single Input", []Rectangle{{1, 1, 3, 4, 4}},
			Grid{
				Point{1, 3}: {1: true},
				Point{2, 3}: {1: true},
				Point{3, 3}: {1: true},
				Point{4, 3}: {1: true},
				Point{1, 4}: {1: true},
				Point{2, 4}: {1: true},
				Point{3, 4}: {1: true},
				Point{4, 4}: {1: true},
				Point{1, 5}: {1: true},
				Point{2, 5}: {1: true},
				Point{3, 5}: {1: true},
				Point{4, 5}: {1: true},
				Point{1, 6}: {1: true},
				Point{2, 6}: {1: true},
				Point{3, 6}: {1: true},
				Point{4, 6}: {1: true},
			}},
		{"Multiple Input",
			[]Rectangle{
				{1, 1, 3, 4, 4},
				{2, 3, 1, 4, 4,},
				{3, 5, 5, 2, 2,},
			},
			/*
			This draws the following grid, where X are the points where 2 and both occupy a space:
			........
			...2222.
			...2222.
			.11XX22.
			.11XX22.
			.111133.
			.111133.
			........
			 */
			Grid{
				Point{3, 1}: {2: true},
				Point{4, 1}: {2: true},
				Point{5, 1}: {2: true},
				Point{6, 1}: {2: true},
				Point{3, 2}: {2: true},
				Point{4, 2}: {2: true},
				Point{5, 2}: {2: true},
				Point{6, 2}: {2: true},
				Point{1, 3}: {1: true},
				Point{2, 3}: {1: true},
				Point{3, 3}: {1: true, 2: true},
				Point{4, 3}: {1: true, 2: true},
				Point{5, 3}: {2: true},
				Point{6, 3}: {2: true},
				Point{1, 4}: {1: true},
				Point{2, 4}: {1: true},
				Point{3, 4}: {1: true, 2: true},
				Point{4, 4}: {1: true, 2: true},
				Point{5, 4}: {2: true},
				Point{6, 4}: {2: true},
				Point{1, 5}: {1: true},
				Point{2, 5}: {1: true},
				Point{3, 5}: {1: true},
				Point{4, 5}: {1: true},
				Point{1, 6}: {1: true},
				Point{2, 6}: {1: true},
				Point{3, 6}: {1: true},
				Point{4, 6}: {1: true},
				Point{5, 5}: {3: true},
				Point{6, 5}: {3: true},
				Point{5, 6}: {3: true},
				Point{6, 6}: {3: true},
			}},
	}

	for _, test := range tests {
		test := test
		t.Run(test.name, func(t *testing.T) {
			t.Parallel()
			assert.Equal(t, test.grid, NewGrid(test.input))
		})
	}
}

func TestDay03Part01(t *testing.T) {
	input := []Rectangle{
		{1, 1, 3, 4, 4},
		{2, 3, 1, 4, 4,},
		{3, 5, 5, 2, 2,},
	}
	grid := NewGrid(input)
	overlappingPoints := grid.FilterBy(func(claimSet map[int]bool) bool {
		return len(claimSet) > 1
	})
	assert.Equal(t, 4, len(overlappingPoints))
}
