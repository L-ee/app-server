package disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * Author:  Administrator
 * Date:    2017/5/21 22:54
 * Function:Please input the function of this class!
 */
public class LongEventHandler implements EventHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
        System.out.println(longEvent.getValue());
    }
}
