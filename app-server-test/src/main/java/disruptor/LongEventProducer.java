package disruptor;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * Author:  Administrator
 * Date:    2017/5/21 22:56
 * Function:Please input the function of this class!
 */
public class LongEventProducer {

    private final RingBuffer<LongEvent> ringBuffer;
    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer byteBuffer){
        long next = ringBuffer.next();
        try {
            LongEvent longEvent = ringBuffer.get(next);
            longEvent.setValue(byteBuffer.getLong(0));
        } finally {
            ringBuffer.publish(next);
        }
    }

}
