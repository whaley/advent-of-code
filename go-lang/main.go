package main

import (
	"fmt"
	"io/ioutil"
	"strings"
)

func main() {
	day01()
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


func day01() error {
	data := readFully("day01.txt")
	freqs := strings.Split(data,"\n")
	for idx, freq := range freqs {
		freqs[idx] = strings.TrimSpace(freq)
	}

	pt1Answer := computeFrequency(freqs)
	pt2Answer := findRepeatingFrequency(freqs)

	fmt.Printf("Day 01 : Part 01  Answer:\n\t%d\n", pt1Answer)
	fmt.Printf("Day 01 : Part 02  Answer:\n\t%d\n", pt2Answer)
	return nil
}
