package com.tianhua.codemaker.bean.component;

import com.tianhua.codemaker.bean.plantuml.AnnotationBean;
import com.tianhua.codemaker.bean.plantuml.ClassBean;
import com.tianhua.codemaker.bean.plantuml.EnumBean;
import com.tianhua.codemaker.bean.plantuml.InterfaceBean;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Optional;

/**
 * Description:组件上下文bean
 * date: 2021/11/22
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public class ComponentContextBean {

    /**
     * 组件名称---服务名称
     */
    private String componentName;

    /**
     * 组件类型--CompTypeEnum
     */
    private String compType;


    /**
     * 组件需要暴露的枚举类
     */
    private List<EnumBean> enumBeanList;

    /**
     * 组件需要暴露的class类
     */
    private List<ClassBean> classBeanList;

    /**
     * 组件需要暴露的接口类
     */
    private List<InterfaceBean> interfaceBeanList;

    /**
     * 注解列表
     */
    private List<AnnotationBean> annotationList;

    /**
     * 组件配置信息
     */
    private ComponentConfigBean componentConfigBean;

    public ComponentConfigBean getComponentConfigBean() {
        return componentConfigBean;
    }

    public void setComponentConfigBean(ComponentConfigBean componentConfigBean) {
        this.componentConfigBean = componentConfigBean;
    }

    public List<AnnotationBean> getAnnotationList() {
        return annotationList;
    }

    public void setAnnotationList(List<AnnotationBean> annotationList) {
        this.annotationList = annotationList;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public List<EnumBean> getEnumBeanList() {
        return enumBeanList;
    }

    public void setEnumBeanList(List<EnumBean> enumBeanList) {
        this.enumBeanList = enumBeanList;
    }

    public List<ClassBean> getClassBeanList() {
        return classBeanList;
    }

    public void setClassBeanList(List<ClassBean> classBeanList) {
        this.classBeanList = classBeanList;
    }

    public List<InterfaceBean> getInterfaceBeanList() {
        return interfaceBeanList;
    }

    public void setInterfaceBeanList(List<InterfaceBean> interfaceBeanList) {
        this.interfaceBeanList = interfaceBeanList;
    }

    public String getCompType() {
        return compType;
    }

    public void setCompType(String compType) {
        this.compType = compType;
    }

    @Override
    public String toString() {
        return "ComponentContextBean{" +
                "componentName='" + componentName + '\'' +
                ", enumBeanList=" + enumBeanList +
                ", classBeanList=" + classBeanList +
                ", interfaceBeanList=" + interfaceBeanList +
                '}';
    }

    /**
     * 获取目标注解信息
     * @param annotationClass
     * @return
     */
    public AnnotationBean getTargetAnnotation(String annotationClass){
        if(CollectionUtils.isEmpty(this.getAnnotationList())){
            return null;
        }
        Optional<AnnotationBean> annotationBeanOptional = this.getAnnotationList().stream().filter(annotationBean -> annotationBean.getClassName().equals(annotationClass)).findFirst();
        if(annotationBeanOptional.isPresent()){
            return annotationBeanOptional.get();
        }
        return null;
    }

    /**
     * 获取目标interface信息
     * @param className
     * @return
     */
    public InterfaceBean getTargetInterface(String className){
        if(CollectionUtils.isEmpty(this.getInterfaceBeanList())){
            return null;
        }
        Optional<InterfaceBean> classBeanOptional = this.getInterfaceBeanList().stream().filter(classBean -> classBean.getClassName().equals(className)).findFirst();
        if(classBeanOptional.isPresent()){
            return classBeanOptional.get();
        }
        return null;
    }

    /**
     * 获取目标class信息
     * @param className
     * @return
     */
    public ClassBean getTargetClass(String className){
        if(CollectionUtils.isEmpty(this.getClassBeanList())){
            return null;
        }
        Optional<ClassBean> classBeanOptional = this.getClassBeanList().stream().filter(classBean -> classBean.getClassName().equals(className)).findFirst();
        if(classBeanOptional.isPresent()){
            return classBeanOptional.get();
        }
        return null;
    }

    /**
     * 判断扫描出来的组件是否为空
     * @return
     */
    public boolean isEmpty(){
        return   CollectionUtils.isEmpty(this.getAnnotationList()) && CollectionUtils.isEmpty(this.getEnumBeanList()) && CollectionUtils.isEmpty(this.getClassBeanList()) && CollectionUtils.isEmpty(this.getInterfaceBeanList());
    }
}
