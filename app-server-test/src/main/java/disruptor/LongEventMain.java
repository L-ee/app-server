package disruptor;

import com.lmax.disruptor.EventTranslatorOneArg;
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
        // 生产者类
        LongEventFactory factory = new LongEventFactory();
        // 存储空间大小
        int ringBufferSize = 1024 * 1024;
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory, ringBufferSize, executorService, ProducerType.SINGLE, new YieldingWaitStrategy());
        // 消费者
        disruptor.handleEventsWith(new LongEventHandler());
        // 启动
        disruptor.start();

        // 容器
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        // 1) 第一方法生产数据
//        LongEventProducer longEventProducer = new LongEventProducer(ringBuffer);
        // 2) 第二方法生产数据
        LongEventProducerWithTranslator longEventProducer = new LongEventProducerWithTranslator(ringBuffer);
        ByteBuffer allocate = ByteBuffer.allocate(8);
        for(long a = 0;a < 1000;a++){
            allocate.putLong(0,a);
            longEventProducer.onData(allocate);
        }
        disruptor.shutdown();
        executorService.shutdown();
    }

    static class LongEventProducerWithTranslator {

        private final RingBuffer<LongEvent> ringBuffer;

        private static final EventTranslatorOneArg<LongEvent,ByteBuffer> TRANSLATOR = new EventTranslatorOneArg<LongEvent, ByteBuffer>() {
            @Override
            public void translateTo(LongEvent longEvent, long l, ByteBuffer byteBuffer) {
                longEvent.setValue(byteBuffer.getLong(0));
            }
        };

        LongEventProducerWithTranslator(RingBuffer<LongEvent> ringBuffer) {
            this.ringBuffer = ringBuffer;
        }

        public void onData(ByteBuffer buffer){
            ringBuffer.publishEvent(TRANSLATOR,buffer);
        }

    }


}
