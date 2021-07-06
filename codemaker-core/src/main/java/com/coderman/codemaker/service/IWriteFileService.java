package com.coderman.codemaker.service;

import java.util.Map;

/**
 * Description:
 * date: 2021/6/18
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public interface IWriteFileService {


    /**
     * 抽象写模块class文件
     * @param templateName
     * @param content
     * @param humpClassName
     */
    public void writeContent(String templateName, String content, String humpClassName);

    /**
     * 一次性生成单表需要的所有模块代码
     *
     * @param humpClassName
     * @param varMap
     */
    public void writeAllContent(String humpClassName, Map<String, Object> varMap, String fast);

    /**
     * 写公共基础服务类
     *
     * @param varMap
     */
    public void writeCommonContent(Map<String, Object> varMap, String fast);


}