package app.server.masterwork2;

import app.server.masterwork.Task;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Author:  Administrator
 * Date:    2017/5/10 22:27
 * Function:Please input the function of this class!
 */
public class Worker implements Runnable {
    // 与任务管理类共享任务实体容器
    private ConcurrentLinkedDeque<Task> tasks;
    // 与任务管理类共享任务返回结果集容器
    private ConcurrentHashMap<String,Object> results;

    public void setTasks(ConcurrentLinkedDeque<Task> tasks) {
        this.tasks = tasks;
    }

    public void setResults(ConcurrentHashMap<String, Object> results) {
        this.results = results;
    }

    @Override
    public void run() {
        Task task = tasks.poll();
        int price = task.getPrice();
        results.put(Thread.currentThread().getName(),price);
    }
}
