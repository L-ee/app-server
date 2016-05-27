package com.util;

import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by loovee1 on 2016/5/27.
 */
public class BaiduAPI {

    private static String ak = "5ef2641d89438a6e708db122820cf1d2";

    public static Map<String, String> testPost(String x, String y) throws IOException {
        URL url = new URL("http://api.map.baidu.com/geocoder?" + ak + "=您的密钥" +
                "&callback=renderReverse&location=" + x
                + "," + y + "&output=json");
        URLConnection connection = url.openConnection();
        /**
         * 然后把连接设为输出模式。URLConnection通常作为输入来使用，比如下载一个Web页。
         * 通过把URLConnection设为输出，你可以把数据向你个Web页传送。下面是如何做：
         */
        connection.setDoOutput(true);
        OutputStreamWriter out = new OutputStreamWriter(connection
                .getOutputStream(), "utf-8");
//        remember to clean up
        out.flush();
        out.close();
//        一旦发送成功，用以下方法就可以得到服务器的回应：
        String res;
        InputStream l_urlStream;
        l_urlStream = connection.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                l_urlStream,"UTF-8"));
        StringBuilder sb = new StringBuilder("");
        while ((res = in.readLine()) != null) {
            sb.append(res.trim());
        }
        String str = sb.toString();
        System.out.println(str);
        Map<String,String> map = null;
        if(StringUtils.isNotEmpty(str)) {
            int addStart = str.indexOf("formatted_address\":");
            int addEnd = str.indexOf("\",\"business");
            if(addStart > 0 && addEnd > 0) {
                String address = str.substring(addStart+20, addEnd);
                map = new HashMap<String,String>();
                map.put("address", address);
                return map;
            }
        }

        return null;

    }

    public static void main(String[] args) throws IOException {
        Map<String, String> json = BaiduAPI.testPost("23.881484","115.742757");
        System.out.println("address :" + json.get("address"));
        System.out.println("city:" + json.get("addressComponent"));
    }




}
