package kafkademo

import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
object KafkaProducerDemo {
  def main(args: Array[String]) {

    /* ***********************************************************************************************************
     * input parameter
     */
    val topic = "cdp22"
    val brokers = "10.10.56.138:9092"
    val zookeeper = "10.10.56.138:2181"

//    val messagesKey =
//      """{"appName":"source-to-kafka","appServerIp":"192.168.136.184","appServerPort":8451,"syncTaskId":"TaskNirvanaMongo1","syncTaskStartTime":1503374966815,"syncTaskSequence":4934710,"sourceType":"oplog","dbName":"holmes_index","tableName":"LocationInfoWrapper","msgUuid":"6a8bea2c920e2689","msgSyncStartTime":1507940761034,"msgSyncEndTime":1507940761347,"dbEffectTime":1507940761000,"msgSyncUsedTime":312,"msgSize":1785,"kafkaTopic":"SrcMonTaskNirvanaHolmesIndex","kafkaPartition":-1,"kafkaOffset":-1,"eventType":"d","clusterId":null,"mongoOpsUuid":7677487466933003913,"mongoTsSecond":1507940761,"mongoTsInc":3,"instanceId":0}"""
//    val messageValue =
//      """{"_id":"rp-test12312312312312321","_class":"com.puhui.holmes.index.calc.input.devices.LocationInfoWrapper","half500ApplyTimes":0,"oneDay500ApplyTimes":0,"oneDay2000ApplyTimes":0,"twoDay2000ApplyTimes":0,"errorCode":"00000","result":[{"_id":"m#ddb0e2e5a988475fa9d05d14556555a4","address":"河南省郑州市中牟县康庄路靠近阜外医院(建设中)","city":"郑州市","country":"中国","district":"中牟县","idNumBiz":"410482198303152511","insertTime":{"$date":"2017-10-12T04:18:58.000Z"},"latitude":"34.781459","longitude":"113.859949","pId":"862699034343943","phase":"apply","phone":"15638902832","province":"河南省","src":"322","updateTime":{"$date":"2017-10-12T04:18:58.000Z"},"requestId":"322201710128966191","applyNo":"322201710128966191","_class":"com.puhui.holmes.index.calc.input.devices.LocationInfo"}],"idNo":"410482198303152511","channelCode":"bairong","requestId":"322201710128966191","applyId":"1194228","userId":"30314154","ct":{"$date":"2017-10-14T00:26:01.276Z"},"bt":{"$date":"2017-10-12T04:18:58.000Z"},"syncJsonKey":"{\"appName\":\"source-to-kafka\",\"appServerIp\":\"192.168.136.184\",\"appServerPort\":8451,\"syncTaskId\":\"TaskNirvanaMongo1\",\"syncTaskStartTime\":1503374966815,\"syncTaskSequence\":4934710,\"sourceType\":\"oplog\",\"dbName\":\"holmes_index\",\"tableName\":\"LocationInfoWrapper\",\"msgUuid\":\"6a8bea2c920e2689\",\"msgSyncStartTime\":1507940761034,\"msgSyncEndTime\":1507940761346,\"dbEffectTime\":1507940761000,\"msgSyncUsedTime\":312,\"msgSize\":0,\"kafkaTopic\":\"SrcMonTaskNirvanaHolmesIndex\",\"kafkaPartition\":-1,\"kafkaOffset\":-1,\"eventType\":\"d\",\"clusterId\":null,\"mongoOpsUuid\":7677487466933003913,\"mongoTsSecond\":1507940761,\"mongoTsInc\":3}"}"""

    val messagesKey =
      """
        |{"dbEffectTime":1524630248472,"dbName":"datapi","syncTaskId":"crawlersSdk1","tableName":"alipay_banklist"
      """.stripMargin
    val messageValue =
      """
        |{"batchNo":"5ae002e35f1503000d664a4f","src":"106","msgId":"d7abcdf2f78442e88e5203cf7d139b34","updateTime":1524630248168,"batchTime":1524630243000,"idNumBiz":"xy26aca80bfc8bb72ef467214d48853669536a5f3d83a7a3cc36337202c8098cdc20160926","isIdNumBizDS":0,"insertTime":1524630248168,"expireTime":1524630248168,"idNumBizDS":"310************25","applyNo":"106201804252023745","fetchTime":1524630248000,"files":["https://api.puhuifinance.com/datapi-api-filesystem/api/v1/file/getFile?path=G12%2FM00%2F79%2F77%2FChONkFrgAuiAdIHRAAAIN3Hdhpc02.html"],"details":[{"bank":"中国光大银行","mobilePhone":"137****7624","openState":"已开通","cardType":"信用卡","applyTime":"2017.10.10","userName":"肖昫","cardNumber":"1095"},{"bank":"中国工商银行","mobilePhone":"137****7624","openState":"已开通","cardType":"信用卡","applyTime":"2017.05.03","userName":"肖昫","cardNumber":"7077"},{"bank":"中建设银行","mobilePhone":"137****7624","openState":"已开通","cardType":"储蓄卡","applyTime":"2015.11.02","userName":"肖昫","cardNumber":"9548"},{"bank":"上海银行","mobilePhone":"137****7624","openState":"已开通","cardType":"信用卡","userName":"肖昫","cardNumber":"4598"},{"bank":"招银行","mobilePhone":"137****7624","openState":"已开通","cardType":"信用卡","applyTime":"2017.05.15","userName":"肖昫","cardNumber":"1437"},{"bank":"交通银行","mobilePhone":"137****7624","openState":"已开通","cardType":"信用卡","applyTime":"2017.06.09","userName":"肖昫","cardNumber":"9682"},{"bank":"中信银行","mobilePhone":"137****7624","openState":"已开通","cardType":"信用卡","userName":"肖昫","cardNumber":"9888"},{"bank":"中信银行","openState":"已开通","cardType":"信用卡","applyTime":"2018.01.05","userName":"肖昫","cardNumber":"9622"}],"id":"5ae002e85de409b4e9507090","alipayId":"2088202433593292","hasDataPK":true,"parseTime":1524630248000,"group":"2088202433593292"}
      """.stripMargin
    /* ***********************************************************************************************************
     * kafka producer parameter
     */
    val props = new Properties()
    props.put("bootstrap.servers", brokers)
    props.put("acks", "all")
    props.put("serializer.class", "org.apache.kafka.common.serialization.ByteArraySerializer")
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArraySerializer")
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArraySerializer")
    val producer: KafkaProducer[Array[Byte], Array[Byte]] = new KafkaProducer[Array[Byte], Array[Byte]](props)

    /* ***********************************************************************************************************
     * messages data send
     */
    val record = new ProducerRecord[Array[Byte], Array[Byte]](topic, messagesKey.getBytes(), messageValue.getBytes())
    producer.send(record)
    producer.close()
    println(s"messages is : $record")

  }
}
