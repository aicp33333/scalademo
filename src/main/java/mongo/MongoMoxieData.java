package mongo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author rongpei
 * @Description: ${todo}
 * @date 2018/5/26
 */
public class MongoMoxieData {

    static List<String> list = new ArrayList<>();

    static {
        list.add("e16b7f52a9d0823b612ec42a6a50b590");
        list.add("7d953d887faf4d7d5e1fc58fca66e5d2");
        list.add("11e3422937b5634f3b1f3f5d1428fdec");
        list.add("876951fa270e0a9563286ce36100cae8");
        list.add("36466d967b0c5761a733a919b4628c43");
        list.add("e6a326c3faa86a8558f336527cc3b9e1");
        list.add("b1486559a9c14cfc15d81d9ed12c852d");
        list.add("6efa683f99a6fbe00d97526ae464a35c");
        list.add("085cb52bb8203dbcc994d5f2a9bf0144");
        list.add("ea777f07de50d9edc085b9ffd18f9fb0");
        list.add("64bb812121191a4d4eb0cc8a3ba339ac");
        list.add("606d6a894ae355c436214be4d81dec80");
        list.add("b2e8f07ea0322bca677885b3c04b789e");
        list.add("ce4de3b8676a2018614bbbd6db18ac1b");
        list.add("4d97b33d95a9ba95dc6df8385ef0d67d");
        list.add("020299f03597ae2166abc7f3af63bdd4");
        list.add("4edcde398a05a578d23000edd0b66c79");
        list.add("8410cc319d606c115ee2050522be8db5");
        list.add("347950f02f2df1da22593786739d335f");
        list.add("5f224297de96317e09ae7fb99decd804");
        list.add("e60bf1ffa6f80427a39d6814aee4cd01");
        list.add("147fe3cdbb8b13be3c3a61b5a7fc545c");
        list.add("f69764591c2ddc8494cdc43a0a642bf4");
        list.add("4e822e2b47d2e50ccee555067f873b05");
        list.add("c3783f2c5982a378e655d6983a9a7f45");
        list.add("09688637dbc1bf714485234a19b9ceef");
        list.add("1020aeac538bd63753fb82b441e04675");
        list.add("b9926d8e2a964ae49fb48833279b2240");
        list.add("450d78c2dde5703609c4e7df73faeae1");
        list.add("7810bbf83a867bb54ab082a28e6e4ad6");
        list.add("6755be624790f2777997b309709756f3");
        list.add("02d96751dc1bf517cc974ad44344ed34");
        list.add("099d00234c7312d599ed90eb2f00be1c");
        list.add("82d2d798ac22a09c708ff5404bea26a2");
        list.add("0901a6ae131be09930ac7b6985c083ac");
        list.add("b53b9cec1bdd06b96f80827acbbe5895");
        list.add("561e379f12fe1b1779726cf7027f0b48");
        list.add("d98c075647fe767c7953bd4816cfed3b");
        list.add("a764030c301d42c96ca7988aa72b38a4");
        list.add("95ec20fea1ccc0578cbe94d6c0ebbcd6");
        list.add("94707cf196537d950e5ebafdba1b9989");
        list.add("830a3b69926b737ffaf5e92b7248e9e6");
        list.add("0af3529f3ac585f20083f9d920f72616");
        list.add("6d9ec40d8dde199e01221c349344782c");
        list.add("8f46a8c8f4c21f7c01204be4babda38c");
        list.add("83656337f890811626eaad7eab78192d");
        list.add("0999d1c45ee4bad564ae3c95fc90186f");
        list.add("fd7f10a6b472f27dfe275394d9687b31");
        list.add("741bc725014b7816d9d542a5f3be49f5");
        list.add("4dbee5e4378f8e929e9a51edb7cb2e48");
        list.add("15194538631788ceaeb75ebccf157419");
        list.add("922afcdf4e96848737dfd73de847717a");
        list.add("a7cd0b16afac35550b2f53ef91ea8ec7");
        list.add("d5763d9282dbde005feb26cc92f16878");
        list.add("c8c513536772c3975b300f08d0ab7934");
        list.add("7087beaa0303db2ee60659669b812b7a");
        list.add("1ac2e31d365711136260afeb0b773de7");
        list.add("823c11fb99b17d6df9bc6ec6c15ca9fd");
        list.add("733a1e2413af465e889e9932df2c5c1b");
    }

    static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public static void main(String args[]) throws ParseException,IOException {
        MongoDBCollection mdc =mdc("daas","JdKRblezE^!qAyGL",
                "admin","192.168.156.72:27017");
        FileWriter fw = new FileWriter("/Users/rongpei/mobileDataTime.txt",true);

        List<String> date = new ArrayList<>();

        BasicDBObject delDbo=new BasicDBObject();
        for (String user: list) {


           Set<String> tableNames=  mdc.use("finup_datacenter").getCollectionNames();

            System.out.println(tableNames.size());
             int idx = 0;
            for (String tableName: tableNames ) {
                idx += 1;
                if (!tableName.equals("system.profile") && tableName.contains("mobile")){
                    System.out.println("table11:" + tableName + ",idx:" + idx);
                delDbo.append("user_id", user);

//                DBCursor dbo = mdc.use("finup_datacenter").getCollection(tableName).find(delDbo);
//                System.out.println(user + "--" + tableName);
//                Iterator<DBObject> it = dbo.iterator();
//                while (it.hasNext()) {
//                    DBObject db = it.next();
//                    System.out.println((sdf.format((Date) db.get("create_time"))));
//                    String time = sdf.format((Date) db.get("create_time"));
//                    date.add("user_id:" + user + ",tableName:" + tableName + ",数据落库时间：" + time);
//                }
                  int count=  mdc.use("finup_datacenter").getCollection(tableName).find(delDbo).count();

                    date.add("user_id:" + user + ",tableName:" + tableName + ",条数：" + count);

             }
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
