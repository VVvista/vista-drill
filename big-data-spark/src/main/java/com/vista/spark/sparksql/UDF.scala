package com.vista.spark.sparksql

import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.catalyst.expressions.SpecificInternalRow
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author WenTingTing by 2020/6/8
  */
object UDF {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("UDFScala").setMaster("local")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    val name = Array("Feng Xiangbin", "Zhao Jun", "Spark", "Hadoop")
    val nameRDD = sc.parallelize(name)
    import sqlContext.implicits._
    val df = nameRDD.toDF("name")
    df.registerTempTable("name")
    sqlContext.udf.register("strlen", (f: String) => f.length)
    sqlContext.sql("select name ,strlen(name) from name").show()
    new SpecificInternalRow()


  }
}
