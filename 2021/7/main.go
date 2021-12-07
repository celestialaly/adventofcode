package main

import (
	"advent"
	"fmt"
	"strings"
	"strconv"
	"math"
)

func main() {
	a()
	b()
}

func a() {
	data := strings.Split(tools.ReadFile("7"), ",")
	positions := []int{}
	max, min := 0, math.MaxInt

	for i := 0; i < len(data); i++ {
		val, _ := strconv.Atoi(data[i])
		positions = append(positions, val)
		if val > max {
			max = val
		}
		if val < min {
			min = val
		}
	}

	lowestCost := math.MaxInt
	for i := min; i <= max; i++ {
		cost := 0

		for _, pos := range positions {
			if pos > i {
				cost += pos-i
			} else if pos < i {
				cost += i-pos
			}
		}

		if cost < lowestCost {
			lowestCost = cost
		}
	}

	fmt.Println(lowestCost)
}

func b() {
	data := strings.Split(tools.ReadFile("7"), ",")
	positions := []int{}

	total := 0
	for i := 0; i < len(data); i++ {
		val, _ := strconv.Atoi(data[i])
		total += val
		positions = append(positions, val)
	}

	median := float64(total)/float64(len(positions))
	// for median = 4.9 (example values), the best outcome was using 5, but for 489.591 (puzzle input), it was 489 so we check the lowest/highest int
	possibleResults := []int{int(median), int(math.Round(median))}

	lowestCost := math.MaxInt
	for _, possibleResult := range possibleResults {
		cost := 0

		for _, pos := range positions {
			if pos > possibleResult {
				cost += sumFuel(pos-possibleResult)
			} else if pos < possibleResult {
				cost += sumFuel(possibleResult-pos)
			}
		}

		if cost < lowestCost {
			lowestCost = cost
		}
	}

	fmt.Println(lowestCost)
}

func sumFuel(n int) int {
	return n * (n + 1) / 2
}
