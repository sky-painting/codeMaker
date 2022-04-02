package com.tianhua.codemaker.custom.decorate.liteflow;

import com.tianhua.codemaker.bean.plantuml.AbstractClassBean;
import com.tianhua.codemaker.bean.plantuml.PlantUmlContextBean;
import com.tianhua.codemaker.element.ElementDecorateHandler;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Description: NodeComponent代码元素包装
 * date: 2021/11/26
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component(value = "eleNodeCmpDecorateHandler")
public class EleNodeCmpDecorateHandler implements ElementDecorateHandler {
    private static final String NODE_COMPONENT = "NodeComponent";

    @Override
    public void decorateElement(Map<String, AbstractClassBean> classCompMap, PlantUmlContextBean plantUmlContextBean) {
        AbstractClassBean nodeCompClassBean = classCompMap.get(NODE_COMPONENT);
        if (nodeCompClassBean != null) {
            decorateNodeComponent(nodeCompClassBean, plantUmlContextBean);
        }
    }
    /**
     * 装饰SlotBO类
     * @param abstractClassBean
     * @param plantUmlContextBean
     */
    private void decorateNodeComponent(AbstractClassBean abstractClassBean, PlantUmlContextBean plantUmlContextBean) {
        plantUmlContextBean.getClassBeanMap().forEach((k, v) -> {
            if (k.toLowerCase().endsWith("cmp")) {
                //继承liteflow的NodeComponent类
                v.setRelationClassStr(" extends " + NODE_COMPONENT);
                v.addImportClass(abstractClassBean.getPackageName());
                v.setMethodBeanList(abstractClassBean.getMethodBeanList());

            }
        });
    }
}
