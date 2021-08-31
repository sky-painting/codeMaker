package com.coderman.codemaker.dberparse;

import com.coderman.codemaker.dberparse.bean.ParseRequestBean;

import java.util.List;

/**
 * Description:
 * date: 2021/8/12
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public interface ERPantUMLParseService {
    /**
     * e-r图解析入口
     * @param contentList
     * @return 解析完成的DDL语句
     */
    List<String> parseERPlantUML(List<String> contentList);
}
