package com.orbinista.smarthome

import scala.collection.JavaConversions._

object Query extends App {
  val cluster = Cassandra.getConnection
  val session = Cassandra.getSession(cluster)

  val results = session.execute("SELECT * FROM readings_by_day WHERE date = '2014-09-10' LIMIT 20;");

  results.iterator().foreach(row => println(s"Fetched row '$row'"))

  session.close()
  cluster.close()

}
