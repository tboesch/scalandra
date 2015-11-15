package com.orbinista.smarthome

import org.joda.time.DateTime
import com.datastax.driver.core.exceptions.SyntaxError

object Import extends App {
  val cluster = Cassandra.getConnection
  val session = Cassandra.getSession(cluster)

  val insertSensorRow = Cassandra.rows.sensor.getRowInserter(session)
  val insertReadingRow = Cassandra.rows.reading_by_day.getRowInserter(session)

  println("Starting import: " + DateTime.now())

  val rawData = getClass.getResource("/enocean_data_log.csv")
  val dataIterator = Csv.timedValueIterator(rawData)
  dataIterator.foreach(row => {
    insertSensorRow(row)
    insertReadingRow(row)
  })

  println("Import finished: " + DateTime.now())

  session.close()
  cluster.close()

}
