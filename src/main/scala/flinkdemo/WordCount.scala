package flinkdemo

import org.apache.flink.api.scala._

/**
  * Created by rongpei on 2017/9/14.
  */
object WordCount {

  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.getExecutionEnvironment

    // get input data
    val text  = env.fromElements[String]("To be, or not to be,--that is the question:--")


    val counts = text.flatMap { _.toLowerCase.split("\\W+") }
      .map { (_,1) }
      .groupBy(0)
      .sum(1)



    counts.print()
  }

}
