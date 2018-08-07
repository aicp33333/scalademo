package kafkademo

import java.util
import java.util.Map
import java.util.Properties

import org.apache.flink.streaming.connectors.kafka.internals.KafkaTopicPartition
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.TopicPartition

import scala.collection.JavaConversions._

/**
  *   ${todo}
 *
  * @author rongpei
  *  2018/5/25
  */
object KafkaScalaConsumer {
  def main(args: Array[String]): Unit = {

    val topic = "tagSpDdl"
    val bootstrap = "daasinfo1.kafka.dc.puhuifinance.com:6667"
    val zookeeper = "daasinfo1.zookeeper.dc.puhuifinance.com:2181"

    val props = new Properties()
    props.put("bootstrap.servers", bootstrap)
    props.put("zookeeper.connect", zookeeper)
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    //props.put("group.id", "cdp21local1")
    props.put("auto.offset.reset","earliest")  //latest earliest
    props.put("auto.commit.enable","true")

    val consumer = new KafkaConsumer[String, String](props)

    //    val m = new java.util.HashMap[TopicPartition, java.lang.Long]()
    //    m.put(new TopicPartition(topic,0),1519801200000L)
    //    m.put(new TopicPartition(topic,1),1519801200000L)
    //    m.put(new TopicPartition(topic,2),1519801200000L)
    //    consumer.offsetsForTimes(m)

    consumer.subscribe(util.Collections.singleton(topic))



    while (true) {
      val records = consumer.poll(500)
      for (record <- records) {
         println(record.key())
      }
    }


  }
}
