package com.vista.spark.scala

import java.text.SimpleDateFormat

import com.obs.services.{ObsClient, ObsConfiguration}
import com.obs.services.model.{ListObjectsRequest, ObjectListing}

import scala.collection.mutable.TreeSet

/** 校验obs游戏日期中文件的上传时间
  * https://support.huaweicloud.com/sdk-android-devg-obs/obs_26_0603.html
  *
  * @author WenTingTing by 2020/7/8
  */
object ObsClientFit {
  val obsClient: ObsClient = {
    val config = new ObsConfiguration
    config.setEndPoint("https://obs.cn-north-1.myhuaweicloud.com")
    new ObsClient("MAOYFCLRAJUTY2M3GZHR", "kmuxVl6DmbhAkmQb3rzz5LvRSNO29KV4bFl7qPfo", config)
  }
  val bucketName = "pro.skuld.service.obs"

  // val formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
  val formatter = new SimpleDateFormat("yyyy-MM-dd HH")

  /**
    * 主方法
    *
    * @param args
    */
  def main(args: Array[String]): Unit = {

    val prefix = "/user/hive/warehouse/game_ods.db/event/app=h5_12mlcs/dt=2019"

    //  /user/hive/warehouse/game_ods.db/event/app=gm_3qdcs/dt=2020-07-07/event=event_role.login_1/part-00000-9a71264c-cfc9-4701-be58-32a7937526f8-c000

    keysFit(prefix)
  }

  def keysFit(prefix: String): Unit = {
    val listObjectsRequest = new ListObjectsRequest(bucketName)
  //   listObjectsRequest.setMaxKeys(10000000)
    listObjectsRequest.setPrefix(prefix)

    //  val objects = obsClient.listObjects(listObjectsRequest).getObjects.iterator()
    // val keys = mutable.HashMap[String, String]()
    //  val sets = mutable.TreeSet[String]((o1, o2) -> o2.compareTo(o1))
    val sets = new TreeSet[String]()(new Ordering[String] {
      override def compare(x: String, y: String): Int = x.compareTo(y)
    })

    var result: ObjectListing = null

    do {
      result = obsClient.listObjects(listObjectsRequest)
      println(result.getObjects.size())
      val objects = result.getObjects.iterator()
      while (objects.hasNext) {
        val obj = objects.next()
        // key即为具体的文件路径
        val key = obj.getObjectKey
        val time = obj.getMetadata.getLastModified
        if (key.startsWith(prefix)) {
          val dateString = formatter.format(time)
          //    keys.put(dateString,key.split("/")(6)+":"+key.split("/")(7))
          //   keys.put(dateString + ":" + key.split("/")(7), "1")
          sets.add(dateString + ":" + key.split("/")(7))
        }
      }
      listObjectsRequest.setMarker(result.getNextMarker)
    } while ( {
      result.isTruncated
    })
    /*    keys.foreach(println(_))
        println(keys.size)*/

    sets.foreach(println(_))
    println(sets.size)

  }

}
