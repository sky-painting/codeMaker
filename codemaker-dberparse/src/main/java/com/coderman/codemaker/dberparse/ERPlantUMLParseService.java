package com.coderman.codemaker.dberparse;

import com.coderman.codemaker.dberparse.bean.EntityBean;
import com.coderman.codemaker.dberparse.bean.ParseRequestBean;

import java.util.List;

/**
 * Description:
 * date: 2021/8/12
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public interface ERPlantUMLParseService {
    /**
     * e-r图解析入口
     * @param contentList
     * @return 解析完成的DDL语句
     */
    List<String> parseERPlantUML(List<String> contentList);

    /**
     * 根据plantuml 内容构建数据库表模型
     * @param contentList
     * @return
     */
    List<EntityBean> getPlantUmlContextBean(List<String> contentList);


}
