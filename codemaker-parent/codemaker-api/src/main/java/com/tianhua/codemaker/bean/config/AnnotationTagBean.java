package com.tianhua.codemaker.bean.config;

import com.tianhua.codemaker.enums.dynamic.AnnotationModifyOnEnum;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * Description:
 * date: 2022/1/24
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Data
public class AnnotationTagBean {
    /**
     * 注解所在包
     */
    private String packageName;
    /**
     * 注解在plantuml 文档中的别名
     */
    private String classAlias;
    /**
     * 注解在方法或者类上的展示形式
     */
    private String annotation;

    /**
     * 注解类名称
     */
    private String className;

    /**
     * 注解需要装饰的目标
     */
    private String modifyOn;


    public boolean modifyOnThis(){
        if(StringUtils.isEmpty(modifyOn)){
            return true;
        }

        return modifyOn.equals(AnnotationModifyOnEnum.THIS.getCode());
    }



}