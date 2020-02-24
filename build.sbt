scalaVersion := "2.13.1"
name := "hamsters-core"
organization := "io.github.scala-hamsters"
version := "1.0.0"

libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "3.1.1" % "test",    
    "org.scalacheck" %% "scalacheck" % "1.14.1" % "test"
)
