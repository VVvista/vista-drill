package com.vista.spark.sparkstreaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * @author WenTingTing by 2020/6/11
  */
object HDFSWordCount {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[2]").setAppName("HDFSWordCountScala")
    val streamingContext = new StreamingContext(conf, Seconds(10))
    val lines = streamingContext.textFileStream("hdfs://skuldcdhtest1.ktcs:8020//user/hive/warehouse/wen_test.db/php_device/")
    val words = lines.flatMap(f => f.split(" "))
    val num = words.map(f => (f, 1))
    val result = num.reduceByKey(_ + _)
    result.print()
    streamingContext.start()
    streamingContext.awaitTermination()
  }

}
