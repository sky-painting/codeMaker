package com.coderman.codemaker.bean;

import com.coderman.codemaker.bean.plantuml.AbstractClassBean;
import lombok.Builder;
import lombok.Data;

/**
 * Description:
 * date: 2021/7/2
 *
 * @author fanchunshuai
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
}
