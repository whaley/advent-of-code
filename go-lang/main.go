package main

import (
	"fmt"
	"io/ioutil"
	"strings"
)

func main() {
	day01()
	day02()
}

func check(e error) {
	if e != nil {
		panic(e)
	}
}

func readFully(filepath string) string {
	dat, err := ioutil.ReadFile(filepath)
	check(err)
	return string(dat)
}

func delimitByNewLine(s string) []string {
	lines := strings.Split(s, "\n")
	for idx, freq := range lines {
		lines[idx] = strings.TrimSpace(freq)
	}
	return lines
}

func day01() {
	freqs := delimitByNewLine(readFully("day01.txt"))
	for idx, freq := range freqs {
		freqs[idx] = strings.TrimSpace(freq)
	}

	pt1Answer := computeFrequency(freqs)
	pt2Answer := findRepeatingFrequency(freqs)

	fmt.Printf("Day 01 : Part 01  Answer:\n\t%d\n", pt1Answer)
	fmt.Printf("Day 01 : Part 02  Answer:\n\t%d\n", pt2Answer)
}

func day02() {
	strings := delimitByNewLine(readFully("day02.txt"))

	pt1Answer := computeChecksum(strings)

	fmt.Printf("Day 02 : Part 01  Answer:\n\t%d\n", pt1Answer)
}
