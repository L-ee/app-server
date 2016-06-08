package app.server.ifs.init;

import javax.servlet.ServletContext;

/**
 * 记录初始化系统信息
 */
public class SystemInitiator {

    private static SystemInfo systemInfo = null;

    public static SystemInfo getSystemInfo(){
        return systemInfo;
    }

    public static void setSystemInfo(SystemInfo systemInfo){
        SystemInitiator.systemInfo = systemInfo;
    }

    public static void initApp(ServletContext ctx){
        // 文件分割符
        String FS = System.getProperty("file.separator");
        // 工作目录
        //String work_dir = System.getProperty("user.dir");
        String path = ctx.getRealPath("/WEB-INF/classes");

        // 获取web.xml初始化值
        String systemName = ctx.getInitParameter("system-name");
        String confPath = path + FS + ctx.getInitParameter("system.config-path-name");
        String logPath  = path + FS + ctx.getInitParameter("system.log-path-name");
        systemInfo = new SystemInfo(systemName,confPath,logPath);
        System.out.println (systemInfo.toString());
    }
}
