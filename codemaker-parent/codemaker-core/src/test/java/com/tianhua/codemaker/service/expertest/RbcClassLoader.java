package com.tianhua.codemaker.service.expertest;

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
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public class RbcClassLoader extends URLClassLoader {
    public RbcClassLoader(final String path, final ClassLoader parent) throws MalformedURLException {
        super(new URL[]{ new URL(path) }, parent);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
       //delete test code  
    }

}
