package app.server.ifs.init;

/**
 * 
 *@ClassName: SystemInfo
 *@Title:
 *@Description:TODO(这里用一句话描述这个类的作用)
 *@Author:tsingning
 *@Since:2016年1月26日下午7:45:16 
 *@Version:1.0
 */
public class SystemInfo {

    private String systemName = null;
    private String confPath   = null;
    private String logPath    = null;

    public SystemInfo(String systemName, String confPath, String logPath) {
        this.systemName = systemName;
        this.confPath = confPath;
        this.logPath = logPath;
    }

    public String getSystemName(){
        return systemName;
    }

    public String getConfPath(){
        return confPath;
    }

    public String getLogPath(){
        return logPath;
    }

    public String toString(){
        return this.getClass ().getName () + "[systemName=" + this.getSystemName () + ";confPath=" + this.getConfPath () + ";logPath=" + this.getLogPath () + "]";
    }
}