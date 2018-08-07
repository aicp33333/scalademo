package sort;

/**
 * Created by rongpei on 2017/6/8.
 */
public class Quicksort {

    /*
     * 排序的核心算法
     *
     * @param array
     *      待排序数组
     * @param startIndex
     *      开始位置
     * @param endIndex
     *      结束位置
     */
    private static void sortCore(int[] array, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return;
        }

        int boundary = boundary(array, startIndex, endIndex);

        sortCore(array, startIndex, boundary - 1);
        sortCore(array, boundary + 1, endIndex);
    }

    /*
    * 交换并返回分界点
    *
    * @param array
    *      待排序数组
    * @param startIndex
    *      开始位置
    * @param endIndex
    *      结束位置
    * @return
    *      分界点
    */
    private static int boundary(int[] array, int startIndex, int endIndex) {
        int standard = array[startIndex]; // 定义标准
        int leftIndex = startIndex; // 左指针
        int rightIndex = endIndex; // 右指针

        while(leftIndex < rightIndex) {
            while(leftIndex < rightIndex && array[rightIndex] >= standard) {
                rightIndex--;
            }
            array[leftIndex] = array[rightIndex];

            while(leftIndex < rightIndex && array[leftIndex] <= standard) {
                leftIndex++;
            }
            array[rightIndex] = array[leftIndex];
        }

        array[leftIndex] = standard;
        return leftIndex;
    }



    public static  void sort(int[] array, int startIndex, int endIndex){
             if(startIndex>=endIndex){
                 return;
             }
        int idx = sortboundary(array,startIndex,endIndex);

        sort(array,startIndex,idx-1);
        sort(array,idx+1,endIndex);
    }

    public static int sortboundary(int[] array, int startIndex, int endIndex){
        int first= array[startIndex];
        while (startIndex<endIndex){

            while (startIndex<endIndex && array[endIndex]>first){
                endIndex --;
            }

            array[startIndex] = array[endIndex];

            while (startIndex<endIndex && array[startIndex] < first){
                startIndex++;
            }

            array[endIndex] = array[startIndex];
        }
        array[startIndex] = first;
       return  startIndex;
    }


    public static void main(String[] args) {
        int[] a = {6,2,4,9,5,7};
        sortCore(a,0,a.length-1);
        for (int i : a){
            System.out.print(i +",");

        }
        System.out.println();

        int[] b = {6,2,4,9,5,7};
        sort(b,0,a.length-1);

        for (int i : b){
            System.out.print(i +",");

        }
    }

}
