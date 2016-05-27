import sbt._

trait Dependency {
  val JERSEY_VERSION = "1.19.1"

  val excludeSlf4J = List(
    ExclusionRule(organization = "org.slf4j"),
    ExclusionRule(organization = "log4j")
  )

  object Resolvers {
    lazy val jettyRepo        = DefaultMavenRepository
    lazy val JavaNet          = JavaNet2Repository
    lazy val jbossRepo        = "JBoss repo"             at "https://repository.jboss.org/nexus/content/repositories/public"
    lazy val webPluginRepo    = "Web plugin repo"        at "http://siasia.github.com/maven2"
    lazy val clojureRepo      = "Clojars"                at "http://clojars.org/repo"
    lazy val sonatypeSnapshot = "Sonatype Snapshots"     at "https://oss.sonatype.org/content/repositories/snapshots"
    lazy val sonatypeReleases = "Sonatype Releases"      at "https://oss.sonatype.org/content/repositories/releases"
    lazy val bintrayRepo      = "JCenter bintray repo"   at "http://jcenter.bintray.com"
    lazy val twitterRepo      = "twitter.com"            at "http://maven.twttr.com/" // URL regex cleaning, finagle, scrooge
    lazy val akkaRepo         = "Typesafe Repository"    at "http://repo.typesafe.com/typesafe/releases"
    lazy val jetloreSnapshots = "Jetlore snapshots repo" at "http://repo.jetlore.com/snapshots/" // we pushed ScalaNLP Maven repo here
    lazy val jetloreReleases  = "Jetlore releases repo"  at "http://repo.jetlore.com/releases/" // twitter_text is here

    val jetloreIvySnapshots   = Resolver.url("Jetlore Ivy Snapshots Repository", url("http://repo.jetlore.com/ivy-snapshots"))(Resolver.ivyStylePatterns)
    val jetloreIvyReleases    = Resolver.url("Jetlore Ivy Releases Repository", url("http://repo.jetlore.com/ivy-releases"))(Resolver.ivyStylePatterns)
  }

  object CompileScope {
    lazy val jersey_client  = "com.sun.jersey" % "jersey-client"  % JERSEY_VERSION withSources() excludeAll (excludeSlf4J: _*)
    lazy val jersey_json    = "com.sun.jersey" % "jersey-json"    % JERSEY_VERSION withSources() excludeAll (excludeSlf4J: _*)
  }

  object ProvidedScope {
    lazy val spark_core_transitive = "org.apache.spark" % "spark-core_2.10" % "1.4.1-JETLORE" withSources()
  }

  object TestScope {

  }
}