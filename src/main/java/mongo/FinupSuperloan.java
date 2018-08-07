package mongo;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by 2018/1/27.
 *
 * @author rongpei
 */
public class FinupSuperloan {
     public static void main(String args[]) throws ParseException, IOException {
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
         SimpleDateFormat sdf1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", java.util.Locale.US);

         FileWriter fw = new FileWriter("/Users/rongpei/decision_data_remote_log.txt",true);


         MongoDBCollection mdc =mdc("daas","$yIa*rQY!9p@bzpb",
                 "admin","192.168.156.73:28017");
         BasicDBObject delDbo=new BasicDBObject();
         sdf.parse("2018-02-06 14:00:00.000");
         delDbo.append("create_time",new BasicDBObject("$gt",sdf.parse("2018-05-26 00:00:00.000")));
         List<DBObject> list= mdc.use("finup_superloan").getCollection("decision_data_remote_log")
                 .find(delDbo).toArray();//.sort(new BasicDBObject("loan_applyinfo_id",-1)).toArray();

         for (DBObject db : list) {

            String loan_applyinfo_id = db.get("loan_applyinfo_id").toString();
            String remote_result = db.get("remote_result").toString();
            String create_time = db.get("create_time").toString();

            JSONObject remote = JSONObject.parseObject(remote_result);
            long beginTime = remote.getLongValue("beginTime");

            String param = remote.getString("param");
            if(param.equals("{}")){
             String result = getresultMap(remote.getString("resultMap"));
             create_time = sdf.format(sdf1.parse(create_time));
             fw.write(loan_applyinfo_id+","+create_time+","+sdf.format(new Date(beginTime))+","+result+"\n");
             System.out.println(loan_applyinfo_id+","+sdf.format(new Date(beginTime))+","+result);
            }
         }

        fw.flush();
        }


        public static String getresultMap(String result){

         JSONObject jsonObject = JSONObject.parseObject(result);
         Set<String> set=jsonObject.keySet();
            Map<String,String> map = new HashMap<>();
         StringBuffer sb = new StringBuffer();
            set.forEach(key ->
                    {
                        map.put(key,jsonObject.getString(key));
                        sb.append(key+":"+jsonObject.getString(key)+",");
                    }
            );
         return sb.toString();
        }


    public static MongoDBCollection mdc(String mongoUserName,String mongoPassword,String mongoDatabase,String mongoAddressList) {
        return  new MongoDBCollection(mongoUserName, mongoPassword, mongoDatabase, mongoAddressList) ;
    }
}
