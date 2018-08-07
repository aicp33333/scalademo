package threads;

/**
 * Created by rp on 16-12-5.
 */
public class RunnablePut implements Runnable{





    public void run(){
        String name = Thread.currentThread().getName();


         for(int i=0; i<10;i++){
            try {
                MyQueue.put(i);
                System.out.println("MyThread.run()"+name+"我把第"+i+"个放进去了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        return ;

    }
}
