package app.server.masterwork2;

import app.server.masterwork.Task;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Author:  Administrator
 * Date:    2017/5/10 22:21
 * Function:Please input the function of this class!
 */
public class Master {
    // 盛放任务的容器
    public ConcurrentLinkedDeque<Task> tasks = new ConcurrentLinkedDeque();
    // 盛放任务完成返回的结果集
    public ConcurrentHashMap<String,Object> result = new ConcurrentHashMap();
    // 执行任务的线程池
    private Map<String,Thread> threadPool = new HashMap();
    // 初始化任务及线程执行数
    public Master(Worker worker,int count){
        worker.setResults(result);
        worker.setTasks(tasks);
        for (int i = 0;i<count;i++){
            Thread thread = new Thread(worker);
            thread.setName("线程-"+i);
            threadPool.put("线程-" + i,thread);
        }
    }

    // 开始执行任务
    public void execute(){
        for (Map.Entry<String, Thread> entry : threadPool.entrySet()) {
            entry.getValue().start();
        }
    }

    // 判断是否完成所有任务
    public boolean isComplete(){
        for (Map.Entry<String, Thread> entry : threadPool.entrySet()) {
            if(entry.getValue().getState() != Thread.State.TERMINATED){
                return false;
            }
        }
        return true;
    }




}
