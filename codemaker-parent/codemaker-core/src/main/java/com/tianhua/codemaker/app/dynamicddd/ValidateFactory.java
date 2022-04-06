package com.tianhua.codemaker.app.dynamicddd;

/**
 * Description:参数校验模式实现工厂
 * date: 2022/1/24
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public class ValidateFactory {
    /**
     *
     *
     *
     *   1.在dto,vo中构建参数验证逻辑,-->代码片段模式
     *   2.独立构建一个validator包
     *   3.走aop+属性注解(相当于自定义实现)
     *   4.走工具类+反射的方式
     *   5.spring validator注解
     */

    /**
     * 4.走工具类+反射的方式
     * @return
     */
    public String buildByToolsAndReflection(){
        return null;
    }

    /**
     * 2.独立构建一个validator包
     * @return
     */
    public String buildByValidator(){
        return null;
    }

    /**
     * 1.在dto,vo中构建参数验证逻辑
     * @return
     */
    public String buildByInnerParam(){
        return null;
    }
}
