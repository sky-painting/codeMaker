package com.tianhua.codemaker.element;

import com.tianhua.codemaker.bean.plantuml.AbstractClassBean;
import com.tianhua.codemaker.bean.plantuml.PlantUmlContextBean;

import java.util.Map;

/**
 * Description:代码元素装饰处理器，抽象接口
 * date: 2021/11/26
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public interface ElementDecorateHandler {
    /**
     * 代码元素包装处理接口
     * @param classCompMap
     * @param plantUmlContextBean
     */
    void decorateElement(Map<String, AbstractClassBean> classCompMap, PlantUmlContextBean plantUmlContextBean );
}
