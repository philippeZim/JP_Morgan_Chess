// Build and Scala version
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "3.6.1"

scalacOptions ++= Seq("-optimize")

// Project definition
lazy val root = (project in file("."))
    .settings(
        name := "JP_Morgan_Chess",

        // Dependencies fjhgdoa
        libraryDependencies ++= Seq(
            "org.scalactic" %% "scalactic" % "3.2.14",
            "org.scalatest" %% "scalatest" % "3.2.14" % Test,
            "org.scalafx" %% "scalafx" % "23.0.1-R34",
            "net.codingwell" %% "scala-guice" % "7.0.0",
            "com.lihaoyi" %% "requests" % "0.9.0",
            "com.lihaoyi" %% "upickle" % "4.0.2"
        )
    )
