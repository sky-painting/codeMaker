package com.tianhua.codemaker.app.dynamicddd;

import com.tianhua.codemaker.annotations.ElementTag;
import com.tianhua.codemaker.api.DomainElementHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * date: 2022/1/17
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
@Slf4j
public class ElementHandlerContainer implements ApplicationContextAware {

    private Map<String, DomainElementHandler> elementHandlerMap = new HashMap<>();


    /**
     * 根据代码元素
     * @param codeElement
     * @return
     */
    public DomainElementHandler getElementHandler(String codeElement) {
        return elementHandlerMap.get(codeElement);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Environment environment = applicationContext.getEnvironment();
        Map<String, Object> beansOfMQHandler = applicationContext.getBeansWithAnnotation(ElementTag.class);
        for (Map.Entry<String, Object> entry : beansOfMQHandler.entrySet()) {
            // 获取Handler中的代码元素标签
            ElementTag identifier = AopUtils.getTargetClass(entry.getValue()).getAnnotation(ElementTag.class);
            String elementTag = environment.resolveRequiredPlaceholders(identifier.elementName()).intern();
            if(elementTag.contains(",")){
                String [] elementArr = elementTag.split(",");
                //多个element共用一个代码元素处理器
                for (String element : elementArr){
                    elementHandlerMap.put(element, (DomainElementHandler) entry.getValue());
                }
            }else{
                elementHandlerMap.put(elementTag, (DomainElementHandler) entry.getValue());
            }

        }
        log.info(">>>>>代码元素处理标签", elementHandlerMap);
    }
}
