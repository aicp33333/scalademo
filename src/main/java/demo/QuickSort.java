package demo;

import java.util.HashMap;

/**
 * Created by rp on 17-2-10.
 * 快速排序
 */
public class QuickSort {
    public static int partition(int []array,int lo,int hi){
        //固定的切分方式
        int key=array[lo];
        while(lo<hi){
            while(array[hi]>=key&&hi>lo){//从后半部分向前扫描
                hi--;
            }
            array[lo]=array[hi];
            while(array[lo]<=key&&hi>lo){//从前半部分向后扫描
                lo++;
            }
            array[hi]=array[lo];
        }
        array[hi]=key;
        return hi;
    }

    public static void sort(int[] array,int lo ,int hi){
        if(lo>=hi){
            return ;
        }
        int index=partition(array,lo,hi);
        sort(array,lo,index-1);
        sort(array,index+1,hi);
    }



    public static void shellsort1(int[] array,int size){



        int tmp = 0;
        if(size==1){
            tmp = size;
        }else{
            tmp = size/2;
        }

        System.out.println(tmp);
        for (int i =0 ;i<array.length-tmp ; i++){
           if(array[i]>array[tmp+i]){
               int t= array[i];
               array[i] = array[tmp+i];
               array[tmp+i] =t;
           }

        }

        if(tmp==1){
           return;
        }

        shellsort1(array,tmp);

    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    public static void main(String[] args) {
        int[] array = new int[]{10,3,4,14,32,6,7,8};


        HashMap<String,String> map = new HashMap<String,String>();
        map.put("4","qw");
        map.put("5","qw");
        map.put("15","qw");
        map.put("16","qw");
        System.out.println(map);



        //sort(array,0,7);
        //shellsort1(array,array.length);

        //for (int i: array) System.out.print (i+",");


    }
}
