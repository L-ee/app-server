package app.server.masterwork2;

import app.server.masterwork.Task;

import java.util.Map;
import java.util.Random;

/**
 * Author:  Administrator
 * Date:    2017/5/10 22:45
 * Function:Please input the function of this class!
 */
public class Main {

    public static void main(String[] args) {

        Master master = new Master(new Worker(), Runtime.getRuntime().availableProcessors());
        Random random = new Random();
        for(int i = 0;i<100;i++){
            Task task = new Task();
            task.setId(i);
            task.setName("任务-" + i);
            task.setPrice(random.nextInt(1000));
            master.tasks.add(task);
        }
        long start = System.currentTimeMillis();
        master.execute();
        while (true){
            if(master.isComplete()){
                break;
            }
        }
        long end = System.currentTimeMillis();
        int result = 0;
        for (Map.Entry<String, Object> entry : master.result.entrySet()) {
            int value = (int) entry.getValue();
            result += value;
        }
        System.out.println("耗时：" + (end-start) + "，执行任务结果：" + result);

    }

}
