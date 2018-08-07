package demo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by 2017/10/18.
 *
 * @author rongpei
 */
public class MysqlTest {

     public static void main(String args[]){
          try {
               BufferedReader in = new BufferedReader(new FileReader("/Users/rongpei/qianjin.user.00001.sql"));

               try {
                    System.out.println(in.readLine());
               } catch (IOException e) {
                    e.printStackTrace();
               }

          } catch (FileNotFoundException e) {
               e.printStackTrace();
          }

     }
}
