package com.tianhua.codemaker.service;

import com.tianhua.codemaker.bean.plantuml.AbstractClassBean;
import com.tianhua.codemaker.bean.plantuml.PlantUmlContextBean;

/**
 * Description:
 * date: 2022/1/26
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public interface ImportPackageService {

    /**
     * 处理需要导入的包
     *
     * @param abstractClassBean
     * @param plantUmlContextBean
     */
    void dealImportClass(AbstractClassBean abstractClassBean, PlantUmlContextBean plantUmlContextBean);


    /**
     * 设置包名
     *
     * @param abstractClassBean
     * @param defaultChildPackage 当plantUML中没有包名则使用配置的全局包名+子包名
     */
    void setPackageName(AbstractClassBean abstractClassBean, String defaultChildPackage);


    /**
     * 在动态调用中处理引用包
     *
     * @param invokerClassBean
     * @param plantUmlContextBean
     * @param importClassName
     */
    void dealImportPackage(AbstractClassBean invokerClassBean, PlantUmlContextBean plantUmlContextBean, String importClassName);


    String getMatchPackageDefault(String targetClass);


    /**
     * 设置包名
     * @param abstractClassBean
     * @param moduleTag 当前类元素所在模块
     * 返回的包路径简化为
     * 全局包路径+模块标示+plantUMLPackage
     */
    public void setPackageNameWithModule(AbstractClassBean abstractClassBean, String moduleTag);


}

