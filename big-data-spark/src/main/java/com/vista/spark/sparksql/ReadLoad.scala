package com.vista.spark.sparksql

import org.apache.spark.sql.{DataFrame, SQLContext, SaveMode}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author WenTingTing by 2020/6/5
  */
object ReadLoad {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("DataFrameConstruct").setMaster("local")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    /* // 示例1：
    //  val df = sqlContext.read.load("D://idea//workspace//vista-drill//big-data-spark//test-file//users.parquet")
       val df = sqlContext.read.load("D://idea//workspace//vista-drill//big-data-spark//test-file//result.parquet")
        df.show()
        df.write.mode(SaveMode.Append).save("D://idea//workspace//vista-drill//big-data-spark//test-file//result.parquet")
     */

    /*
        // 示例2：
        val df = sqlContext.read.format("json").load("D://idea//workspace//vista-drill//big-data-spark//test-file//students.json")
        df.show()
        df.select("name").write.save("D://idea//workspace//vista-drill//big-data-spark//test-file//result.txt")
    */

    /*    // 示例3：
        val df = sqlContext.read.format("json").load("D://idea//workspace//vista-drill//big-data-spark//test-file//students.json")
        df.show()
        df.select("name").write.format("json").mode("overwrite").save("D://idea//workspace//vista-drill//big-data-spark//test-file//result.txt")
      */

    /*    // 示例4：
        val df = sqlContext.read.json("D://idea//workspace//vista-drill//big-data-spark//test-file//students.json")
        df.show()
        df.write.json("D://idea//workspace//vista-drill//big-data-spark//test-file//result.json")
        */

    // 示例5：
    val df = sqlContext.jsonFile("D://idea//workspace//vista-drill//big-data-spark//test-file//students.json")
    df.show()

  }
}
