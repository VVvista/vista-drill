package com.vista.spark.sparkstreaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/** 热点搜索词滑动统计，每隔10秒钟，统计最近60秒钟的搜索词的搜索频次，并打印出排名最靠前的3个搜索词以及出现次数
  *说明： sparkstreaming中可以拥有同时对 batch 、Windows的操作，但必须都要有output操作出发，否则不执行
  * 但Windows只在滑动间隔时间上触发执行
  * @author Wen TingTing by 2020/6/13
  */
object WindowHotWord {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("WindowHotWord").setMaster("local[2]")
    val streamingContext = new StreamingContext(conf, Seconds(5))
    val log = streamingContext.socketTextStream("localhost", 9999)
    val record =log.flatMap(f=>f.split(" ")).map( (_, 1))

    // batch 额外操作：
    val batch=record.map((_,"batch"))

    val windows = record.reduceByKeyAndWindow((v1: Int, v2: Int) => v1 + v2, Seconds(60), Seconds(10))
    val sort = windows.transform(rdd => {
      rdd.sortBy(f => f._2).take(3).foreach(println(_))
      rdd
    }) // dstream不管是window、batch类型，必须使用output 操作出发，否则不会执行
    //测试1：dstream：windows、sort必须使用output操作出发，否则不会执行
    // record.print()

    batch.print()
    sort.print()

    streamingContext.start()
    streamingContext.awaitTermination()


  }
}
