import Dependencies._

libraryDependencies += "org.json4s" %% "json4s-native" % "4.0.6"
libraryDependencies += "org.json4s" %% "json4s-jackson" % "4.0.6"


ThisBuild / scalaVersion     := "2.13.10"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.verve"
ThisBuild / organizationName := "verve"

lazy val root = (project in file("."))
  .settings(
    name := "data-engineer-challenge",
    libraryDependencies += munit % Test
  )

