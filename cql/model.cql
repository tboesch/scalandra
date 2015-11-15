CREATE KEYSPACE envdata WITH REPLICATION = {'class' : 'SimpleStrategy', 'replication_factor' : 1};

# 2014-09-02 18:21:14,01:8B:F3:4A,22.6,°C

USE envdata ;


# partition key enocean_id => one row per sensor
# clustering key timestamp => columns within row are ordered by timestamp

DROP TABLE IF EXISTS sensors;
CREATE TABLE sensors (
   enocean_id text,
   timestamp timestamp,
   unit text,
   value float,
   PRIMARY KEY (enocean_id, timestamp)
) WITH CLUSTERING ORDER BY (timestamp DESC)
;


# partition key date => one row per day
# clustering key timestamp => columns within row are ordered by timestamp
# clustering key enocean_id => support values from each sensor at each possible timestamp

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
