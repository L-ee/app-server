package app.server.ifs.init;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public class HttpUtil {

    final static String charsetName = "UTF-8";

    public static String doGet(String url) throws MalformedURLException,IOException{
        HttpURLConnection urlconn = null;
        urlconn = (HttpURLConnection) new URL (url).openConnection ();
        urlconn.getRequestProperties ();
        urlconn.setRequestProperty ("content-type", "text/html");
        urlconn.setRequestMethod ("GET");
        urlconn.setConnectTimeout (100000);
        urlconn.setReadTimeout (100000);
        urlconn.setDoInput (true);

        BufferedReader rd = new BufferedReader (new InputStreamReader (urlconn.getInputStream (),charsetName));

        String temp = null;
        StringBuffer sb = new StringBuffer ();
        temp = rd.readLine ();
        while (temp != null) {
            sb.append (temp);
            temp = rd.readLine ();
        }
        rd.close ();
        urlconn.disconnect ();

        return sb.toString ();
    }

    public static String doPost(String url,String value) throws MalformedURLException,IOException{
        HttpURLConnection urlconn = null;
        urlconn = (HttpURLConnection) new URL (url).openConnection ();
        urlconn.getRequestProperties ();
        urlconn.setRequestProperty ("content-type", "application/json");
        urlconn.setRequestMethod ("POST");
        urlconn.setConnectTimeout (20000);
        urlconn.setReadTimeout (20000);
        urlconn.setDoInput (true);
        urlconn.setDoOutput (true);
        urlconn.getOutputStream ().write (value.getBytes ());
        urlconn.getOutputStream ().close ();

        BufferedReader rd = new BufferedReader (new InputStreamReader (urlconn.getInputStream (),charsetName));

        String temp = null;
        StringBuffer sb = new StringBuffer ();
        temp = rd.readLine ();
        while (temp != null) {
            sb.append (temp);
            temp = rd.readLine ();
        }
        rd.close ();
        urlconn.disconnect ();
        return sb.toString ();
    }

    /**
     * @Title: doPost
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @Author: tsingning
     * @Since: 2016年1月27日下午5:01:28
     * @param url
     * @return
     * @throws java.net.MalformedURLException
     * @throws java.io.IOException
     *             String headder = req.getHeader ("Content-Type"); // req.getHeader ("content-type;value=multipart/form-data");
     */
    public static String doPostFormData(String url,HttpServletRequest request) throws MalformedURLException,IOException{
        HttpURLConnection urlconn = null;
        urlconn = (HttpURLConnection) new URL (url).openConnection ();

        urlconn.getRequestProperties ();
        Enumeration enumeration = request.getHeaderNames ();
        // 遍历头部信息集
        while (enumeration.hasMoreElements ()) {
            // 取出信息名
            String name = (String) enumeration.nextElement ();
            // 取出信息值
            String value = request.getHeader (name);
            urlconn.setRequestProperty (name, value);
        }
        InputStream is = null;
         is = request.getInputStream();
//         int len = request.getContentLength();
//         byte bytes[] = new byte[len];
//         is.read(bytes);
        urlconn.setRequestMethod ("POST");
        urlconn.setConnectTimeout (10000);
        urlconn.setReadTimeout (10000);
        urlconn.setDoInput (true);
        urlconn.setDoOutput (true);
        urlconn.getOutputStream ().write (toByteArray(is));
        urlconn.getOutputStream ().close ();
        
        BufferedReader rd = new BufferedReader (new InputStreamReader (urlconn.getInputStream (),charsetName));
        String temp = null;
        StringBuffer sb = new StringBuffer ();
        temp = rd.readLine ();
        while (temp != null) {
            sb.append (temp);
            temp = rd.readLine ();
        }
        rd.close ();
        urlconn.disconnect ();
        return sb.toString ();
    }
    public static byte[] toByteArray(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
        }
        return output.toByteArray();
    }
}
