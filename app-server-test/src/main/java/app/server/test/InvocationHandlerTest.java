package app.server.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Author:  Administrator
 * Date:    2016/7/7 21:21
 * Function:Please input the function of this class!
 */
public class InvocationHandlerTest implements InvocationHandler {

    private Object proxied;
    public InvocationHandlerTest(Object proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("*** proxy:" + proxy.getClass() +" ,method:" + method+ ",args:" + args);
        if(args != null){
            for (Object arg : args) {
                System.out.println("  " + arg);
            }
        }

        return method.invoke(proxy,args);
    }
}
