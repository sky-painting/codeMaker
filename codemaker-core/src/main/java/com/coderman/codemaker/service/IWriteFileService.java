package com.coderman.codemaker.service;

import com.coderman.codemaker.bean.WriteContentBean;

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
     * @param writeContentBean
     */
    void writeContent(WriteContentBean writeContentBean);

    /**
     * 一次性生成单表需要的所有模块代码
     *
     * @param humpClassName
     * @param varMap
     */
    void writeAllContent(String humpClassName, Map<String, Object> varMap, String fast);

    /**
     * 写公共基础服务类
     *
     * @param varMap
     */
    void writeCommonContent(Map<String, Object> varMap, String fast);


}
