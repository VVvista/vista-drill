package com.vista.spark.structuredstreaming

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.elasticsearch.spark._

/**
  * @author WenTingTing by 2020/8/27
  */
object SparkToEs {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("app1").setMaster("local[2]")
    conf.set("es.index.auto.create", "true") //在spark中自动创建es中的索引

    conf.set("es.nodes", "192.168.220.128") //设置在spark中连接es的url和端口

    conf.set("es.port", "9200")
    conf.set("es.nodes.wan.only", "true")
    val spark = SparkSession.builder().config(conf).getOrCreate()
    val df = spark.read.format("org.elasticsearch.spark.sql")
      .option("inferSchema", "true")
      .load("index/type")

    val rdd = df.rdd
    rdd.saveToEs("index/type")
  }

}
