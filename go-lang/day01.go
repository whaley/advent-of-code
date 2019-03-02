package main

import (
	"log"
	"strconv"
)

func computeFrequency(freqChanges []string) int {
	freq := 0
	for _, changeString := range freqChanges {
		change := stringToInt(changeString)
		freq += change
	}
	return freq
}

func findRepeatingFrequency(freqChanges []string) int {
	freq := 0
	seen := make(map[int]bool)
	idx := 0

	for !seen[freq] {
		seen[freq] = true

		change := stringToInt(freqChanges[idx])
		freq += change

		idx++
		if idx == len(freqChanges) {
			idx = 0
		}
	}
	return freq
}

func stringToInt(s string) int {
	res, err := strconv.Atoi(s)
	if err != nil {
		log.Fatal(err)
	}
	return res
}
