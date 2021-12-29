

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Description:
 * date: 2021/11/22
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public class RbcClassLoader extends URLClassLoader {
    public RbcClassLoader(final String path, final ClassLoader parent) throws MalformedURLException {
        super(new URL[]{ new URL(path) }, parent);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String path = "jar:file:///Users/dasouche/.m2/repository/com/souche/trade/api/7.0.0-SNAPSHOT/api-7.0.0-SNAPSHOT.jar!/";
        RbcClassLoader myClassLoader = new RbcClassLoader(path, Thread.currentThread().getContextClassLoader().getParent());
        Thread.currentThread().setContextClassLoader(myClassLoader);

        Class clazz = Thread.currentThread()
                .getContextClassLoader()
                .loadClass("com.souche.trade.api.fullorder.TradeFullOrderFacade");
        System.out.println(clazz.getName());
        for (Method method : clazz.getMethods()){
            System.out.println(method.getName());
            method.getParameterTypes();
            for (Class<?> paramType : method.getParameterTypes()){
                System.out.println( paramType.getName());
            }
        }

        Class dtoClass = Thread.currentThread()
                .getContextClassLoader()
                .loadClass("com.souche.trade.api.fullorder.req.CreateMainOrderRequestDTO");
        System.out.println(dtoClass.getName());
        for (Field field : dtoClass.getDeclaredFields()){
            System.out.println(field.getName());
        }
    }

}
