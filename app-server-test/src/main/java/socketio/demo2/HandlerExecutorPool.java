package socketio.demo2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Author:  Administrator
 * Date:    2017/6/7 23:13
 * Function:Please input the function of this class!
 */
public class HandlerExecutorPool {

    private ExecutorService executor;
    public HandlerExecutorPool(int maxPoolSize,int queueSize){
        this.executor = new ThreadPoolExecutor(
                Runtime.getRuntime().availableProcessors(),
                maxPoolSize,120L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(queueSize)
        );
    }
    public void executor(Runnable task){
        this.executor.execute(task);
    }

}
