package com.coderman.codemaker.app.dynamicddd;

import com.coderman.codemaker.bean.plantuml.PlantUmlContextBean;

/**
 * Description:
 * date: 2021/6/29
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public interface DomainElementHandler<T> {

    T getElementBeanList(PlantUmlContextBean plantUmlContextBean);
}
