package exception;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author rongpei
 * @Description: ${todo}
 * @date 2018/7/28
 */
public class ThreadTest {

    public static void test(String s){
        System.out.println(s);
    }

    public static void main(String[] args){
        Queue bsons = new LinkedBlockingQueue<String>(100);


        ThreadPoolUtils.getMongoThreadPoolExecutor().execute(()->{
                test("1");
        });


    }
}
