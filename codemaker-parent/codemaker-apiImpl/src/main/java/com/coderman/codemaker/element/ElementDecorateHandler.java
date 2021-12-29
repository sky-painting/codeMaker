package com.coderman.codemaker.element;

import com.coderman.codemaker.bean.plantuml.AbstractClassBean;
import com.coderman.codemaker.bean.plantuml.PlantUmlContextBean;

import java.util.Map;

/**
 * Description:代码元素装饰处理器，抽象接口
 * date: 2021/11/26
 *
 * @author fanchunshuai
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
