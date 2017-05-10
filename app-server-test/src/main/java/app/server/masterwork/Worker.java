package app.server.masterwork;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Author:  Administrator
 * Date:    2017/5/8 22:34
 * Function:Please input the function of this class!
 */
public class Worker implements Runnable {

    private ConcurrentLinkedDeque<Task> workQueue;
    private ConcurrentHashMap<String,Object> resultMap;
    @Override
    public void run() {
        while (true){
            Task input = this.workQueue.poll();
            if(input == null){
                break;
            }
            Object handle = handle(input);
            this.resultMap.put(Integer.toString(input.getId()),handle);
        }
    }

    public Object handle(Task task){
        Object ouput = null;
        try {
            Thread.sleep(500);
            ouput = task.getPrice();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ouput;
    }


    public void setWorkerQueue(ConcurrentLinkedDeque<Task> workQueue){
        this.workQueue = workQueue;
    }
    public void setResultMap(ConcurrentHashMap<String,Object> resultMap){
        this.resultMap = resultMap;
    }
}
