package com.vista.spark.sparksql

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext

/**
  * @author WenTingTing by 2020/6/8
  */
object ParquetMergeSchema {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("ParquetLoadData")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)


    import sqlContext.implicits._
    val squaresDF = sc.makeRDD(1 to 5).map(i => (i, i * i)).toDF("value", "square")
    squaresDF.write.mode("overwrite").parquet("hdfs://skuldcdhtest1.ktcs:8020//user/hive/warehouse/wen_test/users/schema/key=1")

    val cubesDF = sc.makeRDD(6 to 10).map(i => (i, i * i * i)).toDF("value", "cube")
    cubesDF.write.mode("overwrite").parquet("hdfs://skuldcdhtest1.ktcs:8020//user/hive/warehouse/wen_test/users/schema/key=2")


    val cubesDF2 = sc.makeRDD(6 to 10).map(i => (i, i * i * i)).toDF("value", "cube")
    cubesDF2.write.mode("append").parquet("hdfs://skuldcdhtest1.ktcs:8020//user/hive/warehouse/wen_test/users/schema/key=1")

    // 相同分区字段
    var mergedDF = sqlContext.read.option("mergeSchema", "true").parquet("hdfs://skuldcdhtest1.ktcs:8020//user/hive/warehouse/wen_test/users/schema/")

    mergedDF.printSchema()

    /**
      * root
      * |-- value: integer (nullable = true)
      * |-- square: integer (nullable = true)
      * |-- cube: integer (nullable = true)
      * |-- key: string (nullable = true)
      */


    mergedDF.show()

    /**
      * +-----+------+----+---+
      * |value|square|cube|key|
      * +-----+------+----+---+
      * |    3|     9|null|  1|
      * |    4|    16|null|  1|
      * |    5|    25|null|  1|
      * |    8|  null| 512|  2|
      * |    9|  null| 729|  2|
      * |   10|  null|1000|  2|
      * |    1|     1|null|  1|
      * |    2|     4|null|  1|
      * |    6|  null| 216|  2|
      * |    7|  null| 343|  2|
      * +-----+------+----+---+
      *
      */


    mergedDF = sqlContext.read.option("mergeSchema", "true").parquet("hdfs://skuldcdhtest1.ktcs:8020//user/hive/warehouse/wen_test/users/schema/key=1/")

    // 运行报错： 若路径向下存在分区，则必须两者具有相同的分区，否则报错
    mergedDF = sqlContext.read.option("mergeSchema", "true").parquet("hdfs://skuldcdhtest1.ktcs:8020//user/hive/warehouse/wen_test/users/schema/key=1/",
      "hdfs://skuldcdhtest1.ktcs:8020//user/hive/warehouse/wen_test/users/schema/")

    // 运行成功：合并的数据文件必须具有相同的分区字段，具有相同的分区key
    mergedDF = sqlContext.read.option("mergeSchema", "true")
      .option("basePath", "hdfs://skuldcdhtest1.ktcs:8020//user/hive/warehouse/wen_test/users/schema/")
      .parquet("hdfs://skuldcdhtest1.ktcs:8020//user/hive/warehouse/wen_test/users/schema/key=1/",
        "hdfs://skuldcdhtest1.ktcs:8020//user/hive/warehouse/wen_test/users/schema/")

    // 无分区字段
    mergedDF = sqlContext.read.option("mergeSchema", "true").parquet("hdfs://skuldcdhtest1.ktcs:8020//user/hive/warehouse/wen_test/users/schema/key=1/"
      , "hdfs://skuldcdhtest1.ktcs:8020//user/hive/warehouse/wen_test/users/gender=male/country=china/")

    // 不能运行,不再同一个分区表中
    mergedDF = sqlContext.read.option("mergeSchema", "true")
      .option("basePath", "hdfs://skuldcdhtest1.ktcs:8020//user/hive/warehouse/wen_test/users/")
      .parquet("hdfs://skuldcdhtest1.ktcs:8020//user/hive/warehouse/wen_test/users/key=1/"
        , "hdfs://skuldcdhtest1.ktcs:8020//user/hive/warehouse/wen_test/users/gender=male/country=china/")


    //不能运行：必须在同一个分区表中
    /*    For partitioned table directories, data files should only live in leaf directories.
          And directories at the same level should have the same partition column  name.
          Please check the following directories for unexpected files or inconsistent partition column names
          */
    mergedDF = sqlContext.read.option("mergeSchema", "true").
      option("basePath", "hdfs://skuldcdhtest1.ktcs:8020//user/hive/warehouse/wen_test/users/").
      parquet("hdfs://skuldcdhtest1.ktcs:8020//user/hive/warehouse/wen_test/users/key=1/",
        "hdfs://skuldcdhtest1.ktcs:8020//user/hive/warehouse/wen_test/users/gender=male/")

    // 不能运行
    //If provided paths are partition directories, please set "basePath" in the options of the data source to specify the root directory of the table.
    // If there are multiple root directories,
    // please load them separately and then union them.
    mergedDF = sqlContext.read.option("mergeSchema", "true").
      parquet("hdfs://skuldcdhtest1.ktcs:8020//user/hive/warehouse/wen_test/users/key=1/",
        "hdfs://skuldcdhtest1.ktcs:8020//user/hive/warehouse/wen_test/users/gender=male/")

    mergedDF.printSchema()


    mergedDF = sqlContext.read.option("mergeSchema", "true").parquet("hdfs://skuldcdhtest1.ktcs:8020//user/hive/warehouse/wen_test/users/date=20200608/",
      "hdfs://skuldcdhtest1.ktcs:8020//user/hive/warehouse/wen_test/users/gender=male/")

  }
}
