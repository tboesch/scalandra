package com.orbinista.smarthome

import com.datastax.driver.core.{Session, ProtocolOptions, Cluster}
import org.joda.time.DateTimeZone
import org.joda.time.format.{DateTimeFormat, DateTimeFormatter}

object Cassandra {
  // .withCompression(ProtocolOptions.Compression.SNAPPY)
  def getConnection: Cluster = {
    Cluster.builder().addContactPoint("172.16.7.2").withPort(9042).build()
  }

  def getSession(implicit cluster: Cluster): Session = {
    cluster.connect("envdata")
  }

  object rows {

    object sensor {
      val createTableCQL =
        """
          DROP TABLE IF EXISTS sensors;
          CREATE TABLE sensors (
            enocean_id text,
            timestamp timestamp,
            unit text,
            value float,
            PRIMARY KEY (enocean_id, timestamp)
          ) WITH CLUSTERING ORDER BY (timestamp DESC)
          ;
        """

      def toObjectSeq(row: TimedValue): Seq[Object] = {
        Seq(
          row.address.asInstanceOf[Object],
          row.timestamp.toDate.asInstanceOf[Object],
          row.unit.asInstanceOf[Object],
          row.value.asInstanceOf[Object]
        )
      }

      def getRowInserter (session: Session): TimedValue => Unit = {
        val preparedStatement = session.prepare("INSERT INTO sensors(enocean_id, timestamp, unit, value) VALUES(?, ?, ?, ?);")
        (row: TimedValue) => session.execute(preparedStatement.bind(toObjectSeq(row): _*))
      }

    }

    object reading_by_day {
      val createTableCQL =
        """
          DROP TABLE IF EXISTS readings_by_day;
          CREATE TABLE readings_by_day (
             date text,
             timestamp timestamp,
             enocean_id text,
             unit text,
             value float,
             PRIMARY KEY (date, timestamp, enocean_id)
          ) WITH CLUSTERING ORDER BY (timestamp ASC, enocean_id ASC)
          ;
        """

      val fmt: DateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd")
      def toObjectSeq(row: TimedValue): Seq[Object] = {
        Seq(
          fmt.print(row.timestamp.toDateTime(DateTimeZone.UTC)),
          row.timestamp.toDateTime(DateTimeZone.UTC).toDate.asInstanceOf[Object],
          row.address.asInstanceOf[Object],
          row.unit.asInstanceOf[Object],
          row.value.asInstanceOf[Object]
        )
      }

      def getRowInserter (session: Session): TimedValue => Unit = {
        val preparedStatement = session.prepare("INSERT INTO readings_by_day(date,timestamp, enocean_id, unit, value) VALUES(?, ?, ?, ?, ?);")
        (row: TimedValue) => session.execute(preparedStatement.bind(toObjectSeq(row): _*))
      }

    }

  }

}


