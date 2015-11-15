name := "smarthome"

version := "1.0"

scalaVersion := "2.11.7"

val PhantomVersion = "1.12.2"

// http://www.scalatest.org
libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"

// https://nrinaudo.github.io/tabulate/
libraryDependencies += "com.nrinaudo" %% "tabulate" % "0.1.5"

// https://github.com/typesafehub/scala-logging
libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.2"

// https://github.com/nscala-time/nscala-time (Joda time wrapper)
libraryDependencies += "com.github.nscala-time" %% "nscala-time" % "2.4.0"

// https://github.com/datastax/java-driver/tree/2.1.9
libraryDependencies += "com.datastax.cassandra"  % "cassandra-driver-core" % "2.1.9"


// https://github.com/websudos/phantom
// libraryDependencies ++= Seq(
//  "com.websudos" %% "phantom-dsl" % PhantomVersion,
//  "com.websudos" %% "phantom-testkit" % PhantomVersion % "test, provided"
//)
