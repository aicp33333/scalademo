package mongo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.bson.Document;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by 2018/1/25.
 *
 * @author rongpei
 */
public class MongoQueryData {

    static List<String> list = new ArrayList<>();

    static {
        list.add("79582");
        list.add("79583");
        list.add("79584");
        list.add("79585");
        list.add("79586");
        list.add("79587");
        list.add("79588");
        list.add("79589");
        list.add("79590");
        list.add("79591");
        list.add("79592");
        list.add("79593");
        list.add("79594");
        list.add("79595");
        list.add("79596");
        list.add("79597");
        list.add("79598");
        list.add("79599");
        list.add("79600");
        list.add("79601");
        list.add("79602");
        list.add("79603");
        list.add("79604");
        list.add("79605");
        list.add("79606");
        list.add("79607");
        list.add("79608");
        list.add("79609");
        list.add("79610");
        list.add("79611");
        list.add("79612");
        list.add("79613");
        list.add("79614");
        list.add("79615");
        list.add("79616");
        list.add("79617");
        list.add("79618");
        list.add("79619");
        list.add("79620");
        list.add("79621");
        list.add("79622");
        list.add("79623");
        list.add("79624");
        list.add("79625");
        list.add("79626");
        list.add("79627");
        list.add("79628");
        list.add("79629");
        list.add("79630");
        list.add("79631");
        list.add("79632");
        list.add("79633");
        list.add("79634");
        list.add("79635");
        list.add("79636");
        list.add("79637");
        list.add("79638");
        list.add("79639");
        list.add("79640");
        list.add("79641");
        list.add("79642");
        list.add("79643");
        list.add("79644");
        list.add("79645");
        list.add("79646");
        list.add("79647");
        list.add("79648");
        list.add("79649");
        list.add("79650");
        list.add("79651");
        list.add("79652");
        list.add("79653");
        list.add("79654");
        list.add("79655");
        list.add("79656");
        list.add("79657");
        list.add("79658");
        list.add("79659");
        list.add("79660");
        list.add("79661");
        list.add("79662");
        list.add("79663");
        list.add("79664");
        list.add("79665");
        list.add("79666");
        list.add("79667");
        list.add("79668");
        list.add("79669");
        list.add("79670");
        list.add("79671");
        list.add("79672");
        list.add("79673");
        list.add("79674");
        list.add("79675");
        list.add("79676");
        list.add("79677");
        list.add("79678");
        list.add("79679");
        list.add("79680");
        list.add("79681");
        list.add("79682");
        list.add("79683");
        list.add("79684");
        list.add("79685");
        list.add("79686");
        list.add("79687");
        list.add("79688");
        list.add("79689");
        list.add("79690");
        list.add("79691");
        list.add("79692");
        list.add("79693");
        list.add("79694");
        list.add("79695");
        list.add("79696");
        list.add("79697");
        list.add("79698");
        list.add("79699");
        list.add("79700");
        list.add("79701");
        list.add("79702");
        list.add("79703");
        list.add("79704");
        list.add("79705");
        list.add("79706");
        list.add("79707");
        list.add("79708");
        list.add("79709");
        list.add("79710");
        list.add("79711");
        list.add("79712");
        list.add("79713");
        list.add("79714");
        list.add("79715");
        list.add("79716");
        list.add("79717");
        list.add("79718");
        list.add("79719");
        list.add("79720");
        list.add("79721");
        list.add("79722");
        list.add("79723");
        list.add("79724");
        list.add("79725");
        list.add("79726");
        list.add("79727");
        list.add("79728");
        list.add("79729");
        list.add("79730");
        list.add("79731");
        list.add("79732");
        list.add("79733");
        list.add("79734");
        list.add("79735");
        list.add("79736");
        list.add("79737");
        list.add("79738");
        list.add("79739");
        list.add("79740");
        list.add("79741");
        list.add("79742");
        list.add("79743");
        list.add("79744");
        list.add("79745");
        list.add("79746");
        list.add("79747");
        list.add("79748");
        list.add("79749");
        list.add("79750");
        list.add("79751");
        list.add("79752");
        list.add("79753");
        list.add("79754");
        list.add("79755");
        list.add("79756");
        list.add("79757");
        list.add("79758");
        list.add("79759");
        list.add("79760");
        list.add("79761");
        list.add("79762");
        list.add("79763");
        list.add("79764");
        list.add("79765");
        list.add("79766");
        list.add("79767");
    }
    static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
     public static void main(String args[]) throws ParseException,IOException {
         MongoDBCollection mdc =mdc("daas","$yIa*rQY!9p@bzpb",
                 "admin","192.168.156.73:28017");
         FileWriter fw = new FileWriter("/Users/rongpei/0525问题.txt",true);
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
         List<String> date = new ArrayList<>();

         BasicDBObject delDbo=new BasicDBObject();
         for (String user: list) {
             System.out.println(user);
         delDbo.append("loan_applyinfo_id",Integer.parseInt(user));
//         delDbo.append("create_time",new BasicDBObject("$gt",sdf.parse("2018-05-26 00:00:00.000")));
//             DBCursor dbo = mdc.use("finup_superloan").getCollection("decision_data_remote_log").find(delDbo);
             DBCursor dbo = mdc.use("finup_superloan").getCollection("decision_data_remote_log").find(delDbo);
             Iterator<DBObject> it= dbo.iterator();

             while (it.hasNext()){
                 DBObject db = it.next();
                 String  loan_applyinfo_id = db.get("loan_applyinfo_id").toString();
                 String time = sdf.format((Date)db.get("create_time"));
                 String remote_result = db.get("remote_result").toString();
                 JSONObject array = JSONObject.parseObject(remote_result).getJSONObject("resultMap");
                 String str= JSON.toJSONString(array,SerializerFeature.WriteMapNullValue);
                 date.add("进件号："+loan_applyinfo_id+":"+str + "," +time);
             }


         }


         Collections.sort(date);

         for (String str:date){
             fw.write(str);
             fw.write("\r\n");
         }

         fw.flush();
         fw.close();

     }
    public static MongoDBCollection mdc(String mongoUserName,String mongoPassword,String mongoDatabase,String mongoAddressList) {
        return  new MongoDBCollection(mongoUserName, mongoPassword, mongoDatabase, mongoAddressList) ;
    }
}
