package com.coderman.codemaker.api;

import com.coderman.codemaker.bean.component.ComponentContextBean;
import com.coderman.codemaker.bean.plantuml.PlantUmlContextBean;

/**
 * Description: 组件规则定义完成之后，需要将
 * 组件包相关的类接口枚举注解等应用到对应的代码元素上
 * date: 2021/11/23
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@FunctionalInterface
public interface ICompDecorateService {
    /**
     * 组件上下文与代码模型上下文进行融合，对相关的代码元素做包装和装饰
     * @param componentContextBean  组件上下文
     * @param plantUmlContextBean   代码元素模型上下文
     */
    void decorateComp(ComponentContextBean componentContextBean, PlantUmlContextBean plantUmlContextBean);
}
