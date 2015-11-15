package com.orbinista.smarthome

import com.datastax.driver.core.{ProtocolOptions, Session, Cluster}

trait CassandraTestCluster extends CassandraCluster {

  def getConnection: Cluster = {
    Cluster.builder().addContactPoint("172.16.7.2").withCompression(ProtocolOptions.Compression.SNAPPY).withPort(9042).build()
  }

  lazy val cluster: Cluster =
    Cluster.builder().addContactPoint("172.16.7.2").withCompression(ProtocolOptions.Compression.SNAPPY).withPort(9042).build()

}

