package app.server.util;

/**
 * Author:  Lee
 * Date:    2016/6/2 23:10
 * Function:简单的算法排序
 */
public class SimpleSort {


    public static void main(String[] args) {
        int[] arg = new int[]{2, 1, 4, 5, 8, 7, 6, 3, 9, 0};

        System.out.print("排序前:");
        for (int m : arg) {
            System.out.print(m + ",");
        }

        //arg = SimpleSort.bubbleSortAsc(arg);
        //arg = SimpleSort.bubbleSortDesc(arg);
        //SimpleSort.insertDesc(arg);
        SimpleSort.insertAsc(arg);
        System.out.print("排序后:");
        for (int n : arg) {
            System.out.print(n + ",");
        }
    }

    /**
     * 插入排序（升序）
     * @param args
     */
    public static int[] insertAsc(int[] args) {

        int temp;
        for (int i = 0; i < args.length ; i++) {
            if ( args[i] > args[i+1]) {
                temp = args[i+1];
                args[i+1] = args[i];
                for(int j = i; j == 0 ; j--){
                    if(temp > args[j]){
                        
                    }
                }
                //args[i] = temp;
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
