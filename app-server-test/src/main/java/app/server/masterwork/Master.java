package app.server.masterwork;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Author:  Administrator
 * Date:    2017/5/8 22:33
 * Function:Please input the function of this class!
 */
public class Master {

    // 1,应该有一个承装任务的集合
    private ConcurrentLinkedDeque<Task> workQuque = new ConcurrentLinkedDeque();
    // 2,
    private Map<String,Thread> workers = new HashMap();
    // 3,
    private ConcurrentHashMap<String,Object> resultMap = new ConcurrentHashMap();
    // 4,构造方法
    public Master(Worker worker,int workerCount){
        worker.setResultMap(this.resultMap);
        worker.setWorkerQueue(this.workQuque);
        for (int i =0;i< workerCount;i++){
            workers.put("子节点" + i,new Thread(worker));
        }
    }

    // 5,提交
    public void submit(Task task){
        this.workQuque.add(task);
    }

    // 6,需要一个执行的方法（）
    public void execute(){
        for (Map.Entry<String, Thread> entry : workers.entrySet()) {
            entry.getValue().start();
        }
    }

    // 7,判断是否完成
    public boolean isComplete(){
        for (Map.Entry<String, Thread> entry : workers.entrySet()) {
            if(entry.getValue().getState() != Thread.State.TERMINATED){
                return false;
            }
        }
        return true;
    }

    // 8,返回结果集
    public int getResult(){
        int ret = 0;
        for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
            ret += (Integer)entry.getValue();
        }
        return ret;
    }
}
