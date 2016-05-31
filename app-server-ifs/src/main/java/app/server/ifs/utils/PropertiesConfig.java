package app.server.ifs.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

import app.server.ifs.init.SystemInitiator;

public class PropertiesConfig {

    private static Logger logger = Logger.getLogger(PropertiesConfig.class);
    private static final Map<String,Object> propertieMap = new HashMap<String,Object> ();
    private PropertiesConfig() {}  
    private static PropertiesConfig single=null;  
    //静态工厂方法   
    public static PropertiesConfig getInstance() {  
         if (single == null) {    
             single = new PropertiesConfig();  
         }    
        return single;  
    }  
    
    public void initPropertiesConfig(String path){
        logger.info ("init propertes file ----------------------------");
        String FS = System.getProperty ("file.separator");
        String pathFile = SystemInitiator.getSystemInfo ().getConfPath () + FS + path;
        Properties prop = new Properties();
      //获取输入流
        InputStream in;
        try {
            in = new FileInputStream (new File(pathFile));
            //加载进去
            prop.load(in);
            Set keyValue = prop.keySet();
            for (Iterator it = keyValue.iterator(); it.hasNext();)
            {
                String key = (String) it.next();
                String value = prop.getProperty(key).trim(); 
//                logger.info (key+"="+value);
                propertieMap.put (key, value);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.error ("init propertes file  error :", e);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error ("init propertes file  error :", e);
        }

    }
    
    public static final String getValueByKey(String key){
        return (String) propertieMap.get (key);
    }
     

}