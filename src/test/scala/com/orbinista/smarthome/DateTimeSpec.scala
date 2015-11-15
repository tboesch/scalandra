package com.orbinista.smarthome

import org.joda.time.{DateTimeZone, DateTime}

class DateTimeSpec extends UnitSpec {

  "It" should "be possible to convert joda datetime to java date" in {
    val fivePastTen = new DateTime(2014,9,2,22,5,0)
    val fivePastTwelve = new DateTime(2014,9,3,0,5,0)
    println(s"Five past ten '$fivePastTen'")
    println(s"Five past twelve '$fivePastTwelve'")
    val utc = fivePastTen.toDateTime(DateTimeZone.UTC)
    println(s"Five past ten utc '$utc'")

  }

}