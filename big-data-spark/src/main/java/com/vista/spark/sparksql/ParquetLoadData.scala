package com.vista.spark.sparksql

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

/** 加载parquet数据源数据
  *
  * @author WenTingTing by 2020/6/8
  */
object ParquetLoadData {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local").setAppName("ParquetLoadData")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)
    val dataFrame = sqlContext.read.load("D://idea//workspace//vista-drill//big-data-spark//test-file//users.parquet")
    dataFrame.registerTempTable("student")
    val student = sqlContext.sql("select name from student")
    student.show()
    student.collect().foreach(f => {
      println("name:" + f.getAs[String]("name"))
    })


    val df = sqlContext.read.load("file:///extend_data/wen/buffer_load/part")
    df.registerTempTable("event")
    val insert = sqlContext.sql("INSERT OVERWRITE TABLE game_ods_back.event PARTITION (app='h5_12mlcs',dt='2020-06-08',event='event_app.booting_0') select *  FROM event")
    insert.show()


    df.write.format("parquet").mode("append").option("path","hdfs://skuldcdhtest1.ktcs:8020/user/hive/warehouse/game_ods_back.db/event/app=h5_12mlcs/dt=2020-06-08/event=event_app.booting_0/")


  }

}
