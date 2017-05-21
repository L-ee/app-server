package disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author:  Administrator
 * Date:    2017/5/21 22:27
 * Function:Please input the function of this class!
 */
public class LongEventMain {


    public static void main(String[] args) {
        // 准备构造Disruptor数据参数并启动
        ExecutorService executorService = Executors.newCachedThreadPool();
        LongEventFactory factory = new LongEventFactory();
        int ringBufferSize = 1024 * 1024;
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory, ringBufferSize, executorService, ProducerType.SINGLE, new YieldingWaitStrategy());
        disruptor.handleEventsWith(new LongEventHandler());
        disruptor.start();
        // 生产数据并发布
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        LongEventProducer longEventProducer = new LongEventProducer(ringBuffer);
        ByteBuffer allocate = ByteBuffer.allocate(8);
        for(long a = 0;a < 100;a++){
            allocate.putLong(0,a);
            longEventProducer.onData(allocate);
        }
        disruptor.shutdown();
        executorService.shutdown();
    }


}
