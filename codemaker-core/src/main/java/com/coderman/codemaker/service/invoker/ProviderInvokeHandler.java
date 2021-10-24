package com.coderman.codemaker.service.invoker;

import com.coderman.codemaker.bean.plantuml.PlantUmlContextBean;
import org.springframework.stereotype.Service;

/**
 * Description:
 * date: 2021/10/16
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
public class ProviderInvokeHandler {
    public String buildProviderMethod(String providerContent, PlantUmlContextBean plantUmlContextBean){
        String className = providerContent.trim().split("\\.")[0];
        String method = providerContent.trim().split("\\.")[1];
        return null;
    }


}