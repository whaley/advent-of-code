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
	}

	for _, tc := range cases {
		actual, err := createRect(tc.input)
		assert.NoError(t, err)
		assert.Equal(t, tc.expected, actual, "")
	}
}

func TestDay03Part01(t *testing.T) {
	//input := []string{
	//"#1 @ 1,3: 4x4",
	//"#2 @ 3, 1: 4x4",
	//"#3 @ 5, 5: 2x2",
	//}
	//expected := 2
	assert.Fail(t, "Must implement calculateIntersections(input)")
	//assert.Equal(t, expected, calculateIntersections(input))

}
