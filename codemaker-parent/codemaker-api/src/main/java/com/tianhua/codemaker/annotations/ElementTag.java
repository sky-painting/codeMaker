package com.tianhua.codemaker.annotations;

import com.tianhua.codemaker.enums.ClassEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description:
 * date: 2022/1/17
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ElementTag {
    /**
     * 代码元素类型---TemplateFileEnum
     * @return
     */
    String elementName() default "";

    /**
     * 代码元素类型---ClassEnum
     * @return
     */
    ClassEnum clazzType() default ClassEnum.CLASS;

}
