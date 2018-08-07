package demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by rp on 17-1-19.
 */
public class MathEval {





    public static void main(String[] args){
//        Stack<Object> stack = new Stack<Object>();//保存值的堆栈
//        System.out.println(stack.isEmpty());
        System.out.println(MathEval.simpleEval("9+(3-1)*3+10/2"));
    }


    public static int simpleEval(String eval){
        Stack<String> stack = new Stack<String>();//保存值的堆栈

        String[] s = eval.split("");

        List<String> list = new ArrayList<String>();

        String tmp = "";
        for (String str :s) {
            if("+-*/()".indexOf(str)==-1){
                tmp += str;
            }else {
                if(tmp!=""){
                    list.add(tmp);
                }
                list.add(str);
                tmp = "";
            }
        }
        list.add(tmp);
        List<String> list2 = new ArrayList<String>();

        int idx =0;
        System.out.println("---------------"+list.size());

        for (String str: list) {
           // te(stack);
            if("+-".indexOf(str)!=-1){
                if(idx>0||stack.isEmpty()){
                    stack.push(str);
                }else if("+-".indexOf(stack.peek())!=-1){
                    list2.add(str);
                }else{
                    list2.add(stack.pop());
                    stack.push(str);
                }
            }else if("*/".indexOf(str)!=-1){
                if(idx>0||stack.isEmpty()||"*/".indexOf(stack.peek())==-1){
                    stack.push(str);
                }else{
                    list2.add( str);
                }
            }else if(str.equals("(")){
                stack.push(str);
                idx++;
            }else if(str.equals(")")){
                String t= null;
                while (!(t= stack.pop()).equals("(")){
                    list2.add(t);
                }
                idx--;
            }else{
                list2.add(str);
            }
        }






        while (!stack.isEmpty()){
            list2.add(stack.pop());
        }
        System.out.println(list2.toString());


        for (String str :list2){
            if("+-*/".indexOf(str)==-1){
                stack.push(str);
            }else{
                int i1 = Integer.parseInt(stack.pop());
                int i2 = Integer.parseInt(stack.pop());

                if(str.equals("+")){
                    stack.push((i1+i2)+"");
                }else if(str.equals("-")){
                    stack.push((i2-i1)+"");
                }else if(str.equals("*")){
                    stack.push((i2*i1)+"");
                }else if(str.equals("/")){
                    stack.push((i2/i1)+"");
                }
            }
        }
        return Integer.parseInt(stack.pop()) ;
    }


   public  static void te(Stack<String> stack){

       Object[] st= stack.toArray();
       for (Object obj:st) {
           System.out.print(obj);
           System.out.println("-----------");
       }

   }



}
