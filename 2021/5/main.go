package main

import (
	"advent"
	"fmt"
	"strconv"
	"regexp"
)

func main() {
	a()
	b()
}

func a() {
	data := tools.ReadFileAsArray("5")
	matrix := map[int]map[int]int{}
	r, _ := regexp.Compile("([0-9]+),([0-9]+) -> ([0-9]+),([0-9]+)")

	for _, val := range data {
		match := r.FindStringSubmatch(val)
		x1, _ := strconv.Atoi(match[1])
		y1, _ := strconv.Atoi(match[2])
		x2, _ := strconv.Atoi(match[3])
		y2, _ := strconv.Atoi(match[4])
		
		if x1 != x2 && y1 != y2 {
			continue
		}

		i, j := 0, 0
		if x1 == x2 {
			if (y1 > y2) {
				i = y2
				j = y1
			} else {
				i = y1
				j = y2
			}
		
			for i = i; i <= j; i++ {
				if matrix[x1] == nil {
					matrix[x1] = map[int]int{}
				}
				matrix[x1][i]++
			}
		} else {
			if (x1 > x2) {
				i = x2
				j = x1
			} else {
				i = x1
				j = x2
			}
			
			for i = i; i <= j; i++ {
				if matrix[i] == nil {
					matrix[i] = map[int]int{}
				}
				matrix[i][y1]++
			}
		}
	}

	count := 0
	for _, col := range matrix {
		for _, cell := range col {
			if cell > 1 {
				count++
			}
		}
	}

	fmt.Println(count)
}

func b() {
	data := tools.ReadFileAsArray("5")
	matrix := map[int]map[int]int{}
	r, _ := regexp.Compile("([0-9]+),([0-9]+) -> ([0-9]+),([0-9]+)")

	for _, val := range data {
		match := r.FindStringSubmatch(val)
		x1, _ := strconv.Atoi(match[1])
		y1, _ := strconv.Atoi(match[2])
		x2, _ := strconv.Atoi(match[3])
		y2, _ := strconv.Atoi(match[4])

		i, j := 0, 0
		if x1 == x2 {
			if (y1 > y2) {
				i = y2
				j = y1
			} else {
				i = y1
				j = y2
			}
		
			for i = i; i <= j; i++ {
				if matrix[x1] == nil {
					matrix[x1] = map[int]int{}
				}
				matrix[x1][i]++
			}
		} else if y1 == y2 {
			if (x1 > x2) {
				i = x2
				j = x1
			} else {
				i = x1
				j = x2
			}
			
			for i = i; i <= j; i++ {
				if matrix[i] == nil {
					matrix[i] = map[int]int{}
				}
				matrix[i][y1]++
			}
		} else if x1 == y1 && x2 == y2 {
			for i = x1; i <= x2; i++ {
				if matrix[i] == nil {
					matrix[i] = map[int]int{}
				}
				matrix[i][i]++
			}
		} else {
			// it works, but it's ugly
			diff1 := x1-y1
			diff2 := x2-y2

			if (diff1 < 0) {
				diff1 = 0-diff1
			}
			if (diff2 < 0) {
				diff2 = 0-diff2
			}

			if (diff1 != diff2) {
				// match these: 5,5 -> 8,2
				if (x1+y1 != x2+y2) {
					continue;
				}
				
				a := 0
				b := 0
				c := 0
				if x1 == y1 {
					a = x1
					b = y1
					c = x2
				} else {
					a = x2
					b = y2
					c = x1
				}

				if a < c {
					for x,y := a, b; x <= c; x,y = x+1, y-1 {
						if matrix[x] == nil {
							matrix[x] = map[int]int{}
						}
						matrix[x][y]++
					}
				} else {
					for x,y := a, b; x >= c; x,y = x-1, y+1 {
						if matrix[x] == nil {
							matrix[x] = map[int]int{}
						}
						matrix[x][y]++
					}
				}

				continue;
			}

			if x1 > x2 && y1 > y2 {
				for x,y := x2,y2; x <= x1; x,y = x+1, y+1 {
					if matrix[x] == nil {
						matrix[x] = map[int]int{}
					}
					matrix[x][y]++
				}
			} else if x1 > x2 && y1 < y2 {
				for x, y := x2,y2; x <= x1; x, y = x+1, y-1 {
					if matrix[x] == nil {
						matrix[x] = map[int]int{}
					}
					matrix[x][y]++
				}
			} else if x1 < x2 && y1 > y2 {
				for x, y := x1,y1; x <= x2; x, y = x+1, y-1 {
					if matrix[x] == nil {
						matrix[x] = map[int]int{}
					}
					matrix[x][y]++
				}
			} else if x1 < x2 && y1 < y2 {
				for x,y := x1,y1; x <= x2; x,y = x+1, y+1 {
					if matrix[x] == nil {
						matrix[x] = map[int]int{}
					}
					matrix[x][y]++
				}
			}
		}
	}

	count := 0
	for _, col := range matrix {
		for _, cell := range col {
			if cell > 1 {
				count++
			}
		}
	}

	fmt.Println(count)
}