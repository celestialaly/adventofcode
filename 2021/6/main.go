package main

import (
	"advent"
	"fmt"
	"strings"
	"strconv"
	_"regexp"
)

func main() {
	a()
	b()
}

func a() {
	data := strings.Split(tools.ReadFile("6"), ",")
	fishes := []int{}

	for i := 0; i < len(data); i++ {
		val, _ := strconv.Atoi(data[i])
		fishes = append(fishes, val)
	}

	for day := 0; day < 80; day++ {
		for i := 0; i < len(fishes); i++ {
			if fishes[i] == 0 {
				fishes[i] = 6
				fishes = append(fishes, 9) // 9 because the new value pass through the loop so we decrease it right after to 8
			} else {
				fishes[i]--
			}
		}
	}

	fmt.Println(len(fishes))
}

func b() {
	data := strings.Split(tools.ReadFile("6"), ",")
	fishes := map[int]int{}

	// instead of having one entry per fish, we count the fishes according to their timers
	for i := 0; i < len(data); i++ {
		val, _ := strconv.Atoi(data[i])
		fishes[val]++
	}

	for day := 0; day < 256; day++ {
		newSet := map[int]int{}

		for days, number := range fishes {
			if days == 0 {
				newSet[6] += number
				newSet[8] += number
			} else {
				newSet[days-1] += number
			}
		}

		fishes = newSet
	}

	count := 0
	for _, number := range fishes {
		count += number
	}

	fmt.Println(count)
}
