val scala3Version = "3.1.2"
val scalaTestVersion = "3.2.10"

lazy val root = project
  .in(file("."))
  .settings(
    name := "mastermind",
    
    version := "0.1.0-SNAPSHOT",
    
    scalaVersion := scala3Version,
    
    libraryDependencies ++= { 
      Seq(
      "org.scalameta" %% "munit" % "0.7.29" % Test,
      "org.scalactic" %% "scalactic" % scalaTestVersion,
      "org.scalatest" %% "scalatest" % scalaTestVersion % "test"
      )
    },
    
    jacocoReportSettings := JacocoReportSettings(
      "Jacoco Coverage Report",
      None,
      JacocoThresholds(),
      Seq(JacocoReportFormats.ScalaHTML, JacocoReportFormats.XML),
      "utf-8"),
    
    jacocoCoverallsServiceName := "github-actions", 
    jacocoCoverallsBranch := sys.env.get("CI_BRANCH"),
    jacocoCoverallsPullRequest := sys.env.get("GITHUB_EVENT_NAME"),
    jacocoCoverallsRepoToken := sys.env.get("COVERALLS_REPO_TOKEN")
  )
  .enablePlugins(JacocoCoverallsPlugin)