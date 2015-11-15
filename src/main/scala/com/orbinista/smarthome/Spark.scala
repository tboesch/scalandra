package com.orbinista.smarthome

import org.apache.spark.{SparkConf, SparkContext}
import com.datastax.spark.connector._

object Spark extends App {

  val conf = new SparkConf(true)
    .set("spark.cassandra.connection.host", "172.16.7.2")

  val sc = new SparkContext("spark://Tobiass-MBP.lan:7077", "envdata", conf)

  val rdd = sc.cassandraTable("envdata", "sensors")
  println(rdd.first)

}
