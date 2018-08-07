package threads;

/**
 * Created by rp on 16-12-5.
 */
public class MyRunnable implements  Runnable {


    public void run(){
        String name = Thread.currentThread().getName();
        System.out.println("MyThread.run()"+name+"我进来了");
        int idx= 0;
        while (idx!=5){
            try {
                idx++;
                Object obj = MyQueue.take();
                System.out.println("MyThread.run()"+name+"我那到了第"+obj+"个");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        return;

    }
}
