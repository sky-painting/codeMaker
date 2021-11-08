package com.coderman.codemaker.app.dynamicddd.handler;

import com.coderman.codemaker.service.ImportPackageService;
import com.coderman.codemaker.app.dynamicddd.DomainElementHandler;
import com.coderman.codemaker.bean.dddelement.DomainMsgBodyElementBean;
import com.coderman.codemaker.bean.plantuml.ClassBean;
import com.coderman.codemaker.bean.plantuml.PlantUmlContextBean;
import com.coderman.codemaker.enums.DomainElementEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * date: 2021/6/30
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component(value = "msgBodyElementHandler")
public class MsgBodyElementHandler implements DomainElementHandler<DomainMsgBodyElementBean> {

    @Autowired
    private ImportPackageService importPackageService;


    @Override
    public DomainMsgBodyElementBean getElementBeanList(PlantUmlContextBean plantUmlContextBean) {
        DomainMsgBodyElementBean domainMsgBodyElementBean = new DomainMsgBodyElementBean();
        List<ClassBean> domainmsgBodyBeanList = new ArrayList<>();
        plantUmlContextBean.getClassBeanMap().forEach((k,v)->{
            if(v.getClassName().toLowerCase().endsWith(DomainElementEnum.MSGBODY.getElement())){
                importPackageService.setPackageName(v,"domain.msgbody");

                String className = v.getClassName().substring(0,1).toUpperCase().concat(v.getClassName().substring(1));
                v.setClassName(className);
                importPackageService.dealImportClass(v,plantUmlContextBean);
                domainmsgBodyBeanList.add(v);
            }
        });
        domainMsgBodyElementBean.setClassBeanList(domainmsgBodyBeanList);
        return domainMsgBodyElementBean;
    }
}
