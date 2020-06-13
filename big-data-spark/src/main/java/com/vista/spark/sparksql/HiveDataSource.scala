package com.vista.spark.sparksql

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{SQLContext, SparkSession}

/**
  * @author WenTingTing by 2020/6/8
  */
object HiveDataSource {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("ParquetLoadData")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    val students = sqlContext.read.json("D://idea//workspace//vista-drill//big-data-spark//test-file//students.json")
    students.show()


    // 创建sparkSession
    val spark = SparkSession.builder().appName("Spark Hive Example").enableHiveSupport().getOrCreate()

    //1： sql()直接对hive表进行操作
    spark.sql("CREATE TABLE IF NOT EXISTS src (name STRING, age STRING) ")
    spark.sql("drop table if exists src").explain(true)
    // 2： 创建DataFrame，写入表
    val studentInfo = Array("{\"name\":\"Leo\", \"age\":18}", "{\"name\":\"Marry\", \"age\":17}", "{\"name\":\"Jack\", \"age\":19}")
    val studentInfoRDD = sc.parallelize(studentInfo)
    val studentInfoDF = sqlContext.read.json(studentInfoRDD)
    studentInfoDF.write.saveAsTable("src") // saveAsTable讲解见notepad++


    // 3. 读取表数据
     spark.table("src").show()

    studentInfoDF.write.partitionBy("key").format("hive").saveAsTable("hive_part_tbl")// 前提：表分区必须存在，此处会报错



    spark.sql("select dt from game_ods.event where  dt='2019-07-01' and event='event_app.booting_0' and app='h5_12mlcs' ").explain(true)
    spark.sql("select dt from game_ods.event where  dt='2019-07-01' and event='event_app.booting_0' and app='h5_12mlcs' ").registerTempTable("dt")
  }
}
