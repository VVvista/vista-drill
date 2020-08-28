package com.vista.spark.structuredstreaming

import org.apache.spark.sql.SparkSession
import org.apache.spark.storage.StorageLevel

/**
  * @author WenTingTing by 2020/8/27
  */
object DataSetBasicOperation {

  case class Deptment(id: Long, name: String)

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .master("local")
      .appName("DataSetBasicOperation")
      .getOrCreate()

    import spark.implicits._
    val deptmentDF = spark.read.json("file:///extend_data/wen/hive/department.json")
    // cache() 、persist()
    // storage level (`MEMORY_AND_DISK`)
    deptmentDF.cache()
    deptmentDF.persist(StorageLevel.MEMORY_AND_DISK_SER_2)

    deptmentDF.unpersist()

    // explain
    deptmentDF.createOrReplaceTempView("dep")
    spark.sql("select * from dep where id > 30").explain()

    // schema
    deptmentDF.printSchema()

    // write
    deptmentDF.write.json("dep.json")

    // df->ds
    val ds = deptmentDF.as[Deptment]

    // ds-> df
    ds.toDF()


  }
}
