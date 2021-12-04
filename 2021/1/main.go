package main

import (
	"advent"
	"fmt"
)

func main() {
	a()
	b()
}

func a() {
	data := tools.ReadFileAsArrayOfInt("1")

	prev := data[0]
	count := 0
	for i := 1; i < len(data); i++ {
		val := data[i]
		if (val > prev) {
			count++
		}

		prev = val
	}

	fmt.Println(count)
}

func b() {
	data := tools.ReadFileAsArrayOfInt("1")

	prev := 0
	count := 0
	for i := 0; i < len(data) - 2; i++ {
		val := data[i]+data[i+1]+data[i+2]
		if (prev != 0 && val > prev) {
			count++
		}

		prev = val
	}

	fmt.Println(count)
}