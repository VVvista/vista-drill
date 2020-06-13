package com.vista.spark.sparkstreaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable

/**
  * @author WenTingTing by 2020/6/11
  */
object KafkaReceiverWordCount {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("KafkaReceiverWordCountScala").setMaster("local[2]")
    val streamingContext = new StreamingContext(conf, Seconds(10))

    val zkQuorum = "hadoop-100:2181,hadoop-101:2181,hadoop-102:2181"
    val group = "DefaultConsumerGroup"
    val topics = "WordCount"
    val numThreads = 1

    // 方式1：封装多个topic
    val topicThreadMap = new mutable.HashMap[String, Integer]()
    topicThreadMap.put("WordCount", 1) // 使用多少个线程去拉取topic的数据

    // 方式2：封装多个topic
    val topicMap = topics.split(",").map((_, numThreads.toInt)).toMap
    // stream-kafka新版本中没有此KafkaUtils.createStream方法
    // val lines = KafkaUtils.createStream(streamingContext, zkQuorum, group, topicpMap).map(_._2)

    /*
     val words = lines.flatMap(line => line.split(" "))
     val wordsNumber = words.map(word => (word, 1))
       val result = wordsNumber.reduceByKey(_ + _)

       result.print()*/
    streamingContext.start()
    streamingContext.awaitTermination()
  }
}
