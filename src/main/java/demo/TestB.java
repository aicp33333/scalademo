package demo;

import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 2017/12/7.
 *
 * @author rongpei
 */
public class TestB {

    public static void main(String args[]){


//        System.out.println(computeDuration("1时50分23"));
//        System.out.println(computeDuration("1时50分32秒"));
//        System.out.println(computeDuration("1时分"));
//        System.out.println(computeDuration("1时0分秒"));
//        System.out.println(computeDuration("时123"));
//        System.out.println(computeDuration("1时50分"));


          Map<String,String> map = new HashMap<>();
          map.put("b1","1");
          map.put("a2","2");
          map.put("a3","3");
          map.put("a4","4");
          map.put("a5","5");
          map.put("a6","6");

         Set<String> set =  map.keySet();

         for (String str : set ){
             System.out.println(map.get(str)+"--"+str);
         }


       }
    private static final Pattern ExtractSecond=Pattern.compile("(([\\d]+)(小时|时|:))?(([\\d]+)(分钟|分|:))?(([\\d]+)(秒)?)?");

    /**
     * 计算通话时间到秒
     * @param input 输入的通话记录, 例如:1小时6分49秒
     * @return 以秒计, 例如:4009   大于19550置为0
     */
    public static int computeDuration(String input) {
        if (StringUtils.isEmpty(input)) {
            return 0;
        }
        //判断是否为纯数字,如果为纯数字，当做秒返回
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(input);
        if (isNum.matches()) {
            int num=Integer.parseInt(input);
            if(num>19550)
                num=0;
            return num;
        }

        Matcher matcher=ExtractSecond.matcher(input);
        int result=0;
        if(matcher.find()){
            if(matcher.group(2)!=null){
                String hours=matcher.group(2);
                result+=Integer.parseInt(hours)*3600;
            }
            if(matcher.group(5)!=null){
                String hours=matcher.group(5);
                result+=Integer.parseInt(hours)*60;
            }
            if(matcher.group(8)!=null){
                String hours=matcher.group(8);
                result+=Integer.parseInt(hours);
            }
        }
        if(result>19550)
            result=0;
        return result;
    }
}
