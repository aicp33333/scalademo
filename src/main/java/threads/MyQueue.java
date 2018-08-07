package threads;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by rp on 16-12-2.
 */
public class MyQueue {
    static final Lock lock = new ReentrantLock();
    static final Condition notFull = lock.newCondition();
    static final Condition notEmpty = lock.newCondition();

    static final Object obj1 = new Object();
    static final Object obj2 = new Object();

    static final Object[] items = new Object[2]; // 阻塞队列
    static int putptr, takeptr, count;



    public static  void put1(Object x) throws InterruptedException {
        System.out.println("进入put");
        synchronized (obj1){
            while(count==items.length){
                System.out.println("put lock 锁住");
                obj1.wait();
            }
        }
        items[putptr] = x; // 终于可以插入队列
        if (++putptr == items.length)
            putptr = 0; // 如果下标到达数组边界，循环下标置为0
        ++count;
        synchronized(obj2){
            System.out.println("take lock 解锁");
            obj2.notifyAll();
        }
    }

    public static Object take1() throws InterruptedException {
        System.out.println("进入take");
        synchronized (obj2){
            while(count==0){
                System.out.println("take lock  锁珠");
                obj2.wait();
            }
        }
         Object obj= items[takeptr];
        System.out.println(obj);
           if(++takeptr==items.length){
               takeptr=0;
           }
        count --;
        synchronized(obj1){
            System.out.println("put lock 解锁");
            obj1.notifyAll();
        }
         return obj;
    }




    public static  void put(Object x) throws InterruptedException {

        System.out.println("进入put");
        lock.lock();
        System.out.println("put lock 锁住");
        try {
            while (count == items.length) { // 如果队列满了，notFull就一直等待
                System.out.println("put notFull 等待");
                notFull.await(); // 调用await的意思取反，及not notFull -> Full
            }
            items[putptr] = x; // 终于可以插入队列
            if (++putptr == items.length)
                putptr = 0; // 如果下标到达数组边界，循环下标置为0
            ++count;
            System.out.println("put notEmpty 唤醒");
            notEmpty.signal(); // 唤醒notEmpty
        } finally {
            System.out.println("put lock 解锁");
            lock.unlock();
        }
    }

    public static Object take() throws InterruptedException {
        lock.lock();
        System.out.println("take lock 锁住");
        try {
            while (count == 0) {
                System.out.println("take notEmpty 等待");
                notEmpty.await();
            }
            Object x = items[takeptr];
            if (++takeptr == items.length)
                takeptr = 0;
            --count;
            System.out.println("take notFull 唤醒");
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
            System.out.println("take lock 解锁");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final MyQueue bb = new MyQueue();
        System.out.println(Thread.currentThread()+","+bb);

        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread()+","+bb);
                    bb.put1("xx");
                    bb.put1("yy");
                    bb.put1("zz");
                    bb.put1("zz");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        bb.take1();

    }
}
