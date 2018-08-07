package zkdemo

import java.util.concurrent.CountDownLatch

import org.apache.zookeeper.Watcher.Event.KeeperState
import org.apache.zookeeper.ZooDefs.Ids
import org.apache.zookeeper.{CreateMode, WatchedEvent, Watcher, ZooKeeper}

/**
  * Created by 2017/12/13.
  *
  * @author rongpei
  */
object ZookeeperD {

  def main(args: Array[String]): Unit = {
    val connSignal = new CountDownLatch(1)
    try {

     val zkclient = new ZooKeeper("10.10.56.138:2181",20000,new Watcher {
        override def process(event: WatchedEvent): Unit = {

          if (event.getState eq KeeperState.SyncConnected)
            connSignal.countDown
        }
      })
      connSignal.await

      println(zkclient.exists("/puhui/nbsp/cif/mongosinkoffset/testcdp",false))
      println(new java.lang.String(zkclient.getData("/puhui/nbsp/cif/mongosinkoffset/testcdp" ,true, null)))
    } catch {
      case e: Exception => println(e.getMessage)
    }

  }

}
