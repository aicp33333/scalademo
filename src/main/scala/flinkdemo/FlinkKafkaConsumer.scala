package flinkdemo

import java.util.Properties

import org.apache.flink.api.java.ExecutionEnvironment
import org.apache.flink.streaming.api.scala.{StreamExecutionEnvironment, _}
import org.apache.flink.streaming.api.scala.DataStream
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010
import org.apache.flink.streaming.util.serialization.{KeyedDeserializationSchema, SerializationSchema, SimpleStringSchema}

/**
  * Created by 2017/11/1.
  *
  * @author rongpei
  */
object FlinkKafkaConsumer {

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    val properties = new Properties();

    properties.setProperty("bootstrap.servers", "10.10.56.138:9092");
    // only required for Kafka 0.8
    properties.setProperty("zookeeper.connect", "10.10.56.138:2181");
    properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    properties.put("group.id","rong123")
    properties.put("auto.offset.reset","earliest")

    val stream : DataStream[String] = env.addSource(
      new FlinkKafkaConsumer010[String](
        "rptest", new SimpleStringSchema(), properties)
    )

    stream.map{
      x =>
        println(x)
    }


  }

}
