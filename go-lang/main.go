package main

import (
	"fmt"
	"io/ioutil"
	"strings"
)

func main() {
	day01()
	day02()
	day03()
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
	lines := delimitByNewLine(readFully("day02.txt"))

	pt1Answer := computeChecksum(lines)
	pt2Answer := day02Pt2(lines)

	fmt.Printf("Day 02 : Part 01  Answer:\n\t%d\n", pt1Answer)
	fmt.Printf("Day 02 : Part 02  Answer:\n\t%s\n", pt2Answer)
}

func day03() {
	var rects []Rectangle
	for _, line := range delimitByNewLine(readFully("day03.txt")) {
		rect, err := createRect(line)
		check(err)
		rects = append(rects, rect)
	}
	grid := NewGrid(rects)
	day0301Points:= grid.FilterBy(func(claims map[int]bool) bool {
		return len(claims) > 1
	})

	id, err := FindNonOverlappingClaim(rects)
	if err != nil {
		panic(err)
	}
	fmt.Printf("Day 03 : Part 01  Answer:\n\t%d\n", len(day0301Points))
	fmt.Printf("Day 03 : Part 02  Answer:\n\t%d\n", id)
}
