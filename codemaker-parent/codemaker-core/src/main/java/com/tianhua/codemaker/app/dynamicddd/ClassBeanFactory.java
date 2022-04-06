package com.tianhua.codemaker.app.dynamicddd;

import com.tianhua.codemaker.bean.plantuml.ClassBean;
import com.tianhua.codemaker.bean.plantuml.FieldBean;
import com.tianhua.codemaker.bean.plantuml.InterfaceBean;
import com.tianhua.codemaker.enums.DomainDerivedElementEnum;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 * date: 2021/11/1
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
public class ClassBeanFactory {

    /**
     * 构建dtobean
     * @param className
     * @param classBean
     * @param fieldBeanList
     * @return
     */
    public ClassBean buildDtoClassBean(String className, ClassBean classBean, List<FieldBean> fieldBeanList){
        String dtoClassName = className;
        if (!className.toLowerCase().endsWith("dto")) {
            dtoClassName = className + "DTO";
        }
        return classBean.derivedNewClassBean(dtoClassName,"api.dto",fieldBeanList);
    }


    /**
     *
     * @param className
     * @param classBean
     * @param fieldBeanList
     * @return
     */
    public ClassBean buildVOClassBean(String className, ClassBean classBean, List<FieldBean> fieldBeanList){
        String dtoClassName = className;
        if (!className.toLowerCase().endsWith("vo")) {
            dtoClassName = className + "VO";
        }
        return classBean.derivedNewClassBean(dtoClassName,"adapter.vo",fieldBeanList);
    }

    public InterfaceBean buildInterfaceBean(ClassBean classBean){
        InterfaceBean interfaceBean = new InterfaceBean();
        int x = classBean.getClassName().toLowerCase().lastIndexOf(DomainDerivedElementEnum.DTO.getElement());
        if (x >= 0) {
            String className = classBean.getClassName().substring(0, x) + "Facade";
            interfaceBean.setClassName(className);
            interfaceBean.setPlantUMLPackage("api.facade");
            interfaceBean.setClassDesc(classBean.getClassDesc());
            interfaceBean.setMethodBeanList(Lists.newArrayList());
            return interfaceBean;
        }
        return null;
    }

}
