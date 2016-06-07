package app.server.algorithm;

/**
 * Author:  Lee
 * Date:    2016/6/2 23:10
 * Function:
 *          简单的算法排序 ：都以队员按身高从低到高排队来讲述实现简单排序算法的原理
 *          1) 冒泡排序
 *                  1、从第一名队员跟下一名队员比较身高值，如果第一名身高值大于第二名身高值则相互调换位置，然后第二名队员跟第三名如上述规则作比较，直到
 *                  只剩下最后一名队员。则第一轮结束时，
 *                  2、第二轮则以
 *          2) 插入排序
 *          3) 选择排序
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
