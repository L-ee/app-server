package app.server.ifs.init;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class URLMapConfig {

    public static Map<String, String> map = new ConcurrentHashMap<String, String>();
    public static Map<String, String> valiTokenUrlMap = new ConcurrentHashMap<String, String>();

    private static URLMapConfig instance = null;

    /*
     * 生成配置对象实例
     */
    public synchronized static URLMapConfig getInstance() {
        if (instance == null) {
            instance = new URLMapConfig();
        }
        return instance;
    }


    public void initMapItem(String welcomeFileName) {
        try {
            String FS = System.getProperty("file.separator");
            String welcomeFile = SystemInitiator.getSystemInfo().getConfPath() + FS + welcomeFileName;
            File file = new File(welcomeFile);

            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            StringBuffer sb = new StringBuffer(100);
            String str;
            boolean firstLine = true;
            while ((str = br.readLine()) != null) {
                if (firstLine) {
                    sb.append(str);
                    firstLine = false;
                } else {
                    sb.append("\n").append(str);
                }
            }
            System.out.println(sb.toString());

            JSONObject json = JSONObject.parseObject(sb.toString());
            Set<String> it = json.keySet();
            for (String jsonKey : it) {
                JSONArray jsonArray = json.getJSONArray(jsonKey);
                if (null != jsonArray) {
                    for (int i = 0; i < jsonArray.size(); i++) {
                        JSONObject urlJson = jsonArray.getJSONObject(i);
                        Set<String> urlSet = urlJson.keySet();
                        for (String apiUrl : urlSet) {
                            this.map.put(apiUrl, jsonKey + apiUrl);
                            this.valiTokenUrlMap.put(apiUrl, urlJson.getString(apiUrl));
                        }

                    }
                }
            }

            br.close();
            fileReader.close();
        } catch (Exception e) {
            System.out.println("  Read welcome file error:" + e.getMessage());
        }

    }

    /**
     * @param key
     * @return
     * @Title: getValueByKey
     * @Description: 根据key取得值
     * @Author: Administrator
     */
    public static String getValueByKey(String key) {
        if (null != map) {
            String item = map.get(key);
            return item;
        }
        return null;
    }

    /**
     * @param url
     * @return
     * @Title: getValueByKey
     * @Description: 根据key取得值
     * @Author: Administrator
     */
    public static String getValidateTokenFlagByUrl(String url) {
        if (null != map) {
            String item = valiTokenUrlMap.get(url);
            return item;
        }
        return null;
    }

}
