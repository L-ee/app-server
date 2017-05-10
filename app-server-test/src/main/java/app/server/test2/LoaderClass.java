package app.server.test2;

/**
 * Author:  Administrator
 * Date:    2017/3/30 22:38
 * Function:Please input the function of this class!
 */
public class LoaderClass {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        ClassLoader classLoader = LoaderClass.class.getClassLoader();
//        System.out.println(classLoader);

        Class<?> test2 = classLoader.loadClass("app.server.test2.Test2");
//        test2.newInstance();
//        System.out.println(test2);

        Class<?> aClass = Class.forName("app.server.test2.Test2");
        System.out.println();




    }


}
