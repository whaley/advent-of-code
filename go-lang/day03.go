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

type Point struct {
	x int
	y int
}

type Grid map[Point]map[int]bool

//String input expected in the form of "#1 @ 1,3: 4x4"
func createRect(input string) (Rectangle, error) {
	regex := regexp.MustCompile(`#(?P<id>\d+)[\s@]+(?P<tlX>\d+),(?P<tlY>\d+)[:\s]+(?P<wide>\d+)x(?P<tall>\d+)`)
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
			err = fmt.Errorf("invalid square input string of '%s'", input)
		}
		if err != nil {
			return Rectangle{}, err
		}
	}
	return *rect, nil
}

func NewGrid(rects []Rectangle) Grid{
	grid := Grid{}
	for _, rect := range rects {
		grid.addRectToGrid(rect)
	}
	return grid
}

func (grid Grid) addRectToGrid(rectangle Rectangle) {
	for xStep := 0; xStep < rectangle.wide; xStep++ {
		for yStep := 0; yStep < rectangle.tall; yStep++ {
			x := rectangle.topLeftX + xStep
			y := rectangle.topLeftY + yStep
			point := Point{x, y}
			if set, ok := grid[point]; !ok {
				set = map[int]bool{rectangle.id: true}
				grid[point] = set
			} else {
				grid[point][rectangle.id] = true
			}
		}
	}
}

func (grid Grid) FilterBy(filter func(map[int]bool) bool) []Point  {
	var matchedPoints []Point
	for point, claims := range grid {
		if filter(claims) {
			matchedPoints = append(matchedPoints, point)
		}
	}
	return matchedPoints
}
