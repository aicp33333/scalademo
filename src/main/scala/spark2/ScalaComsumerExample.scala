package spark2

import java.util.{Collections, Properties}

import org.apache.kafka.clients.consumer.KafkaConsumer
import scala.collection.JavaConversions._

/**
  * Created by rongpei on 2017/9/19.
  */
object ScalaComsumerExample {
  def main(args: Array[String]): Unit = {
    val topic = "SrcMongoDataPiSDK"
    val brokers = "10.10.31.31:6667"
    val zookeeper = "10.10.31.31:2181"
    val props = new Properties()
    props.put("bootstrap.servers", brokers)
    props.put("zookeeper.connect",zookeeper)
    //props.put("client.id", "ScalaProducerExample")
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("group.id","rong123")
    props.put("auto.offset.reset","earliest")
    val consumer = new KafkaConsumer[String, String](props)

    consumer.subscribe(Collections.singleton(topic))
    val records = consumer.poll(1000)
    println("---------")
    for (record <- records){
      println(record.key())
    }
    consumer.close()
  }

}
