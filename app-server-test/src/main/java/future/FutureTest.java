package future;

/**
 * Author:  Administrator
 * Date:    2017/8/6 21:59
 * Function:Please input the function of this class!
 */
public class FutureTest {

    public static void main(String[] args) {

        FutureClient futureClient = new FutureClient();
        Data data = futureClient.request("请求参数");
        System.out.println("请求发送成功");
        System.out.println("做其他的事情");
        String request = data.getRequest();
        System.out.println(request);


    }


}

class RealData implements Data {
    private String queryStr;

    public RealData(String queryStr) {
        System.out.println("根据" + queryStr + "进行查询，这是一个很耗时的操作");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("操作完毕，获取结果");
        this.queryStr = "查询结果。。。。。。。。。。";
    }

    @Override
    public String getRequest() {
        return queryStr;
    }
}


class FutureClient {
    public Data request(final String queryStr){
        FutureData futureData = new FutureData();
        new Thread(() -> {
            RealData realData = new RealData(queryStr);
            futureData.setRealData(realData);
        }).start();
        return futureData;
    }
}

class FutureData implements Data{


    private boolean isReady = false;
    private RealData realData;


    public synchronized void setRealData(RealData realData) {
        if(isReady){
            return;
        }
        this.realData = realData;
        isReady = true;
        notify();
    }

    @Override
    public synchronized String getRequest() {
        while (!isReady){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return this.realData.getRequest();
    }
}



interface Data {
    String getRequest();
}
