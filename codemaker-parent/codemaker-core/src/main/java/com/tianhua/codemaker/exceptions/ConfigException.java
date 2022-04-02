package com.tianhua.codemaker.exceptions;

/**
 * Description:
 * date: 2021/6/22
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public class ConfigException  extends Exception{

    //无参构造方法
    public ConfigException(){

        super();
    }

    //有参的构造方法
    public ConfigException(String message){
        super(message);

    }
}
