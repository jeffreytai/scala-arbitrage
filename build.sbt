//import sbtassembly.AssemblyKeys._

// sbt-assembly
//assemblySettings

organization := "com.github.jeffreytai"
name := "ArbitrageCalculator"
version := "0.1"

scalaVersion := "2.12.4"

resolvers += "jitpack.io" at "https://jitpack.io"

// required for javax.ws.rs jar error
val workaround = {
  sys.props += "packaging.type" -> "jar"
  ()
}

libraryDependencies ++= Seq(
  // Exchange dependencies
  "org.knowm.xchange"     % "xchange-gdax"      % "4.3.3",
  "org.knowm.xchange"     % "xchange-gemini"    % "4.3.3",
  "org.knowm.xchange"     % "xchange-bitstamp"  % "4.3.3",
  "org.knowm.xchange"     % "xchange-kraken"    % "4.3.3",
  "org.knowm.xchange"     % "xchange-cexio"     % "4.3.3",

  // Slack dependency
  "com.github.allbegray"  % "slack-api"         % "v1.5.1.RELEASE",
  "javax.ws.rs"           % "javax.ws.rs-api"   % "2.1"
)

//mergeStrategy in assembly := {
//  case x if x.endsWith("io.netty.versions.properties") => MergeStrategy.first
//}

assemblyJarName in assembly := "scala-arbitrage.jar"
mainClass in assembly := Some("Main")

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", "io.netty.versions.properties")   => MergeStrategy.last
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}