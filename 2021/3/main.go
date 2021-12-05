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
	data := tools.ReadFileAsArray("3")

	var bitlen int = len(data[0])
	g := make([]int, bitlen)

	for _, row := range data {
		if (row == "") {
			continue
		}

		for i := 0; i < bitlen; i++ {
			if row[i:i+1] == "1" {
				g[i]++
			} else {
				g[i]--
			}
		}
	}

	var gamma, epsilon string
	for _, value := range g {
		if value >= 0 {
			gamma += "1"
			epsilon += "0"
		} else {
			gamma += "0"
			epsilon += "1"
		}
	}

	gammaDec, _ := strconv.ParseInt(gamma, 2, 64);
	epsilonDec, _ := strconv.ParseInt(epsilon, 2, 64);

	fmt.Println(gammaDec*epsilonDec)
}

func b() {
	data := tools.ReadFileAsArray("3")
	dataset := data

	bitlen := len(data[0])
	o2 := ""
	co2 := ""

	for i := 0; i < bitlen; i++ {
		if len(dataset) == 1 {
			o2 = dataset[0]
			break
		}

		v := 0
		for _, row := range dataset {
			if row == "" {
				continue
			}

			if row[i:i+1] == "1" {
				v++
			} else {
				v--
			}
		}

		var newDataset = []string{}
		for _, row := range dataset {
			if row == "" {
				continue
			} else if v >= 0 && row[i:i+1] == "1" {
				newDataset = append(newDataset, row)
			} else if v < 0 && row[i:i+1] == "0" {
				newDataset = append(newDataset, row)
			}
		}

		dataset = newDataset
	}

	if o2 == "" {
		o2 = dataset[0]
	}

	dataset = data
	for i := 0; i < bitlen; i++ {
		if len(dataset) == 1 {
			co2 = dataset[0]
			break
		}

		v := 0
		for _, row := range dataset {
			if row == "" {
				continue
			}

			if row[i:i+1] == "1" {
				v++
			} else {
				v--
			}
		}

		var newDataset = []string{}
		for _, row := range dataset {
			if row == "" {
				continue
			} else if v >= 0 && row[i:i+1] == "0" {
				newDataset = append(newDataset, row)
			} else if v < 0 && row[i:i+1] == "1" {
				newDataset = append(newDataset, row)
			}
		}

		dataset = newDataset
	}

	if co2 == "" {
		co2 = dataset[0]
	}

	o2Dec, _ := strconv.ParseInt(strings.TrimSpace(o2), 2, 64)
	co2Dec, _ := strconv.ParseInt(strings.TrimSpace(co2), 2, 64)

	fmt.Println(o2Dec*co2Dec)
}
