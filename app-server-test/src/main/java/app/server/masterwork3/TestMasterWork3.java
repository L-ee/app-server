package app.server.masterwork3;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ilene on 2017/7/22.
 */
public class TestMasterWork3 {
    // 盛放任务完成返回的结果集
    private ConcurrentHashMap<String,Object> result = new ConcurrentHashMap();
    private ExecutorService executor = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {

        TestMasterWork3 testMasterWork3 = new TestMasterWork3();
        testMasterWork3.executor.submit(() -> {
            testMasterWork3.result.put("ytyyyu","ddddddddddddddd0000");
        });
        testMasterWork3.executor.submit(() -> {
            testMasterWork3.result.put("ytyyddddyu","dddddd55555555555500");
        });
        testMasterWork3.executor.shutdown();
        while (true){
            if(testMasterWork3.executor.isTerminated()){
                break;
            }
        }
        for (Map.Entry<String, Object> entry : testMasterWork3.result.entrySet()) {
            System.out.println(entry.getKey() + "===ddd=========" + entry.getValue().toString());
        }
    }
}