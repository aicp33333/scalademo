package exception;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Created by rp on 16-12-12.
 */
public class StackOverFlow {

    public static   String test(){
        try {
            return "111111111";
        }finally {
            System.out.println("222222222");
        }
    }



    public static void main(String[] args){

//        Scanner sc = new Scanner(System.in);
//         System.out.print(sc.next());


       while (true){
           OutOfMemory om  =new  OutOfMemory();
       }

    }
}
