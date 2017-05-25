package disruptor.demo2;

import com.lmax.disruptor.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author:  Administrator
 * Date:    2017/5/25 23:40
 * Function:Please input the function of this class!
 */
public class Main1 {

    public static void main(String[] args) {
        int BUFFER_SIZE = 1024;
        int THREAD_NUMBERS = 4;


        final RingBuffer<Trade> ringBuffer = RingBuffer.createSingleProducer(new EventFactory<Trade>() {
            @Override
            public Trade newInstance() {
                return new Trade();
            }
        }, BUFFER_SIZE, new YieldingWaitStrategy());

        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_NUMBERS);

        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();

        BatchEventProcessor<Trade> eventProcessor = new BatchEventProcessor<Trade>(ringBuffer, sequenceBarrier, new TradeHandler());
        ringBuffer.addGatingSequences(eventProcessor.getSequence());
        executorService.submit(eventProcessor);





    }

}
