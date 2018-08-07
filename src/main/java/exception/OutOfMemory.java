package exception;

/**
 * Created by rp on 16-12-12.
 */
public class OutOfMemory {


    public static int test(int idx){
        return test(idx-1)+test(idx-2);
    }


    public static void main(String[] args){
          System.out.println(OutOfMemory.test(1000000));
    }
}
