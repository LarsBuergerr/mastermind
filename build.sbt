val scala3Version = "3.1.2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "mastermind",
  )


jacocoReportSettings := JacocoReportSettings(
  "Jacoco Coverage Report",
  None,
  JacocoThresholds(),
  Seq(JacocoReportFormats.ScalaHTML, JacocoReportFormats.XML), // note XML formatter
"utf-8")
