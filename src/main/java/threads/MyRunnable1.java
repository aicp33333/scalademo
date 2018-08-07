package threads;

/**
 * Created by rp on 16-12-5.
 */
public class MyRunnable1 implements  Runnable{


    public void run(){
        String name = Thread.currentThread().getName();

        int idx= 0;
        while (idx==5){
            try {

                Thread.sleep(1000);

                idx++;
                 Object obj = MyQueue.take();
                System.out.println("MyThread1.run()"+name+"我那到了第"+obj+"个");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        return ;

    }
}
