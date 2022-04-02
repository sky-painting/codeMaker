package com.tianhua.codemaker.service.expertest;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;

/**
 * Description:
 * date: 2021/12/22
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public class ClassLoaderTest2 {
    public static void main(String[] args)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
            SecurityException, ClassNotFoundException, InstantiationException, MalformedURLException {
//         String path =
//         "/xx/.m2/repository/org/apache/commons/commons-lang3/3.1/commons-lang3-3.1.jar";
        String path = "commons-lang3-3.1.jar";//jar文件需放在工程目录下


        Class<?> aClass = Class.forName("org.apache.commons.lang3.StringUtils");
        Object instance = aClass.newInstance();
        Object strip = aClass.getDeclaredMethod("strip", String.class, String.class).invoke(instance,
                "[1,2,3,4,5,6,2,3,5,1]", "[]");
        System.out.println(strip);

    }

}
