package threads;

/**
 * Created by rp on 16-12-5.
 */
public class RunnableTest {

    public static  void main(String[] args){
           MyRunnable myRunnable = new MyRunnable();
           RunnablePut runnablePut = new RunnablePut();
           Thread thread1 = new Thread(myRunnable,"myRunnable1");
           Thread thread2 = new Thread(myRunnable,"myRunnable2");
           Thread threadPut = new Thread(runnablePut ,"threadPut");
           thread1.start();
           threadPut.start();
           thread2.start();

    }
}
