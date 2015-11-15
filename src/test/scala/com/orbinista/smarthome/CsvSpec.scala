package com.orbinista.smarthome

import org.joda.time.DateTime
import org.scalatest._

class CsvSpec extends UnitSpec {

  "the timedValueIterator" should "parse sensor value data from a file" in {
    val rawData = getClass.getResource("/sensor_test_values.csv")
    val iterator = Csv.timedValueIterator(rawData)
    assert(iterator.isEmpty === false)
    // 2014-09-02 18:16:04
    assert(iterator.next().timestamp === new DateTime(2014,9,2,18,16,4))
    val datetime = iterator.next().timestamp
    println(s"Datetime: '$datetime'")
  }

}
