package test;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by loovee1 on 2016/4/20.
 */
public class TestServer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {


        System.out.println("================================");

        ServletContext context = servletContextEvent.getServletContext();

        String initParameter = context.getInitParameter("system-name");

        System.out.println(initParameter);




    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
