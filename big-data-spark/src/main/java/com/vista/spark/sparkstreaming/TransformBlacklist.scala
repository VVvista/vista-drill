package com.vista.spark.sparkstreaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * @author WenTingTing by 2020/6/12
  */
object TransformBlacklist {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("KafkaReceiverWordCountScala").setMaster("local[2]")
    val streamingContext = new StreamingContext(conf, Seconds(10))

    val sc = streamingContext.sparkContext
    // 黑名单
    val blacklist = sc.parallelize(Array(("tom", true)))

    val adsClickLogDStream = streamingContext.socketTextStream("localhost", 9999)
    val userAdsClickLogDStream = adsClickLogDStream.map(s => (s.split(" ")(1), s))
    // transform算子进行rdd之间的join操作
    val validAdsClickLogRDD = userAdsClickLogDStream.transform(userAdsClickLogRDD => {
      val joinRDD = userAdsClickLogRDD.leftOuterJoin(blacklist)
      val filterRDD = joinRDD.filter(f => {
        if (f._2._2.getOrElse(false)) false
        else true
      })
      filterRDD.map(f => f._2._1)

    })

    userAdsClickLogDStream.checkpoint(Seconds(10))

    validAdsClickLogRDD.print()

    streamingContext.start()
    streamingContext.awaitTermination()
  }
}
