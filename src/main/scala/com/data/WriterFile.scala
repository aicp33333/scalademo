package com.data

import java.io.{FileWriter, PrintWriter}

/**
  * Created by rongpei on 2017/5/31.
  */
object WriterFile {

  def main(args: Array[String]): Unit = {
    val writer = new PrintWriter(new FileWriter("/Users/rongpei/work/tmp/test.txt",true))
    var idx =0
    while (1 == 1){
      Thread.sleep(1000)
      idx += 1
      writer.println("Hello Scala "+idx)
      writer.flush()
    }

    writer.close()
  }

}
