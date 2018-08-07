package threads;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by 2018/5/3.
 *
 * @author rongpei
 */
public class FutureTest {

    static class Task implements Callable<Boolean>{
        public String name;
        public int time;
        public Task(String s,int t){
            name =s;
            time =t;
        }

        public Boolean call() throws ClassNotFoundException {
            String namet = Thread.currentThread().getName();


            Class.forName("com.mysql.jdbc.Driver");
            try {
                Connection conn= DriverManager.getConnection("jdbc:mysql://127.0.0.1/wherehows?useUnicode=true&characterEncoding=utf-8&useSSL=false", "root","123456");
                Statement stat= conn.createStatement();
                stat.setQueryTimeout(2);
                stat.execute("select count(*),sleep(4) from dict_dataset");

                System.out.println("----------");
            } catch (SQLException e) {
                System.out.println(name+" sql is interrupted when calculating, will stop...");
                e.printStackTrace();
                return false;  //注意这里如果不return的话，线程还会继续执行，所以任务超时后在这里处理结果然后返回
            } catch (Exception e) {
                System.out.println(name+" is interrupted when calculating, will stop...");
                return false;  //注意这里如果不return的话，线程还会继续执行，所以任务超时后在这里处理结果然后返回
            }

//            for(int i=0;i<time;++i){
//                System.out.println(namet+"task "+name+" round "+(i+1));
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    System.out.println(name+" is interrupted when calculating, will stop...");
//                    return false;  //注意这里如果不return的话，线程还会继续执行，所以任务超时后在这里处理结果然后返回
//                }
//            }
            return true;
        }
    }

    public static void main(String[] args) {
       // ExecutorService executor= Executors.newCachedThreadPool();


        ThreadPoolExecutor task = new ThreadPoolExecutor(3,5,60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(8));
        final List<Future> threadList = new ArrayList<Future>();
        int idx = 10;
//        for (int i=0;i<10;++i){
//            Task task1=new Task("one", idx-1);
//            Future<Boolean> f1= task.submit(task1);
//            threadList.add(task.submit(task1));
//        }


        Task task1=new Task("one", 10);
        System.out.println("执行开始时间："+System.currentTimeMillis());
        Future<Boolean> f= task.submit(task1);

        try{
            if(f.get(1,TimeUnit.SECONDS)){
                System.out.println("完成");
            }
        }catch (InterruptedException e) {
            System.out.println("future在睡着时被打断");
            task.shutdownNow();
        } catch (ExecutionException e) {
            System.out.println("future在尝试取得任务结果时出错");
            task.shutdownNow();
        } catch (TimeoutException e) {
            System.out.println("future时间超时");
            System.out.println("线程关闭时间开始："+System.currentTimeMillis());
            Boolean lean= f.cancel(true);
            System.out.println(lean);
            System.out.println(f.isCancelled());
            System.out.println("线程关闭时间结束："+System.currentTimeMillis());
            //executor.shutdownNow();
            //executor.shutdown();
        }finally{

            task.shutdownNow();

        }



        for (Future  future:threadList) {
            final Future f1 = future;
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        f1.get(5, TimeUnit.SECONDS);
                    } catch (InterruptedException e) {
                        System.out.println("future在睡着时被打断");
                        task.shutdownNow();
                    } catch (ExecutionException e) {
                        System.out.println("future在尝试取得任务结果时出错");
                        task.shutdownNow();
                    } catch (TimeoutException e) {
                        System.out.println("future时间超时");

                        f1.cancel(true);
                        //executor.shutdownNow();
                        //executor.shutdown();
                    }finally{

                        task.shutdownNow();

                    }
                }
            });
            t.start();
        }

        System.out.println(System.currentTimeMillis());

        }

    }


