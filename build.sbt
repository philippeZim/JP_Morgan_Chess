// Build and Scala version
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "3.6.1"


/*
lazy val javafxDependencies: Seq[ModuleID] = {
    lazy val osName = System.getProperty("os.name").toLowerCase match {
        case n if n.startsWith("linux")   => "linux"
        case n if n.startsWith("mac")     => "mac"
        case n if n.startsWith("windows") => "win"
        case other                        => throw new Exception(s"Unsupported platform: $other")
    }
    Seq("base", "controls", "fxml", "graphics", "media", "swing", "web")
        .map(module => "org.openjfx" % s"javafx-$module" % "16" classifier osName)
}

 */

// Project definition
lazy val root = (project in file("."))
    .settings(
        name := "JP_Morgan_Chess",

        // Dependencies fjhgdoa
        libraryDependencies ++= Seq(
            "org.scalactic" %% "scalactic" % "3.2.14",
            "org.scalatest" %% "scalatest" % "3.2.14" % Test,
            "org.scalafx" %% "scalafx" % "23.0.1-R34"
            //"org.scalafx" %% "scalafx" % "20.0.0-R31"
        )//  ++ javafxDependencies
    )
