package com.vista.spark.sparkstreaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * @author WenTingTing by 2020/6/12
  */
object UpdateStateByKeyWordCount {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[2]").setAppName("UpdateStateByKeyWordCount")

    val streamingContext = new StreamingContext(conf, Seconds(10))

    streamingContext.checkpoint("D://idea//workspace//vista-drill//big-data-spark//test-file//checkpoint")

    val lines = streamingContext.socketTextStream("localhost", 9999)
    val words = lines.flatMap(f => f.split(" "))
    val wordsNum = words.map((_, 1))
    val result = wordsNum.updateStateByKey((values: Seq[Int], state: Option[Int]) => {
      var newValue = state.getOrElse(0) // 获取key之前的状态值
      for (value <- values) {
        newValue += 1 // 叠加key的状态值
      }
      Option(newValue) // 返回key最新的状态值
    }
    )
    result.print()
    streamingContext.start()
    streamingContext.awaitTermination()

  }
}
