package main

func calculateRepeats(s string) map[int]bool {
	charCounts := make(map[rune]int)
	for _, r := range s {
		charCounts[r] = charCounts[r] + 1
	}

	repeats := make(map[int]bool)
	for _, v := range charCounts {
		repeats[v] = true
	}
	return repeats
}

func computeChecksum(input []string) int {
	repeatCount := make(map[int]int)
	for _, s := range input {
		repeats := calculateRepeats(s)
		for entry := range repeats {
			repeatCount[entry] = repeatCount[entry] + 1
		}
	}

	product := 1
	for k, v := range repeatCount {
		if k != 1 {
			product *= v
		}
	}
	return product
}
