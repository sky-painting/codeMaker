package com.tianhua.codemaker.api;

import com.tianhua.codemaker.bean.plantuml.PlantUmlContextBean;

/**
 * Description:参数校验逻辑植入服务接口
 *
 * date: 2022/1/25
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public interface IValidateService {

    /**
     * 统一参数校验模式
     * @param plantUmlContextBean
     */
    void dealValidate(PlantUmlContextBean plantUmlContextBean);

}
