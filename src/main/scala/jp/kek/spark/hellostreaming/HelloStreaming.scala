package jp.kek.spark.hellostreaming

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.streaming.Seconds
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.storage.StorageLevel
import org.apache.log4j.Level
import org.apache.log4j.Logger

object HelloStreaming {
  def main(args: Array[String]): Unit = {
    Logger.getRootLogger.setLevel(Level.WARN)

    val sc = new SparkContext(new SparkConf().setAppName("Hello Streaming"))
    val ssc = new StreamingContext(sc, Seconds(10))

    val lines = ssc.socketTextStream(args(0), args(1).toInt,
                                     StorageLevel.MEMORY_AND_DISK_SER)
    val words = lines.flatMap(_.split(" ")).filter(_.nonEmpty)
    val wordCounts = words.map((_, 1)).reduceByKey(_ + _)
    wordCounts.print()
    ssc.start()
    ssc.awaitTermination()
  }
}
