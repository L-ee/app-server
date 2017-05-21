package disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * Author:  Administrator
 * Date:    2017/5/21 22:27
 * Function:Please input the function of this class!
 */
public class LongEventFactory implements EventFactory {
    @Override
    public Object newInstance() {
        return new LongEvent();
    }
}
