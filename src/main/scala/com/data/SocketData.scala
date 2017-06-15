package com.data

import java.io.PrintWriter
import java.net.ServerSocket

/**
  * Created by rongpei on 2017/5/31.
  */
object SocketData {
  def main(args: Array[String]): Unit = {
    val listener = new ServerSocket(9999)
    var idx =0
    while (true) {
      val socket = listener.accept()
      idx +=1
      new Thread() {
        override def run = {
          println("Got client connected from: " + socket.getInetAddress)
          val out = new PrintWriter(socket.getOutputStream(), true)
          while (true) {
            Thread.sleep(1000)
            val content = "Hello Scala Hello spark "+idx
            println(content)
            out.write(content + '\n')
            out.flush()
          }
          socket.close()
        }
      }.start()
    }
  }

}
