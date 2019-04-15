name := "spark-opentsdb-sink"
organization in ThisBuild := "nullm4ri"
scalaVersion in ThisBuild := "2.11.12"

lazy val global = project
    .in(file("."))
    .settings(settings,
      libraryDependencies ++= commonDependencies)

lazy val settings = resolverSetting ++ assemblySettings

lazy val resolverSetting = Seq(
  resolvers ++= Seq(
    Resolver.sonatypeRepo("releases"),
    Resolver.sonatypeRepo("snapshots")
  )
)

lazy val dependencies =
  new {
    val sparkVersion = "2.4.0"
    val asyncHttpClientVersion = ""
    val jacksonVersion = ""

    val sparkCore = "org.apache.spark" %% "spark-core" % sparkVersion % "provided"
    val asyncHttpClient = "org.asynchttpclient" % "async-http-client" % "2.8.1"// withSources() excludeAll (ExclusionRule(organization = "io.netty") :: excludeSlf4J: _*)
    val jacksonCore = "com.fasterxml.jackson.core" % "jackson-core" % "2.9.8"// withSources() excludeAll (excludeSlf4J: _*)

  }

lazy val commonDependencies = Seq(
  dependencies.sparkCore
  , dependencies.asyncHttpClient
  , dependencies.jacksonCore
)

lazy val assemblySettings = Seq(
  test in assembly := {},
  assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false),
  assemblyMergeStrategy in assembly := {
    case PathList("javax", "xml", xs@_*) => MergeStrategy.first
    case PathList("org", "slf4j", xs@_*) => MergeStrategy.first
    case PathList("org", "apache", "log4j", xs@_*) => MergeStrategy.first
    case PathList("javax", "annotation", xs@_*) => MergeStrategy.first
    case "library.properties" => MergeStrategy.discard
    case "asm-license.txt" => MergeStrategy.rename
    case s: String if s.startsWith("META-INF/maven/com.fasterxml.jackson.core") => MergeStrategy.filterDistinctLines
    case s: String if s.startsWith("META-INF/maven/com.sun.jersey") => MergeStrategy.filterDistinctLines
    case s: String if s.startsWith("META-INF/maven/org.codehaus.jettison") => MergeStrategy.filterDistinctLines
    case s: String if s.startsWith("META-INF/maven/org.slf4j") => MergeStrategy.filterDistinctLines
    case s: String if s.startsWith("META-INF/maven/ch.qos.logback") => MergeStrategy.filterDistinctLines
    case x => (assemblyMergeStrategy in assembly).value(x)
  },
  assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)
)