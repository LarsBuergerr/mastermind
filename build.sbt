val scala3Version = "3.1.2"
val scalaTestVersion = "3.2.10"

lazy val root = project
  .in(file("."))
  .settings(
    name := "mastermind",
    
    version := "0.1.0-SNAPSHOT",
    fork / run := true, // not pretty but fixes error on second startup
    scalaVersion := scala3Version,
    
    libraryDependencies += "org.scalafx" %% "scalafx" % "16.0.0-R24",

    libraryDependencies ++= { 
      Seq(
      "org.scalameta" %% "munit" % "0.7.29" % Test,
      "org.scalactic" %% "scalactic" % scalaTestVersion,
      "org.scalatest" %% "scalatest" % scalaTestVersion % "test",
      "com.google.inject" % "guice" % "4.2.3",
      ("net.codingwell" %% "scala-guice" % "5.0.2").cross(CrossVersion.for3Use2_13),
      "org.scala-lang.modules" %% "scala-xml" % "2.0.1", // XML
      "com.lihaoyi" %% "upickle" % "1.4.4", // JSON upickle
      ("net.jcazevedo" %% "moultingyaml" % "0.4.2").cross(CrossVersion.for3Use2_13), //YAML
      ("com.typesafe.play" %% "play-json" % "2.9.3").cross(CrossVersion.for3Use2_13)), // JSON
    },
    libraryDependencies ++= {
  // Determine OS version of JavaFX binaries
      lazy val osName = System.getProperty("os.name") match {
        case n if n.startsWith("Linux") => "linux"
        case n if n.startsWith("Mac") => "mac"
        case n if n.startsWith("Windows") => "win"
        case _ => throw new Exception("Unknown platform!")
      } 
      Seq("base", "controls", "fxml", "graphics", "media", "swing", "web")
        .map(m => "org.openjfx" % s"javafx-$m" % "16" classifier osName)
    },
    
    jacocoReportSettings := JacocoReportSettings(
      "Jacoco Coverage Report",
      None,
      JacocoThresholds(),
      Seq(JacocoReportFormats.ScalaHTML, JacocoReportFormats.XML),
      "utf-8"),
    
      jacocoExcludes := Seq(
      "*aview.*",
      "*Mastermind.*",
      "*MastermindModule.*"
      ),
    
    jacocoCoverallsServiceName := "github-actions", 
    jacocoCoverallsBranch := sys.env.get("CI_BRANCH"),
    jacocoCoverallsPullRequest := sys.env.get("GITHUB_EVENT_NAME"),
    jacocoCoverallsRepoToken := sys.env.get("COVERALLS_REPO_TOKEN")
  )
  .enablePlugins(JacocoCoverallsPlugin)