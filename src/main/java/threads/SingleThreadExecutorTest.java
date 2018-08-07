package threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author rongpei
 * @create 2017/9/26
 **/
public class SingleThreadExecutorTest {

     public static void main(String args[]){
         ExecutorService executor = Executors.newSingleThreadExecutor();

         executor.submit(() -> {
             String threadName = Thread.currentThread().getName();
             System.out.println("Hello " + threadName);
         });
     }
}
