package demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by rp on 17-2-28.
 */
public class YHtest {


    public static void TopN(){
        String[] str = {"A","B","C","D","E","F","G","H"};
        int[] idxs = {7,5,3,2,6,1,4,9};

        String[] obj = new String[8];

        for (int i = 0; i < idxs.length; i++) {
            obj[i] = (i+idxs[i])+","+str[i];
        }

        int size  = obj.length;
        String temp ="";
        for (int i = 0; i < size; i++) {

            int k = i;
            for (int j = size-1; j > i; j--) {
                if(Integer.parseInt(obj[j].split(",")[0])>Integer.parseInt(obj[k].split(",")[0]))
                    k= j;
            }
            temp =obj[i];
            obj[i] = obj[k];
            obj[k] = temp;

        }
        for (int i = 0; i <5; i++) {
            System.out.print(obj[i].split(",")[1]);
        }

    }

    public static List<String> list = new ArrayList<String>();

    public  static void  treeTest(String B){

        /**
         * 生成树
         */
        Node node = new Node("A");
        node.setLean(false);
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        nodeB.setLean(false);
        nodeC.setLean(false);
        nodeD.setLean(false);
        nodeB.getNodeList().add(new Node("E"));
        nodeC.getNodeList().add(new Node("F"));
        nodeC.getNodeList().add(new Node("G"));
        nodeC.getNodeList().add(new Node("H"));
        nodeD.getNodeList().add(new Node("I"));
        node.getNodeList().add(nodeB);
        node.getNodeList().add(nodeC);
        node.getNodeList().add(nodeD);


        System.out.println(node.getData());

        /**
         * 遍历树
         */

        if(node.getData().equals(B)){
            System.out.println("该元素为根节点都是他的子孙节点");
        }else{
            TreeMenu(node.getNodeList(),B);
        }

        System.out.println(list.toString());

    }

    public  static void TreeMenu(List<Node> nodeList,String B){

        for (Node node : nodeList) {
            if(node.getData().equals(B)||node.lean==true){
                list.add(node.getData());
            }else{
                list.add(node.getData());
                TreeMenu(node.getNodeList(),B);
            }

        }

    }

    public static void main(String[] args) throws ParseException {
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
//        Date[] ds = {formatter.parse("20170101 00:00:10"),formatter.parse("20170301 00:00:30"),formatter.parse("20170501 00:00:50")};
//        long tmp =0;
//         for (int i=1 ;i<ds.length ;i++){
//             tmp = ds[i].getTime() -ds[i-1].getTime() ;
//         }
//
//        long nh = 1000 * 60 * 60;
//        long nm = 1000 * 60;
//        long ns = 1000 ;
//        long hour = tmp / nh;
//        long min = tmp  % nh / nm;
//        long sec = tmp  % nh % nm / ns;
//        System.out.println( hour + "hour" + min + "minute"+sec+"second");

        //YHtest.TopN();

//        String[] strs = {"-100.1","abcd","1.0023e13","true","tttt","20","20170101","False","100%","20170103 04:00:00"};
//        String regx = "^((-?\\d+.?\\d*)[Ee]{1}(-?\\d+))$";
//        String regex="^[+-]?\\d+(\\.\\d+)?$";
//        for (String str:strs) {
//            if(str.matches(regx)||str.matches(regex)){
//                System.out.print("Number,");
//            }else if(str.toLowerCase().equals("true")||str.toLowerCase().equals("false")){
//                System.out.print("Boolean,");
//            }else if(str.matches("[0-9]{4}[0-9]{2}[0-9]{2}( [0-6]{2}:[0-6]{2}:[0-6]{2})?")){
//                System.out.print("Date,");
//            }else{
//                System.out.print("String,");
//            }
//
//        }

        //String[] strs = {"AC","CB","CD","AB","DF","CE","BF"};
        String[] strs = {"a","b","a1","a2","a","b","b","b","b3","d","e"};
        Map<String,Integer> map = new HashMap<String,Integer>();
        for (String str:strs) {
            if(map.get(str)!=null){
               int idx =  map.get(str);
               map.put(str,idx+1);
                if(map.get(str+idx)!=null){
                    map.put(str+idx+1,1);
                }else{
                    map.put(str+idx,1);
                }

            }else{
                map.put(str, 1);
            }
        }

        System.out.println(map.keySet().toString());
    }
}

class Node{
    String data ;
    Boolean lean  ;
    List<Node> nodeList = new ArrayList<Node>();

    public Node(String data){
        this.data = data;
        lean = true;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Boolean getLean() {
        return lean;
    }

    public void setLean(Boolean lean) {
        this.lean = lean;
    }

    public List<Node> getNodeList() {
        return nodeList;
    }




}
