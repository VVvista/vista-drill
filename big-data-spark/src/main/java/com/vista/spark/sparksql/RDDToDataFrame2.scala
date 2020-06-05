package com.vista.spark.sparksql

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.types.{DataType, DataTypes, StructField, StructType}
import org.apache.spark.sql.{Row, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author WenTingTing by 2020/6/5
  */
object RDDToDataFrame2 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("DataFrameConstruct").setMaster("local")
    val sc = new SparkContext(conf)
    val textRDD = sc.textFile("D://idea//workspace//vista-drill//big-data-spark//test-file//student.txt")
    // 第一步，构造出元素为Row的普通RDD
    val studentRDD = textRDD.map(f => {
      val strings = f.split(" ")
      Row(Integer.valueOf(strings(0)), strings(1), Integer.valueOf(strings(2)))
    })

    for (elem <- studentRDD.collect()) {
      println(elem)
    }

    val sqlContext = new SQLContext(sc)
    // 第二步，编程方式动态构造元数据
    val structType = StructType(Array(
      StructField("id", DataTypes.IntegerType, true),
      StructField("name", DataTypes.StringType, true),
      StructField("age", DataTypes.IntegerType, true)
    ))


    // 第三步，进行RDD到DataFrame的转换
    val studentDF = sqlContext.createDataFrame(studentRDD, structType)

    studentDF.show()
    studentDF.printSchema()

    val student: RDD[Row] = studentDF.rdd

    student.foreach(f => {
      println(f.get(0) + " "
        + f.getAs[String](1) + " "
        + f.getAs[String]("id") + " "
        + f.getInt(2)
      )
    })
  }

}
