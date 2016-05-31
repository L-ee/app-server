package app.server.ifs.init;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import app.server.ifs.utils.PropertiesConfig;

/**
 * 系统初始化监听
 */
public class InitSystemListener implements ServletContextListener {

    private static final String FS = System.getProperty ("file.separator");

    public void contextDestroyed(ServletContextEvent sce){
        // TODO Auto-generated method stub

    }

    public void contextInitialized(ServletContextEvent sce){
        ServletContext ctx = sce.getServletContext ();
        System.out.println ("========================");
        System.out.println ("ifs: Initial system...");
        SystemInitiator.initApp (ctx);
        // print welcome.txt
       // System.out.println ("ifs: welcome info");
        processURLMapConfigFile (ctx.getInitParameter ("system.apiurl-confi"));
        
        
        System.out.println ("  Initial conf.properties ...");
        initPropertesFile(ctx.getInitParameter ("system.conf.properties"));

    }

    private void processURLMapConfigFile(String welcomeFileName){
        URLMapConfig config = URLMapConfig.getInstance ();
        config.initMapItem (welcomeFileName);
        /*String FS = System.getProperty ("file.separator");
        String welcomeFile = SystemInitiator.getSystemInfo ().getConfPath () + FS + welcomeFileName;
        File file = new File (welcomeFile);

        try {
            
            FileReader fileReader = new FileReader (file);
            BufferedReader br = new BufferedReader (fileReader);
            StringBuffer sb = new StringBuffer (100);
            String str;
            boolean firstLine = true;
            while ((str = br.readLine ()) != null) {
                if (firstLine) {
                    sb.append (str);
                    firstLine = false;
                } else {
                    sb.append ("\n").append (str);
                }
            }
            System.out.println (sb.toString ());
            JSONObject json = JSONObject.parseObject (sb.toString ());
            
            Set<String> it = json.keySet ();
            for ( String jsonKey : it ) {
                JSONArray jsonArray = json.getJSONArray (jsonKey);
                if (null!=jsonArray) {
                    for ( int i = 0 ; i < jsonArray.size () ; i++ ) {
                        String value = jsonArray.getString (i);
                        System.out.println ("value="+value);
                    }
                }
            }
            URLMapConfig config = URLMapConfig.getInstance ();
//            List<String> keyListstr = new ArrayList<String>();  
//            while(it.hasNext()){  
//                keyListstr.add(it.next().toString());  
//            }  
            
            
            
            br.close ();
            fileReader.close ();
        } catch (Exception e) {
            System.out.println ("  Read welcome file error:" + e.getMessage ());
        }*/
    }

    /**
     * @Title: initcheckParamXml checkParam.Xml
     * @Description: TODO( 初始化checkParam.Xml)
     * @Author: Administrator
     * @param ctx
     */
//    private void initcheckParamXml(ServletContext ctx){
//        String checkParamXmlFile = ctx.getInitParameter ("config.checkParams-file");
//        if (checkParamXmlFile == null || checkParamXmlFile.trim ().length () == 0) {
//            System.out.println ("  config.checkParams-file is not configed, so not initial!!! ");
//        } else {
//            // checkParamXmlFile =
//            // ConfigurationHelper.getFullFileName(checkParamXmlFile)+"/"+checkParamXmlFile;
//            checkParamXmlFile = SystemInitiator.getSystemInfo ().getConfPath () + FS + checkParamXmlFile;
//            // String fielPath = checkParamXmlFile.replace("/", "\\");
//            // 初始化 CheckParams.xml
//            try {
//                CheckParamsUtil.initXml (checkParamXmlFile);
//            } catch (Exception e) {
//                e.printStackTrace ();
//                System.out.println (" init checkParam.Xml error !");
//            }
//        }
//    }

    
    private void initPropertesFile(String path){
        PropertiesConfig.getInstance ().initPropertiesConfig(path);
    }
    
    
}
