package jdk8Threas;

import java.util.concurrent.*;

/**
 * Created by 2017/9/27.
 *
 * @author rongpei
 */
public class RunnableDemo {
     public static void main(String args[]){
         Runnable task = () -> {
             String threadName = Thread.currentThread().getName();
             System.out.println("Hello " + threadName);
         };
         System.out.println("-------");
         task.run();


         Thread thread = new Thread(task);
         thread.start();

         System.out.println("Done!");



         Runnable runnable = () -> {
             try {
                 String name = Thread.currentThread().getName();
                 System.out.println("Foo " + name);
                 TimeUnit.SECONDS.sleep(1);
                 System.out.println("Bar " + name);
             }
             catch (InterruptedException e) {
                 e.printStackTrace();
             }
         };

         Thread thread1 = new Thread(runnable);
         thread1.start();


         ExecutorService executor = Executors.newSingleThreadExecutor();
         Future<Integer> future =  executor.submit(() -> {
             String threadName = Thread.currentThread().getName();
             System.out.println("Hello " + threadName);
             return 1;
         });



        }
}
