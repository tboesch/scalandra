package com.orbinista.smarthome

import com.typesafe.scalalogging.LazyLogging
import org.joda.time.DateTime
import org.joda.time.format.{DateTimeFormatter, DateTimeFormat}
import tabulate.{RowDecoder, CellDecoder, DecodeResult}
import tabulate.ops._


object Csv extends LazyLogging {

  def timedValueIterator(rawData: java.net.URL): Iterator[TimedValue] = {
    implicit val codec = scala.io.Codec.UTF8
    def fmt: DateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")
    implicit val dateDecoder = CellDecoder(s => DecodeResult(DateTime.parse(s, fmt)))
    implicit val sensorValueRowDecoder = RowDecoder.caseDecoder4((t: DateTime, a: String, v: Float, u: String) => new TimedValue(t, a, v, u))(0, 1, 2, 3)

    rawData.asUnsafeCsvRows[TimedValue](separator = ',', header = false)
  }

}
