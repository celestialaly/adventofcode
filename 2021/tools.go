package tools

import (
	"log"
	"io/ioutil"
	"strings"
	"strconv"
)

func ReadFileAsArray(day string) []string {
    fileBytes, err := ioutil.ReadFile(day+"/input.txt")

	if err != nil {
		log.Fatal(err)
	}
	
	return strings.Split(string(fileBytes), "\n")
}

func ReadFileAsArrayOfInt(day string) []int {
    data := ReadFileAsArray(day)
	intData := []int{}

	for i := 0; i < len(data); i++ {
		val := strings.TrimSpace(data[i])

		if (val == "") {
			continue
		}

		v, err := strconv.Atoi(val)
        if err != nil {
            panic(err)
        }

		intData = append(intData, v)
	}

	return intData
}
