package com.coderman.codemaker.service.plantuml;

import com.coderman.codemaker.bean.plantuml.ClassBean;
import com.coderman.codemaker.bean.plantuml.EnumBean;
import com.coderman.codemaker.bean.plantuml.InterfaceBean;
import com.coderman.codemaker.bean.plantuml.PackageBean;

import java.util.List;

/**
 * Description:
 * date: 2021/7/2
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public interface PlantUMLService {
    /**
     * 读取plantUML中的class信息
     * @param packageName 可选值，如果不为空则基于包模式解析，否则基于类模式
     * @return
     */
    List<ClassBean> readClassBeanList(String packageName);

    /**
     * 读取plantUML中的enum信息
     * @param packageName  可选值，如果不为空则基于包模式解析，否则基于枚举模式
     * @return
     */
    List<EnumBean> readEnumBeanList(String packageName);

    /**
     * 读取plantUMl中的interface信息
     * @param packageName 可选值，如果不为空则基于包模式解析，否则基于包模式
     * @return
     */
    List<InterfaceBean> readInterfaceBeanList(String packageName);

    /**
     * 获取plantuml中的所有包列表
     * @return
     */
    List<String> getPackageList();

    /**
     * 通过package模式读取解析plantuml内容
     * @return
     */
    List<PackageBean> readPacakge();

}
