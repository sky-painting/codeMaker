package com.coderman.codemaker.classloader;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * date: 2021/11/22
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public class CompClassLoader extends URLClassLoader {


    public CompClassLoader(final String path, final ClassLoader parent) throws MalformedURLException {
        super(new URL[]{ new URL(path) }, parent);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String path = "jar:file:///Users/dasouche/.m2/repository/com/souche/trade/api/7.0.0-SNAPSHOT/api-7.0.0-SNAPSHOT.jar!/";
        CompClassLoader myClassLoader = new CompClassLoader(path, Thread.currentThread().getContextClassLoader().getParent());


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
        myClassLoader.close();
    }


    /**
     * 批量加载类
     * @param classList
     * @return
     */
    public List<Class<?>> loadClassList(List<String> classList){
        if(CollectionUtils.isEmpty(classList)){
            return null;
        }
        System.out.println(JSON.toJSONString(classList));
        List<Class<?>> list = new ArrayList<>();
        for (String classNamePath : classList){
            Class clazz = loadTargetClass(classNamePath);
            list.add(clazz);
        }
        return list;
    }

    /**
     * 从jar包中加载具体类
     * @param classPath
     * @return
     */
    private Class loadTargetClass(String classPath){
        try {
            return Thread.currentThread()
                    .getContextClassLoader()
                    .loadClass(classPath);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}
