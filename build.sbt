organization := "com.github.jeffreytai"
name := "ArbitrageCalculator"
version := "0.1"

scalaVersion := "2.12.4"

// required for javax.ws.rs jar error
val workaround = {
  sys.props += "packaging.type" -> "jar"
  ()
}

libraryDependencies += "org.knowm.xchange" % "xchange-gdax" % "4.3.3"
libraryDependencies += "org.knowm.xchange" % "xchange-gemini" % "4.3.3"
libraryDependencies += "org.knowm.xchange" % "xchange-bitstamp" % "4.3.3"
libraryDependencies += "org.knowm.xchange" % "xchange-kraken" % "4.3.3"
libraryDependencies += "org.knowm.xchange" % "xchange-cexio" % "4.3.3"

libraryDependencies += "javax.ws.rs" % "javax.ws.rs-api" % "2.1"