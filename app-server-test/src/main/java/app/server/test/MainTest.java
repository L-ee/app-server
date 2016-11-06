package app.server.test;

/**
 * Author:  Administrator
 * Date:    2016/10/16 9:33
 * Function:Please input the function of this class!
 */
public class MainTest {

    public static boolean isPrime(int num) {
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

//        System.out.println(Math.sqrt(6));

        int n = 20;

        int[] array = new int[n];

        for (int i = 2; i < n; i++) {

            array[i] = i;

        }

        for (int i = 2; i < n; i++) {
            if (array[i] != 0) {

                int j, temp;

                temp = array[i];

                for (j = 2 * temp; j < n; j = j + temp) {
                    array[j] = 0;
                }
                System.out.println("\n");

            }

        }

        for (int i = 0 ; i<array.length;i++){
            System.out.print(array[i] + ",");
        }


        System.out.println();


        for (int j = 2; j <= 20; j++) {
            if (isPrime(j)) {
                System.out.print(j + ",");
            }
        }
    }

}
