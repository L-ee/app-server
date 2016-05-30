package lee.test;

/**
 * Created by loovee1 on 2016/5/24.
 */
public class SystemDemo02 {


    public static int count = 0;

    public synchronized static void inc() {

        //这里延迟1毫秒，使得结果明显
        /*try {
            //Thread.sleep(1);
        } catch (InterruptedException e) {
        }*/

        count++;
    }

    public static void main(String[] args) {

        //final SystemDemo02 test = new SystemDemo02();

        //同时启动1000个线程，去进行i++计算，看看实际结果

        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SystemDemo02.inc();
                }
            }).start();
        }

        while(Thread.activeCount()>1)  //保证前面的线程都执行完
            Thread.yield();

        //这里每次运行的值都有可能不同,可能为1000
        System.out.println("运行结果:Counter.count=" + SystemDemo02.count);
    }




}
