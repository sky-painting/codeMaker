package com.tianhua.codemaker.custom.decorate.mybatisplus;

import com.tianhua.codemaker.api.ICompDecorateService;
import com.tianhua.codemaker.bean.component.ComponentContextBean;
import com.tianhua.codemaker.bean.plantuml.AbstractClassBean;
import com.tianhua.codemaker.bean.plantuml.InterfaceBean;
import com.tianhua.codemaker.bean.plantuml.PlantUmlContextBean;
import com.tianhua.codemaker.element.ElementDecorateHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:将代码模型中的mapper元素通过mybatis-plus组件装饰
 * date: 2022/3/1
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component(value = "mybatisPlusDecorateService")
public class MybatisPlusDecorateServiceImpl  implements ICompDecorateService {
    private static final String BASE_MAPPER = "BaseMapper";

    @Resource(name = "mapperplusDecorateHandler")
    private ElementDecorateHandler mapperDecorateHandler;

    @Override
    public void decorateComp(ComponentContextBean componentContextBean, PlantUmlContextBean plantUmlContextBean) {
        InterfaceBean baseMapper = componentContextBean.getTargetInterface(BASE_MAPPER);
        Map<String, AbstractClassBean> interfaceBeanMap = new HashMap<>();
        interfaceBeanMap.put(BASE_MAPPER, baseMapper);

        mapperDecorateHandler.decorateElement(interfaceBeanMap, plantUmlContextBean);
    }
}
