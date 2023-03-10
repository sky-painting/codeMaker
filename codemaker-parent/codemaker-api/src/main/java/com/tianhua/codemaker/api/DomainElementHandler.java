package com.tianhua.codemaker.api;

import com.tianhua.codemaker.bean.plantuml.PlantUmlContextBean;

/**
 * Description:
 * date: 2021/6/29
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public interface DomainElementHandler<T> {

    T getElementBeanList(PlantUmlContextBean plantUmlContextBean);
}
