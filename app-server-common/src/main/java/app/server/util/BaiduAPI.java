package app.server.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

/**
 * 根据经纬度值调用百度API获取定位点的地址信息
 * @author Lee
 * @Time 2016/4/21.
 */
public class BaiduAPI {

    private static String ak = "5ef2641d89438a6e708db122820cf1d2";

    /*
     * lat : 纬度。说明：与赤道平行的线就是纬度即X轴
     * lng : 经度。说明：与赤道垂直的线就是经度即Y轴
     */
    public static Map<String, String> testPost(String lat, String lng) {

        BufferedReader in = null;
        InputStream l_urlStream = null;
        OutputStreamWriter out = null;

        StringBuilder urlAddress = new StringBuilder("http://api.map.baidu.com/geocoder?");
        urlAddress.append(ak);
        urlAddress.append("=您的密钥&callback=renderReverse&location=");
        urlAddress.append(lat);
        urlAddress.append(",");
        urlAddress.append(lng);
        urlAddress.append("&output=json");

        try {
            URL url = new URL(urlAddress.toString());
            URLConnection connection = url.openConnection();
            /**
             * 然后把连接设为输出模式。URLConnection通常作为输入来使用，比如下载一个Web页。
             * 通过把URLConnection设为输出，你可以把数据向你个Web页传送。下面是如何做：
             */
            connection.setDoOutput(true);
            out = new OutputStreamWriter(connection.getOutputStream(), "utf-8");
//        remember to clean up
            out.flush();
            out.close();
//        一旦发送成功，用以下方法就可以得到服务器的回应：
            String res;

            l_urlStream = connection.getInputStream();
            in = new BufferedReader(new InputStreamReader(l_urlStream, "UTF-8"));
            StringBuilder sb = new StringBuilder("");
            while ((res = in.readLine()) != null) {
                sb.append(res.trim());
            }

            if(StringUtils.isEmpty(sb.toString())){
                return null;
            }

            return JSON.parseObject(sb.toString(),new TypeReference<Map<String,String>>(){});
        } catch (IOException e){
            e.printStackTrace();
            return null;
        } finally {
            if (in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (l_urlStream != null){
                try {
                    l_urlStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Map<String, String> json = BaiduAPI.testPost("23.881484","115.742757");
        System.out.println("address :" + json.toString());
    }


}
