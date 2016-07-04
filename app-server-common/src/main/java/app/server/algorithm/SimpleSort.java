package app.server.algorithm;

/**
 * Author:  Lee
 * Date:    2016/6/2 23:10
 * Function:
 *          简单的算法排序 ：都以队员按身高从低到高排队来讲述实现简单排序算法的原理
 *          1) 冒泡排序算法的运作如下：（从后往前）
 *                    1、比较相邻的元素。如果第一个比第二个大，就交换他们两个。
 *                    2、对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。
 *                    3、针对所有的元素重复以上的步骤，除了最后一个。
 *                    4、持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
 *          2) 插入排序
 *              插入排序法,插入排序的基本操作就是将一个数据插入到已经排好序的有序数据中，从而得到一个新的、个数加一的有序数据，算法适用于少量数据的排序，时间复杂度为O(n^2)。是稳定的排序方法。
 *              插入算法把要排序的数组分成两部分：
 *                  第一部分包含了这个数组的所有元素，但将最后一个元素除外（让数组多一个空间才有插入的位置）。
 *                  而第二部分就只包含这一个元素（即待插入元素）。在第一部分排序完成后，再将这个最后元素插入到已排好序的第一部分中。
 *          3) 选择排序
 *              选择排序（Selection sort）是一种简单直观的排序算法。
 *              它的工作原理是每一次从待排序的数据元素中选出最小（或最大）的一个元素，存放在序列的起始位置，直到全部待排序的数据元素排完。
 *              选择排序是不稳定的排序方法（比如序列[5， 5， 3]第一次就将第一个[5]与[3]交换，导致第一个5挪动到第二个5后面）。
 *
 */
public class SimpleSort {


    public static void main(String[] args) {
        int[] arg = new int[]{2, 1, 4, 5, 8, 7, 6, 3, 9, 0};

        System.out.print("排序前:");
        for (int m : arg) {
            System.out.print(m + ",");
        }

        //SimpleSort.bubbleSortAsc(arg);
        //SimpleSort.bubbleSortDesc(arg);
        SimpleSort.insertDesc(arg);
        //SimpleSort.insertAsc(arg);
        System.out.print("排序后:");
        for (int n : arg) {
            System.out.print(n + ",");
        }
    }

    /**
     * 插入排序（降序）
     * @param args
     */
    public static int[] insertDesc(int[] args) {

        int temp;
        for (int i = 0; i < args.length ; i++) {
            for (int j = i+1 ; j < args.length ; j++){
                if ( args[j] > args[i]) {
                    temp = args[j];
                    args[j] = args[i];
                    if(i == 0){
                        args[i] = temp;
                    } else {
                        for(int x = i - 1; x >= 0 ; x--){
                            if( temp > args[x]){
                                args[x+1] = args[x];
                            } else {
                                args[x+1] = temp;
                                break;
                            }
                        }
                    }
                }
            }
        }

        return args;
    }

    /**
     * 插入排序（升序）
     * @param args
     */
    public static int[] insertAsc(int[] args) {

        int temp;
        for (int i = 0; i < args.length ; i++) {
            for (int j = i+1 ; j < args.length ; j++){
                if ( args[i] > args[j]) {
                    temp = args[j];
                    args[j] = args[i];
                    if(i == 0){
                        args[i] = temp;
                    } else {
                        for(int x = i - 1; x >= 0 ; x--){
                            if(args[x] > temp){
                                args[x+1] = args[x];
                            } else {
                                args[x+1] = temp;
                                break;
                            }
                        }
                    }
                }
            }
        }

        return args;
    }

    /**
     * 冒泡排序（升序）
     * @param args
     */
    public static int[] bubbleSortAsc(int[] args) {

        for (int i = 0; i < args.length ; i++) {
            for (int j = i + 1; j < args.length; j++) {
                int temp;
                if (args[i] > args[j]) {
                    temp = args[j];
                    args[j] = args[i];
                    args[i] = temp;
                }
            }
        }
        return args;
    }

    /**
     * 冒泡排序（降序）
     * @param args
     */
    public static int[] bubbleSortDesc(int[] args) {

        for (int i = 0; i < args.length ; i++) {
            for (int j = i + 1; j < args.length; j++) {
                int temp;
                if (args[i] < args[j]) {
                    temp = args[j];
                    args[j] = args[i];
                    args[i] = temp;
                }
            }
        }
        return args;
    }

    /**
     * 选择排序（升序）
     * @param args
     */
    public static int[] selectAsc(int[] args) {

        int temp;
        int index;
        for (int i = 0; i < args.length ; i++) {
            temp = args[i];
            index = i;
            for (int j = i + 1; j < args.length; j++) {
                if (temp > args[j]) {
                    temp = args[j];
                    index = j;
                }
            }
            args[index] = args[i];
            args[i] = temp;

        }
        return args;
    }

    /**
     * 选择排序（降序）
     * @param args
     */
    public static int[] selectDesc(int[] args) {

        int temp;
        int index;
        for (int i = 0; i < args.length ; i++) {
            temp = args[i];
            index = i;
            for (int j = i + 1; j < args.length; j++) {
                if (temp < args[j]) {
                    temp = args[j];
                    index = j;
                }
            }
            args[index] = args[i];
            args[i] = temp;

        }
        return args;
    }

}
