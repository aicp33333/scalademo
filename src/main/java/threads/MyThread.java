package threads;


/**
 * Created by rp on 16-12-2.
 */
public class MyThread extends Thread {
     public void run(){
         for(int i =0 ;i <100 ;i++)
         System.out.println("MyThread.run()"+getName()+"正在打印"+i);
     }

     public static void main(String[] args){
         String name = Thread.currentThread().getName();
         System.out.println("开始执行"+name);


         for(int i =0 ;i <5 ;i++){
             new MyThread().start();
         }

//         MyThread myThread = new MyThread();
//         myThread.start();
//         MyThread myThread1 = new MyThread();
//         myThread1.start();
         for(int i =0 ;i <100 ;i++)
             System.out.println("线程"+name+"正在打印"+i);


         System.out.println("执行完毕"+name);
     }
}
