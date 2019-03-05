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
	return 0
}
