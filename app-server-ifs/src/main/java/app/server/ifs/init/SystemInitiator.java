package app.server.ifs.init;

import javax.servlet.ServletContext;

/**
 * 记录初始化系统信息
 */
public class SystemInitiator {

    static private SystemInfo systemInfo = null;

    static public SystemInfo getSystemInfo(){
        return systemInfo;
    }

    public static void setSystemInfo(SystemInfo systemInfo){

        SystemInitiator.systemInfo = systemInfo;
    }

    public static void initApp(ServletContext ctx){
        // get file seperator
        String FS = System.getProperty ("file.separator");

        // get system name configed in web.xml
        String systemName = ctx.getInitParameter ("system-name");

        System.out.println ("systemName="+systemName);    
        // get working dir
        String work_dir = System.getProperty ("user.dir");

        // set conf path
        String confPath = work_dir + FS + systemName + FS + ctx.getInitParameter ("system.config-path-name");

        // set log path
        String logPath = work_dir + FS + systemName + FS + ctx.getInitParameter ("system.log-path-name");

        systemInfo = new SystemInfo (systemName,confPath,logPath);
        System.out.println (systemInfo.toString ());
    }
}
