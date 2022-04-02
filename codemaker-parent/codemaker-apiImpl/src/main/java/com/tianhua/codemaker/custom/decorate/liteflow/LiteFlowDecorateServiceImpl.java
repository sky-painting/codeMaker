package com.tianhua.codemaker.custom.decorate.liteflow;

import com.tianhua.codemaker.api.ICompDecorateService;
import com.tianhua.codemaker.bean.component.ComponentContextBean;
import com.tianhua.codemaker.bean.plantuml.AbstractClassBean;
import com.tianhua.codemaker.bean.plantuml.ClassBean;
import com.tianhua.codemaker.bean.plantuml.PlantUmlContextBean;
import com.tianhua.codemaker.element.ElementDecorateHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:将代码模型中的SlotBO和Cmp元素通过liteflow组件装饰
 * date: 2022/3/1
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component(value = "liteFlowDecorateService")
public class LiteFlowDecorateServiceImpl implements ICompDecorateService {
    private static final String NODE_COMPONENT = "NodeComponent";
    private static final String ABS_SLOT = "AbsSlot";

    @Resource(name = "eleSlotBODecorateHandler")
    private ElementDecorateHandler eleSlotBODecorateHandler;

    @Resource(name = "eleNodeCmpDecorateHandler")
    private ElementDecorateHandler eleNodeCmpDecorateHandler;

    @Override
    public void decorateComp(ComponentContextBean componentContextBean, PlantUmlContextBean plantUmlContextBean) {
        ClassBean compClassBean = componentContextBean.getTargetClass(NODE_COMPONENT);
        Map<String, AbstractClassBean> compBeanMap = new HashMap<>();
        compBeanMap.put(NODE_COMPONENT, compClassBean);

        ClassBean absSlotClassBean = componentContextBean.getTargetClass(ABS_SLOT);
        Map<String, AbstractClassBean> slotBeanMap = new HashMap<>();
        slotBeanMap.put(ABS_SLOT, absSlotClassBean);

        eleNodeCmpDecorateHandler.decorateElement(compBeanMap, plantUmlContextBean);
        eleSlotBODecorateHandler.decorateElement(slotBeanMap, plantUmlContextBean);
    }
}
