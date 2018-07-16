package main

import (
	"testing"

	"github.com/stretchr/testify/assert"
)

func TestCaptcha(t *testing.T) {
	cases := []struct {
		input    string
		expected int
	}{
		{"1122", 3},
		{"1111", 4},
		{"1234", 0},
		{"91212129", 9},
	}

	for _, c := range cases {
		assert.Equal(t, Captcha(c.input), c.expected, "result of captcha is not the same as expected")
	}
}
