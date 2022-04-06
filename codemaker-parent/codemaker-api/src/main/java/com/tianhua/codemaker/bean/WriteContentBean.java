package com.tianhua.codemaker.bean;

import com.tianhua.codemaker.bean.plantuml.AbstractClassBean;
import com.tianhua.codemaker.enums.TemplateFileEnum;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * Description:
 * date: 2021/7/2
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Builder
@Data
public class WriteContentBean {
    private String templateName;
    private String content;
    private String humpClassName;
    private String classPackageName;

    /**
     * 是否是自定义的代码元素
     */
    private boolean customCodeElement;

    public boolean isCustomCodeElement() {
        return customCodeElement;
    }

    public void setCustomCodeElement(boolean customCodeElement) {
        this.customCodeElement = customCodeElement;
    }

    private AbstractClassBean abstractClassBean;

    public AbstractClassBean getAbstractClassBean() {
        return abstractClassBean;
    }

    public void setAbstractClassBean(AbstractClassBean abstractClassBean) {
        this.abstractClassBean = abstractClassBean;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHumpClassName() {
        return humpClassName;
    }

    public void setHumpClassName(String humpClassName) {
        this.humpClassName = humpClassName;
    }

    /**
     * 将writeContentbean对象转换为classcontentBean对象
     * @return
     */
    public ClassContentBean buildClassContentBean(String modulePath){
        ClassContentBean classContentBean = new ClassContentBean();
        classContentBean.setClassContent(this.getContent());
        classContentBean.setHumpClassName(this.getHumpClassName());
        classContentBean.setClassPackageName(this.getClassPackageName());
        classContentBean.setClassSuffix("");
        classContentBean.setModulePath(modulePath);
        return classContentBean;
    }

    /**
     * 将writeContentbean对象转换为classcontentBean对象
     * @param moduleCode
     * @param modulePath
     * @return
     */
    public ClassContentBean buildClassContentBean(String moduleCode, String modulePath){
        ClassContentBean classContentBean = new ClassContentBean();
        classContentBean.setClassContent(this.getContent());
        classContentBean.setHumpClassName(this.getHumpClassName());
        classContentBean.setClassPackageName(this.getClassPackageName());
        String classSuffix = TemplateFileEnum.getClassSuffix(this.getTemplateName());
        if(StringUtils.isNotEmpty(classSuffix)){
            classContentBean.setClassSuffix(classSuffix);
        }else {
            classContentBean.setClassSuffix("");
        }

        classContentBean.setModulePath(modulePath);
        String defaultChildPackageName = TemplateFileEnum.getChildPackageName(this.getTemplateName());

        String childPackageName;
        if(defaultChildPackageName.startsWith(moduleCode)){
            childPackageName = defaultChildPackageName;
        }else {
            childPackageName = moduleCode + "." + defaultChildPackageName;

        }
        classContentBean.setChildPackageName(childPackageName);

        return classContentBean;
    }


}
