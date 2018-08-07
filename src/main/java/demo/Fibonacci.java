package demo;

import java.math.BigDecimal;

/**
 * Created by rp on 16-12-9.
 */
public class Fibonacci {

    private static BigDecimal f1 =new BigDecimal(0);
    private  static BigDecimal f2 =new BigDecimal(1);
    private  static BigDecimal tmp = new BigDecimal(0);


    public static int getFibonacciNum(int idx){
         if(idx==1||idx ==2)
           return 1;
         else
             return getFibonacciNum(idx-1)+getFibonacciNum(idx-2);
    }


    public static BigDecimal fibonacci1(int idx){

        for(int i=1;i<idx;i++){
           tmp = f1.add(f2);
            f1 =f2;
            f2 = tmp;
        }
        return  f2;

    }


    public static void main(String[] args){
        long l1 =System.currentTimeMillis();
       System.out.println(fibonacci1(100));
        long l2 =System.currentTimeMillis();
        System.out.println(l2-l1);



    }
}
