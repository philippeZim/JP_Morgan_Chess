// Build and Scala version
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "3.6.1"

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
            "com.google.inject" % "guice" % "5.1.0",
            "com.lihaoyi" %% "requests" % "0.9.0",
            "com.lihaoyi" %% "upickle" % "4.0.2",
            "org.scala-lang.modules" %% "scala-xml" % "2.3.0",
            "org.playframework" %% "play-json" % "3.0.4"
        ),
        coverageExcludedPackages := "<empty>;.*aView.*",

    )
