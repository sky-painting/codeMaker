package com.tianhua.codemaker.service.expertest;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * Description:
 * date: 2021/11/22
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public class ClassLoaderTest  extends ClassLoader {

    private final static String filePathSuffix = ".class";
    private String filePathPrefix;

    public ClassLoaderTest(String filePathPrefix) {
        this.filePathPrefix = filePathPrefix;
    }

    @Override
    protected Class<?> findClass(String name) {
        String fileName = name.split("\\.")[name.split("\\.").length - 1];
        byte[] bytes = loadClassData(filePathPrefix + fileName + filePathSuffix);
        return defineClass(name, bytes, 0, bytes.length);
    }

    private byte[] loadClassData(String filePath) {
        InputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            in = new FileInputStream(new File(filePath));
            out = new ByteArrayOutputStream();
            int i = 0;
            while ((i = in.read()) != -1) {
                out.write(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return out.toByteArray();
    }

    public static void main(String[] args) throws Exception {

        ClassLoaderTest clt = new ClassLoaderTest("D:/");
        Class c = clt.loadClass("com.Hello");
        System.out.println(c.getClassLoader());
        System.out.println(c.getClassLoader().getParent());
        System.out.println(c.getClassLoader().getParent().getParent());
        System.out.println(c.getClassLoader().getParent().getParent().getParent());
        Method sayHi = c.getMethod("sayHi", String.class);
        // 无参实例化
        Object o = c.newInstance();
        // 调用方法
        sayHi.invoke(o, "zhangsan");
    }
}
