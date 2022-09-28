val scala3Version = "3.1.2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "mastermind",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test,
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.10",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.10" % "test"
  )

jacocoExcludes := Seq(
  "mastermind.Main",
  "Main.scala",
  "/src/main/scala/de/htwg/se/mastermind/Main.scala"
)

jacocoReportSettings := JacocoReportSettings(
  "Jacoco Coverage Report",
  None,
  JacocoThresholds(),
  Seq(JacocoReportFormats.ScalaHTML, JacocoReportFormats.XML), // note XML formatter
"utf-8")