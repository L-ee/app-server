package app.server.ifs.init;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import app.server.ifs.utils.PropertiesConfig;

/**
 * 系统初始化监听
 */
public class InitSystemListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent sce){
        // TODO Auto-generated method stub
    }

    public void contextInitialized(ServletContextEvent sce){
        ServletContext ctx = sce.getServletContext();
        SystemInitiator.initApp(ctx);

        URLMapConfig config = URLMapConfig.getInstance();
        config.initMapItem(ctx.getInitParameter("system.apiurl-confi"));

        System.out.println("Initial conf.properties ...");
        initPropertesFile(ctx.getInitParameter("system.conf.properties"));

    }


    
    private void initPropertesFile(String path){
        PropertiesConfig.getInstance().initPropertiesConfig(path);
    }
    
    
}
