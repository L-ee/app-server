package app.server;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author:  Administrator
 * Date:    2017/5/18 23:41
 * Function:Please input the function of this class!
 */
public class CyclicBarrierTest {

    static class Runner implements Runnable {

        private CyclicBarrier barrier;
        private String name;

        public Runner(CyclicBarrier barrier,String name){
            this.name = name;
            this.barrier = barrier;
        }

        @Override
        public void run() {

            System.out.println(name + ":准备好了。");
            try {
                Thread.sleep(1000 * new Random().nextInt(5));
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(name + ": GO!");

        }
    }


    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3);
        ExecutorService service = Executors.newFixedThreadPool(3);
        service.submit(new Thread(new Runner(barrier,"rose")));
        service.submit(new Thread(new Runner(barrier,"jack")));
        service.submit(new Thread(new Runner(barrier,"mark")));

        service.shutdown();
    }

}
