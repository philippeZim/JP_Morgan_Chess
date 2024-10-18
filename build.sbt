val scala3Version = "3.5.1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "Scala 3 Project Template",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies += "org.scalameta" %% "munit" % "1.0.0" % Test
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.14"
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.14" % "test"
  )
