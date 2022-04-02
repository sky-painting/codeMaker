package com.tianhua.codemaker.custom.decorate.liteflow;

import com.tianhua.codemaker.bean.plantuml.AbstractClassBean;
import com.tianhua.codemaker.bean.plantuml.PlantUmlContextBean;
import com.tianhua.codemaker.element.ElementDecorateHandler;
import com.tianhua.codemaker.enums.TemplateFileEnum;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Description: slotBO代码元素包装
 * date: 2021/11/26
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component(value = "eleSlotBODecorateHandler")
public class EleSlotBODecorateHandler implements ElementDecorateHandler {
    private static final String ABS_SLOT = "AbsSlot";

    @Override
    public void decorateElement(Map<String, AbstractClassBean> classCompMap, PlantUmlContextBean plantUmlContextBean) {
        AbstractClassBean slotClassBean = classCompMap.get(ABS_SLOT);
        if (slotClassBean != null) {
            decorateSlotBO(slotClassBean, plantUmlContextBean);
        }
    }

    /**
     * 装饰SlotBO类
     * @param abstractClassBean
     * @param plantUmlContextBean
     */
    private void decorateSlotBO(AbstractClassBean abstractClassBean, PlantUmlContextBean plantUmlContextBean) {
        plantUmlContextBean.getClassBeanMap().forEach((k, v) -> {
            if (k.toLowerCase().endsWith("slotbo")) {
                //继承liteflow的AbsSlot类
                v.setRelationClassStr(" extends " + ABS_SLOT);
                v.addImportClass(abstractClassBean.getPackageName());
            }
        });
    }

}
