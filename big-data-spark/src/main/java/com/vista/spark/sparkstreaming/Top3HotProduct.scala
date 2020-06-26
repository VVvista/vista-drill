package com.vista.spark.sparkstreaming

import org.apache.spark.SparkConf
import org.apache.spark.sql.types.{DataTypes, StructField, StructType}
import org.apache.spark.sql.{Row, SQLContext}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/** 每隔10秒，统计最近60秒的，每个种类的每个商品的点击次数，然后统计出每个种类top3热门的商品。
  *
  * @author Wen TingTing by 2020/6/13
  */
object Top3HotProduct {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("Top3HotProduct").setMaster("local[2]")
    val streamingContext = new StreamingContext(conf, Seconds(3))
    val log = streamingContext.socketTextStream("localhost", 9999) // log: phone huawei/phone mi
    val record = log.map((_, 1))
    //reduceByKeyAndWindow  默认进行持久化：MEMORY_ONLY_SER
    val window = record.reduceByKeyAndWindow((v1: Int, v2: Int) => v1 + v2, Seconds(60), Seconds(6))
    window.foreachRDD(rdd => {// foreachRDD中rdd的操作也必须有action算子触发，否则不会执行
      val row = rdd.map(f => {
        val str = f._1.split(" ")
        Row(str(0), str(1), f._2)
      })

      val sqLContext = new SQLContext(streamingContext.sparkContext)
      val structType = StructType(Array(
        StructField("type", DataTypes.StringType, true),
        StructField("name", DataTypes.StringType, true),
        StructField("num", DataTypes.IntegerType, true)
      ))

      val frame = sqLContext.createDataFrame(row, structType)
      frame.registerTempTable("log")
     val result= sqLContext.sql(s"select type,name,num from " +
        "(select type,name,num,row_number() over(partition by type,name order by num desc ) rank from log ) a" +
        "where a.rank <=3")
      result.show()
    })

    streamingContext.start()
    streamingContext.awaitTermination()

  }
}
