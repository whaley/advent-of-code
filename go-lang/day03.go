package main

import (
	"fmt"
	"regexp"
	"strconv"
)

type Rectangle struct {
	id       int
	topLeftX int
	topLeftY int
	wide     int
	tall     int
}

//String input expected in the form of "#1 @ 1,3: 4x4"
func createRect(input string) (Rectangle, error) {
	regex := regexp.MustCompile(`#(?P<id>\d)[\s@]+(?P<tlX>\d),(?P<tlY>\d)[:\s]+(?P<wide>\d)x(?P<tall>\d)`)
	matches := regex.FindStringSubmatch(input)
	rect := &Rectangle{}
	//res := regex.FindStringSubmatch(s)
	for i, name := range regex.SubexpNames() {
		if i == 0 {
			continue
		}
		var err error
		switch name {
		case "id":
			rect.id, err = strconv.Atoi(matches[i])
		case "tlX":
			rect.topLeftX, err = strconv.Atoi(matches[i])
		case "tlY":
			rect.topLeftY, err = strconv.Atoi(matches[i])
		case "wide":
			rect.wide, err = strconv.Atoi(matches[i])
		case "tall":
			rect.tall, err = strconv.Atoi(matches[i])
		default:
			err = fmt.Errorf("Invalid square input string of '%s'", input)
		}
		if err != nil {
			return Rectangle{}, err
		}
	}
	return *rect, nil
}

/*
switch os := runtime.GOOS; os {
	case "darwin":
		fmt.Println("OS X.")
	case "linux":
		fmt.Println("Linux.")
	default:
		// freebsd, openbsd,
		// plan9, windows...
		fmt.Printf("%s.\n", os)
	}
 */
