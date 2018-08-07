package actorDemo

import akka.actor.{ActorSystem, Props}

import scala.util.Try

/**
  * Created by 2017/9/21.
  *
  * @author rongpei
  */
object ActorDemo {

  def main(args: Array[String]): Unit = {
    val actorSystem=ActorSystem("ActorSystem")
//    val actor1 = actorSystem.actorOf(Props[BasicActor],"actor1")
//    val actor2 = actorSystem.actorOf(Props[BasicActor],"actor2")
     var idx =0
    try {
      while (true) {
        idx += 1
        val actor = actorSystem.actorOf(Props[BasicActor], s"myactor$idx")
        actor ! "hi"

      }
    } catch {
      case _ => println("---------"+idx)
    }

    println(actorSystem.settings.config)



  }

}
