package com.vista.spark.sparksql

import org.apache.spark.sql.{DataFrame, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author WenTingTing by 2020/6/4
  */
object DataFrameConstruct {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("DataFrameConstruct").setMaster("local")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)
    //  val df: DataFrame = sqlContext.read.json("file:///extend_data/wen/buffer_load/students.json")
    //  val df: DataFrame = sqlContext.read.json("D://idea//workspace//vista-drill//big-data-saprk//test-file//students.json")
    val df: DataFrame = sqlContext.read.format("json").load("D://idea//workspace//vista-drill//big-data-saprk//test-file//students.json")


    /*   df.show();
       df.show(1);*/
    //  df.select("name", "age").show()
    //   df.select(df.col("name"), df.col("age").+(1)).show()
    df.select(df.col("name"), df.col("age")).where(df.col("age").gt(18)).show()
    //    df.select(df.col("name"), df.col("age").+(1).alias("Age")).show()
    df.filter(df("age") >= 18)

    // df.select(df.col("name"), df.col("age").+(1).alias("moreAge")).where(df("age").geq(18)).show()

    //df.groupBy("name").sum("age").show()

    df.registerTempTable("student")

    sqlContext.sql("select * from student where age>18").show()
  }

}
