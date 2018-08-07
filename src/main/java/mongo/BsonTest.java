package mongo;
import com.alibaba.fastjson.JSONArray;
import com.mongodb.BasicDBObject;

import java.util.Scanner;
import com.mongodb.util.JSON;
/**
 * @author rongpei
 * @Description: ${todo}
 * @date 2018/5/9
 */
public class BsonTest {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println(JSON.parse(str).getClass().getName());
        //BasicDBObject obj= (BasicDBObject)JSON.parse(str);


        System.out.println(JSONArray.parseArray(JSON.parse(str).toString()));
    }
}
