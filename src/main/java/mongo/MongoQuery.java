package mongo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.mongodb.DBObject;

import com.puhui.aes.AesEncryptionUtil;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.bson.types.Binary;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;

/**
 *
 * @author rongpei
 * @create 2017/9/27
 **/
public class MongoQuery {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // ES客户端
    private static TransportClient client = null;
    private String keyName = "esPrimaryKey";
    // 从什么位置取
    private static int from = 0;
    // 取多少条目
    private static int size = 10000;
    public static void main(String[] args) throws ParseException {
        String esIp ="10.10.213.187:9300,10.10.240.231:9300,10.10.242.225:9300";
        //MongoDBCollection mdc =mdc("daas","JdKRblezE^!qAyGL","admin","192.168.156.72:27017");
        MongoDBCollection mdc =mdc("cif","J4DU6EHNxXcHg4&k","admin","192.168.156.34:27017");

        System.out.println(mdc.use("finup_decision"));

        DBObject cur = mdc.use("finup_decision").getCollection("fs.chunks").findOne();

        System.out.println(cur.get("data"));


//        System.out.println(dbo.get("create_time"));
//
//        String calls =dbo.get("calls").toString();
//
//        JSONArray array= JSONArray.parseArray(calls);
//
//        Iterator<Object>  it =array.iterator();
//
//        while (it.hasNext()){
//            String items = it.next().toString();
//
//            String jsonObject =JSONObject.parseObject(items).getString("items");
//
//            JSONArray arr= JSONArray.parseArray(jsonObject);
//            Iterator<Object>  it1 =arr.iterator();
//            while (it1.hasNext()){
//                String str =it1.next().toString();
//                //System.out.println(str);
//                JSONObject jo = JSONObject.parseObject(str);
//                System.out.println(jo.getString("peer_number")+","+jo.getString("duration")+","+
//                 jo.getString("dial_type")+","+jo.getString("time"));
//            }
//
//        }

        System.out.println(AesEncryptionUtil.decrypt("210112199103311022") );
        System.out.println(AesEncryptionUtil.encrypt("210112199103311022") );


        getInstance("bdpculstert1.es.dc.puhuifinance.com",esIp);

        Map<String, String> params = new HashMap<>();
        //params.put("cfType", "basic_info"); // cfType es中的
//        Iterator<DBObject> it = cur.iterator();
//        while (it.hasNext()) {
//            DBObject obj = it.next();
//            String _id = obj.get("_id").toString();
//            String idNo = obj.get("idCardNum").toString();
//            String applyId = obj.get("applyNo") != null ? obj.get("applyNo").toString() : "";
//
//            String idCardNum = "";
//            params.put("idCardNum", idCardNum);
        System.out.println(AesEncryptionUtil.encrypt("14222519930716001X"));
            params.put("idCardNum", AesEncryptionUtil.encrypt("14222519930716001X"));
            //params.put("user_id","d2528d3812a98a829f4d206ac1f3f735");
           long idx = 0;
           // while(idx==0){
                List<String> list =  fieldMatchQuery("cif", "indexFeatureToHbaseRowkey", params, null);

//                for (String str : list){
//                    JSONObject jsonObject= JSON.parseObject(str);
//                    if(jsonObject.getLongValue("insertDate")-idx>0){
//                        idx =jsonObject.getLongValue("insertDate");
//                    }
//                    System.out.println(str);
//                }

                System.out.println(idx);


                System.out.println(list.size());
          //  }



//        }

    }
    public static MongoDBCollection mdc(String mongoUserName,String mongoPassword,String mongoDatabase,String mongoAddressList) {
        return  new MongoDBCollection(mongoUserName, mongoPassword, mongoDatabase, mongoAddressList) ;
    }

    public static  void getInstance(String esClusterName, String ipAndPorts) {

//            Settings settings = Settings.settingsBuilder().put("cluster.name", esClusterName).build();
//            client = TransportClient.builder().settings(settings).build();
//
//            for (String address : ipAndPorts.split(",")) {
//                String[] tmp = address.split("\\:");
//                try {
//                    client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(tmp[0]), Integer.parseInt(tmp[1])));
//                } catch (NumberFormatException e) {
//                    e.printStackTrace();
//                } catch (UnknownHostException e) {
//                    e.printStackTrace();
//                }
//            }

    }

    public static List<String> fieldMatchQuery(String indexName, String typeName, Map<String, String> params,
                                        QueryBuilders filterBuilder) {
        BoolQueryBuilder qb = QueryBuilders.boolQuery();

        QueryBuilder r = qb;
        if (params != null)
            for (Map.Entry<String, String> e : params.entrySet()) {
                qb.must(QueryBuilders.matchQuery(e.getKey(), replaceValue(e.getValue())));
                //qb.must(QueryBuilders.boolQuery()
                //		.should(QueryBuilders.matchPhraseQuery(e.getKey(), replaceValue(e.getValue()))));
            }

        return query(indexName, typeName, r);
    }

    public static List<String> query(String indexName, String typeName, QueryBuilder qb) {
        // 构建一个搜索请求
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(indexName).setTypes(typeName);
        // 设置字段名称和字段值
        searchRequestBuilder.setQuery(qb);
        searchRequestBuilder.setFrom(from);
        searchRequestBuilder.setSize(size);
        searchRequestBuilder.setSearchType(SearchType.DFS_QUERY_THEN_FETCH);
        searchRequestBuilder.setExplain(true);

        // 查询
        SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();
        // 把命中的数据放入集合中
        List<String> result = new ArrayList();
        SearchHits hits = searchResponse.getHits();
        for (int i = 0; i < hits.getHits().length; i++) {
            result.add(hits.getHits()[i].getSourceAsString());
        }
        return result;
    }
    public static String replaceValue(String value) {
        return QueryParser.escape(value);
    }
}
