package zkdemo

import org.I0Itec.zkclient.ZkClient
import org.I0Itec.zkclient.serialize.{BytesPushThroughSerializer, SerializableSerializer}
import org.apache.zookeeper.ZooDefs.Ids
import org.apache.zookeeper.{CreateMode, ZooDefs, ZooKeeper}

/**
  * Created by 2017/10/31.
  *
  * @author rongpei
  */
object ReadZK {


  def main(args: Array[String]): Unit = {
    //val zkclient = new ZkClient("cift1.zookeeper.dc.puhuifinance.com:2181")

    val zkclient = new ZkClient("10.10.56.138:2181");


    zkclient.setZkSerializer(new BytesPushThroughSerializer())


    println(zkclient.exists("/puhui/nbsp/cif/mongosinkoffset"))



    val list =  zkclient.getChildren("/puhui/nbsp/cif/mongosinkoffset")
     list.toArray.map{
        s =>
          println(s)
          println(new java.lang.String(zkclient.readData("/puhui/nbsp/cif/mongosinkoffset/"+s.toString), "utf-8"))
     }


//    if(!zkclient.exists("/puhui/nbsp/cif/zktest"))
//    zkclient.createPersistent("/puhui/nbsp/cif/zktest","data".getBytes)

    //获取 节点中的对象
    //println(new java.lang.String(zkclient.readData("/puhui/nbsp/cif/zktest/MongoOplogAnalyzer_nirvana"), "utf-8"))
   }


}
