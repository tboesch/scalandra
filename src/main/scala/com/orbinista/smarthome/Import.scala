package com.orbinista.smarthome

import org.joda.time.DateTime

object Import extends App {
  implicit val cluster = Cassandra.getConnection
  implicit val session = Cassandra.getSession

  val insertSensorRow = Cassandra.rows.sensor.getRowInserter(session)
  val insertReadingRow = Cassandra.rows.reading_by_day.getRowInserter(session)

  println("Starting import: " + DateTime.now())

  val rawData = getClass.getResource("/enocean_data_log.csv")
  val dataIterator = Csv.timedValueIterator(rawData)
  dataIterator.take(1000).foreach(row => {
    insertSensorRow(row)
    insertReadingRow(row)
  })

  println("Import finished: " + DateTime.now())

  session.close()
  cluster.close()

}
