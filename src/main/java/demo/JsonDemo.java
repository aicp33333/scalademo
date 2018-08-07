package demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jayway.jsonpath.JsonPath;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;


/**
 * Created by 2017/12/8.
 *
 * @author rongpei
 */
public class JsonDemo {
     public static void main(String args[]){
       String s  = "{ \"store\": {\"book\": [ { \"category\": \"reference\",\"author\": \"Nigel Rees\",\"title\": \"Sayings of the Century\",\"price\": 8.95,\"test\":[{\"price\":33.444}]},{ \"category\": \"fiction\",\"author\": \"Evelyn Waugh\",\"title\": \"Sword of Honour\",\"price\": 12.99,\"isbn\": \"0-553-21311-3\"}],\"bicycle\": {\"color\": \"red\",\"price\": 19.95}}}";
         JSONObject json = JSON.parseObject(s);
         String author = JsonPath.read(json, "$.store.book[0].author");

         System.out.println(author);


         //输出全部author的值，使用Iterator迭代
         List<String> authors = JsonPath.read(json, "$.store.book[*].author");

         //输出book[*]中category == 'reference'的book
         List<Object> books = JsonPath.read(json, "$.store.book[?(@.category == 'reference')]");

         //输出book[*]中price>10的book
         List<Object> books3 = JsonPath.read(json, "$.store.book[?(@.price>10)]");

         //输出book[*]中含有isbn元素的book
         List<Object> books2 = JsonPath.read(json, "$.store.book[?(@.isbn)]");

         //输出该json中所有price的值
         List<BigDecimal> prices = JsonPath.read(json, "$..price");

       for (BigDecimal bd: prices) {
         System.out.println(bd);
       }

        if(!prices.isEmpty()){
          System.out.println(prices.get(0));
        }


         //可以提前编辑一个路径，并多次使用它
         JsonPath path = JsonPath.compile("$.store.book[*]");
         List<Object> books1 = path.read(json);



         String str = "{ \"_id\" : { \"$oid\" : \"59fb38bfcdabb40001c5ade9\" }, \"wealth\" : { \"fund\" : 0, \"huabai_limit\" : 0, \"huabai_balance\" : 0, \"yeb\" : 0, \"mapping_id\" : \"8490731547621480517\", \"yue\" : 200, \"zcb\" : 0, \"cjb\" : 0, \"taolicai\" : 0 }, \"user_id\" : \"d2528d3812a98a829f4d206ac1f3f735\", \"tradeinfo\" : [{ \"trade_number\" : \"2017051221001004870227609181\", \"incomeorexpense\" : \"支出\", \"trade_time\" : \"2017-05-12T16:39:00.000+08\", \"trade_amount\" : 99.5, \"mapping_id\" : \"8490731547621480517\", \"trade_status\" : \"交易成功\", \"counterparty\" : \"中国移动通信有限公司\", \"capital_status\" : \"已支出\", \"product_name\" : \"话费\" }, { \"trade_number\" : \"2017050821001004870220893935\", \"incomeorexpense\" : \"支出\", \"trade_time\" : \"2017-05-08T19:01:00.000+08\", \"trade_amount\" : 18.0, \"mapping_id\" : \"8490731547621480517\", \"trade_status\" : \"交易成功\", \"counterparty\" : \"蒸好吃\", \"capital_status\" : \"已支出\", \"product_name\" : \"蒸好吃-奇顺美食城店支付码：1297\" }, { \"trade_number\" : \"20170508200040011100870005563317\", \"incomeorexpense\" : \"\", \"trade_time\" : \"2017-05-08T19:00:00.000+08\", \"trade_amount\" : 20.0, \"mapping_id\" : \"8490731547621480517\", \"trade_status\" : \"交易成功\", \"counterparty\" : \"苗笑伟\", \"capital_status\" : \"资金转移\", \"product_name\" : \"充值-普通充值\" }], \"task_id\" : \"e74512ea-bfe1-11e7-bcca-00163e12d150\", \"userinfo\" : { \"taobao_id\" : \"testfinup1112\", \"idcard_number\" : \"130921***********8\", \"alipay_userid\" : \"2088422531206870\", \"user_name\" : \"苗笑伟\", \"mapping_id\" : \"8490731547621480517\", \"certified\" : true, \"phone_number\" : \"13522133509\", \"email\" : \"\", \"register_time\" : \"2016-07-24T00:00:00.000+08\" }, \"create_time\" : { \"$date\" : \"2017-11-02T15:24:47.871Z\" }, \"syncJsonKey\" : \"{\\\"appName\\\":\\\"source-to-kafka\\\",\\\"appServerIp\\\":\\\"192.168.176.12\\\",\\\"appServerPort\\\":8302,\\\"syncTaskId\\\":\\\"TaskMoxieMongo1\\\",\\\"syncTaskStartTime\\\":1511231638028,\\\"syncTaskSequence\\\":15303,\\\"sourceType\\\":\\\"oplog\\\",\\\"dbName\\\":\\\"finup_datacenter\\\",\\\"tableName\\\":\\\"moxie_alipay_data\\\",\\\"msgUuid\\\":\\\"74b35ca348c5677f\\\",\\\"msgSyncStartTime\\\":1511231829907,\\\"msgSyncEndTime\\\":1511231829907,\\\"dbEffectTime\\\":1509636287000,\\\"msgSyncUsedTime\\\":0,\\\"msgSize\\\":0,\\\"kafkaTopic\\\":\\\"MongoFinupDatacenterMg1\\\",\\\"kafkaPartition\\\":-1,\\\"kafkaOffset\\\":-1,\\\"eventType\\\":\\\"i\\\",\\\"clusterId\\\":null,\\\"mongoOpsUuid\\\":8409166785601169279,\\\"mongoTsSecond\\\":1509636287,\\\"mongoTsInc\\\":3}\" }";


         JSONObject json1 = JSON.parseObject(str);

         List<Object> register_time = JsonPath.read(json1, "$..register_time");
         System.out.println(register_time.size());
         if(!register_time.isEmpty()){
             System.out.println(register_time.get(0));
         }

     }
}
