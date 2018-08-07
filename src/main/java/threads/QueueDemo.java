package threads;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by 2017/10/17.
 *
 * @author rongpei
 */
public class QueueDemo {

     public static void main(String args[]){
         Queue<String> queue = new LinkedList<String>();
         //添加元素
         queue.offer("a");
         queue.offer("b");
         queue.offer("c");
         queue.offer("d");
         queue.remove();
         queue.remove();
         System.out.println(queue.peek());

        }
}
