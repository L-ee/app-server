package test;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by loovee1 on 2016/4/21.
 */
public class downimage {

    public void saveToFile(String destUrl) {
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        HttpURLConnection httpUrl = null;
        URL url = null;
        int BUFFER_SIZE = 1024;
        byte[] buf = new byte[BUFFER_SIZE];
        int size = 0;
        try {
            url = new URL(destUrl);
            httpUrl = (HttpURLConnection) url.openConnection();
            httpUrl.connect();
            bis = new BufferedInputStream(httpUrl.getInputStream());
            fos = new FileOutputStream("E:\\img\\haha.gif");
            while ((size = bis.read(buf)) != -1) {
                fos.write(buf, 0, size);
            }
            fos.flush();
        } catch (IOException e) {
        } catch (ClassCastException e) {
        } finally {
            try {
                fos.close();
                bis.close();
                httpUrl.disconnect();
            } catch (IOException e) {
            } catch (NullPointerException e) {
            }
        }
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        downimage dw=new downimage();
        dw.saveToFile("http://u.opercenter.com/152ea07702fb558353367de4d4c8b812");
    }
}
