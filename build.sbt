name := "advent-of-code-2022"
organization := "code.of.advent"
version := "1.0"
scalaVersion := "2.13.8"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.1" % "test"
enablePlugins(GraalVMNativeImagePlugin)