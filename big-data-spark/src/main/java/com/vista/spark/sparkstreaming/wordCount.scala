package com.vista.spark.sparkstreaming

import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * @author WenTingTing by 2020/6/10
  */
object wordCount {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("wordCount").setMaster("local[2]")
    val sc = new SparkContext(conf)
    //  val streamingContext = new StreamingContext(conf, Seconds(10))
    val streamingContext = new StreamingContext(sc, Seconds(10))

    val lines: ReceiverInputDStream[String] = streamingContext.socketTextStream("localhost", 9999)
    val words: DStream[String] = lines.flatMap(f => f.split(" "))
    val wordNumbers: DStream[(String, Int)] = words.map((_, 1))
    val result: DStream[(String, Int)] = wordNumbers.reduceByKey(_ + _)
    result.print()

    streamingContext.start() // 线程处于堵塞状态
    streamingContext.awaitTermination()
    streamingContext.stop(false)

    /*    result.count().print() // 不会继续往下执行
        streamingContext.start()*/
       val line: ReceiverInputDStream[String] = streamingContext.socketTextStream("localhost", 29999)
        line.print()
    streamingContext.start() // 线程处于堵塞状态
    streamingContext.awaitTermination()
    streamingContext.stop()
  }
}
