package app.server;

import java.util.concurrent.CountDownLatch;

/**
 * Author:  Administrator
 * Date:    2017/5/18 23:21
 * Function:Please input the function of this class!
 */
public class CountDownLatchTest {

    public static void main(String[] args) {

        final CountDownLatch latch = new CountDownLatch(2);

        Thread t1 = new Thread(() -> {
            System.out.println("任务1，执行开始！");
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务1，执行完成！");
        });

        Thread t2 = new Thread(() -> {
            System.out.println("任务2，执行开始！");
            System.out.println("任务2，执行完成！");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();

        });

        Thread t3 = new Thread(() -> {
            System.out.println("任务3，执行开始！");
            System.out.println("任务3，执行完成！");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();

        });


        t1.start();
        t2.start();
        t3.start();










    }


}
