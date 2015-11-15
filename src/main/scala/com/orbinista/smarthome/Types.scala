package com.orbinista.smarthome

import com.github.nscala_time.time.Imports._

sealed trait SensorValue {
  def timestamp: DateTime

  def address: String

  def value: Float

  def unit: String
}
case class TimedValue(override val timestamp: DateTime, override val address: String, override val value: Float, override val unit: String) extends SensorValue
case class TemperatureValue(override val timestamp: DateTime, override val address: String, override val value: Float, override val unit: String = "°C") extends SensorValue
