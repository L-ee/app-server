package app.server.masterwork;

import java.util.Random;

/**
 * Author:  Administrator
 * Date:    2017/5/8 23:01
 * Function:Please input the function of this class!
 */
public class Main {

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        Master master = new Master(new Worker(),Runtime.getRuntime().availableProcessors());
        Random r = new Random();
        for(int i = 0;i<= 100;i++){
            Task task = new Task();
            task.setId(i);
            task.setName("任务"+i);
            task.setPrice(r.nextInt(1000));
            master.submit(task);
        }
        master.execute();
        long start = System.currentTimeMillis();
        while (true){
            if(master.isComplete()){
                long end = System.currentTimeMillis() -start;
                int result = master.getResult();
                System.out.println("最终结果：" + result + ",耗时：" + end);
                break;
            }
        }
    }


}
