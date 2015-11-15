name := "smarthome"

version := "1.0"

scalaVersion := "2.10.4"

// http://www.scalatest.org
libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.2.4" % "test"

// https://nrinaudo.github.io/tabulate/
libraryDependencies += "com.nrinaudo" %% "tabulate" % "0.1.5"
libraryDependencies += "com.github.mpilquist" %% "simulacrum" % "0.5.0"

// https://github.com/nscala-time/nscala-time (Joda time wrapper)
libraryDependencies += "com.github.nscala-time" %% "nscala-time" % "2.4.0"

// https://github.com/datastax/java-driver/tree/2.1.9
libraryDependencies += "com.datastax.cassandra"  % "cassandra-driver-core" % "2.1.9"

// http://spark.apache.org/docs/1.5.2/index.html
libraryDependencies += "org.apache.spark" %% "spark-core" % "1.5.2"

// https://github.com/datastax/spark-cassandra-connector
libraryDependencies += "com.datastax.spark" %% "spark-cassandra-connector" % "1.5.0-M2"
