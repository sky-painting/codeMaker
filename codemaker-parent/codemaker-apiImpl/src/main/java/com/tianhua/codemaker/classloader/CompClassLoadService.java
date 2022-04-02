package com.tianhua.codemaker.classloader;

import com.tianhua.codemaker.bean.component.ComponentConfigBean;
import com.tianhua.codemaker.bean.component.ComponentContextBean;
import com.tianhua.codemaker.bean.plantuml.AnnotationBean;
import com.tianhua.codemaker.bean.plantuml.ClassBean;
import com.tianhua.codemaker.bean.plantuml.InterfaceBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Description:
 * date: 2021/11/22
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
//@Slf4j
public class CompClassLoadService {
    /** jar中的文件路径分隔符 */
    private static final char SLASH_CHAR = '/';
    /** 包名分隔符 */
    private static final char DOT_CHAR = '.';

    @Value(value = "${application.maven.repo.path}")
    private String mavenRepoPath;


    @Autowired
    private CompClassFactory compClassFactory;

    /**
     * 通过类加载机制加载组件内需要被依赖的class,enum,annotation,interfance
     * @param componentConfigBean
     * @return
     */
    public ComponentContextBean loadComponent(ComponentConfigBean componentConfigBean){

        ComponentContextBean componentContextBean = new ComponentContextBean();
        CompClassLoader classLoader = buildClassLoader(componentConfigBean);
        List<Class<?>> classList = classLoader.loadClassList(componentConfigBean.getClassList());
        List<ClassBean> classBeanList = compClassFactory.convert2ClassBean(classList, componentConfigBean);
        componentContextBean.setClassBeanList(classBeanList);


        List<Class<?>> interfaceList = classLoader.loadClassList(componentConfigBean.getInterfaceList());
        List<InterfaceBean> interfaceBeanList = compClassFactory.convert2InterfaceBean(interfaceList,componentConfigBean.getServiceName());
        componentContextBean.setInterfaceBeanList(interfaceBeanList);

        //注解
        componentContextBean.setAnnotationList(compClassFactory.convert2AnnotationBean(componentConfigBean.getAnnotationList()));

        //如果具体类没有扫描到，则进行包扫描，递归扫描组件的包内容
        if(componentContextBean.isEmpty()){
            try {
                return loadComp(componentConfigBean);
            } catch (IOException e) {
                return componentContextBean;
            } catch (ClassNotFoundException e) {
                return componentContextBean;
            }
        }
        return componentContextBean;
    }


    /**
     * 根据组件模型构建一个类加载器
     * @param componentConfigBean
     * @return
     */
    private CompClassLoader buildClassLoader(ComponentConfigBean componentConfigBean){
        CompClassLoader myClassLoader = null;
        try {

            myClassLoader = new CompClassLoader(componentConfigBean.buildRepoJarPath(mavenRepoPath), Thread.currentThread().getContextClassLoader().getParent());
            Thread.currentThread().setContextClassLoader(myClassLoader);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return myClassLoader;
    }

    /**
     * 扫描组件指定包下所有类
     * @param componentConfigBean
     * @return
     */
    private ComponentContextBean loadComp(ComponentConfigBean componentConfigBean) throws IOException, ClassNotFoundException {
        ComponentContextBean componentContextBean = new ComponentContextBean();

        String jarPath  = componentConfigBean.buildJarPath(mavenRepoPath);
        URL[] urls = new URL[1];
        urls[0] = new URL("file:" + jarPath);

        /**
         * 组件需要暴露的枚举类
         */
        List<Class<?>> enumClassList = new ArrayList<>(128);

        /**
         * 组件需要暴露的class类
         */
        List<Class<?>> classList = new ArrayList<>(128);

        /**
         * 组件需要暴露的接口类
         */
        List<Class<?>> interfaceClassList =  new ArrayList<>(128);

        /**
         * 注解列表
         */
        List<Class<?>> annotationClassList = new ArrayList<>(128);

        URLClassLoader classLoader = new URLClassLoader(urls);
        JarFile jarfile = new JarFile(jarPath);
        Enumeration<JarEntry> iterator = jarfile.entries();
        while (iterator.hasMoreElements()) {
            //这里拿到的一般的"aa/bb/.../cc.class"格式的Entry或 "包路径"
            JarEntry jarEntry = iterator.nextElement();
            if (!jarEntry.isDirectory()) {
                String name = jarEntry.getName();
                //对于拿到的文件,要去除末尾的.class
                int lastDotClassIndex = name.lastIndexOf(".class");
                if(lastDotClassIndex != -1) {
                    name = name.replace(SLASH_CHAR, DOT_CHAR);
                    Class clazz = classLoader.loadClass(name.replace(".class",""));
                    //如果配置了包名，则按报名扫描类
                    if(StringUtils.isNotEmpty(componentConfigBean.getPackageName()) && clazz.getPackage().getName().contains(componentConfigBean.getPackageName())){
                        if(clazz.isAnnotation()){
                            annotationClassList.add(clazz);
                        }
                        else if(clazz.isInterface()){
                            interfaceClassList.add(clazz);
                        }else if(clazz.isArray() || clazz.isMemberClass()){
                            classList.add(clazz);
                        }else if(clazz.isEnum()){
                            enumClassList.add(clazz);
                        }else if(!clazz.getName().contains("$")){
                            classList.add(clazz);
                        }
                    }else {
                        if(clazz.isAnnotation()){
                            annotationClassList.add(clazz);
                        }
                        else if(clazz.isInterface()){
                            interfaceClassList.add(clazz);
                        }else if(clazz.isArray()){
                            classList.add(clazz);
                        }else if(clazz.isEnum()){
                            enumClassList.add(clazz);
                        }else if(!clazz.getName().contains("$")){
                            classList.add(clazz);
                        }
                    }
                }
            }
        }
        List<ClassBean> classBeanList = compClassFactory.convert2ClassBean(classList,componentConfigBean);
        List<InterfaceBean> interfaceBeanList = compClassFactory.convert2InterfaceBean(interfaceClassList,componentConfigBean.getServiceName());
        List<AnnotationBean> annotationBeanList = compClassFactory.convert2AnnotationBeanClass(annotationClassList);
        //todo 枚举
        componentContextBean.setClassBeanList(classBeanList);
        componentContextBean.setInterfaceBeanList(interfaceBeanList);
        componentContextBean.setAnnotationList(annotationBeanList);
        return componentContextBean;
    }

}
