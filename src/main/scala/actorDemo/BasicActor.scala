package actorDemo

import akka.actor.Actor
import akka.event.LoggingReceive

/**
  * Created by 2017/9/21.
  *
  * @author rongpei
  */
class BasicActor extends Actor{
  override def preStart() ={
    println(this.sender().path)
    println("Inside the preStart method of BasicLifecycleLoggingActor")
  }
  override def receive = LoggingReceive{

    case "hello" => println("---hello----")
    case "hi" => println("---hi----")
  }
}
