ThisBuild / scalaVersion := "2.13.7"
ThisBuild / organization := "com.example"

lazy val hello = (project in file("."))
  .settings(
    name := "Advent of Code 2020",
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.10",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.10" % Test,
  )

// name := "Advent of Code - 2020 - Scala"

// version := "0.1"





