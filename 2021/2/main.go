package main

import (
	"advent"
	"fmt"
	"strings"
	"strconv"
)

func main() {
	a()
	b()
}

func a() {
	data := tools.ReadFileAsArray("2")

	var h, d int
	for _, row := range data {
		if (row == "") {
			continue
		}

		val := strings.Fields(row)
		dist, _ := strconv.Atoi(val[1])

		switch val[0] {
		case "forward":
			h += dist
		case "down":
			d += dist
		case "up":
			d -= dist
		}
	}

	fmt.Println(h*d)
}

func b() {
	data := tools.ReadFileAsArray("2")

	var h, d, a int
	for _, row := range data {
		if (row == "") {
			continue
		}

		val := strings.Fields(row)
		dist, _ := strconv.Atoi(val[1])

		switch val[0] {
		case "forward":
			h += dist
			d += a*dist
		case "down":
			a += dist
		case "up":
			a -= dist
		}
	}

	fmt.Println(h*d)
}