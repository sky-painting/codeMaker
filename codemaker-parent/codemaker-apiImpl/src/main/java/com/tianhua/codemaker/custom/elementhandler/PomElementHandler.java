package com.tianhua.codemaker.custom.elementhandler;

import com.tianhua.codemaker.annotations.ElementTag;
import com.tianhua.codemaker.api.DomainElementHandler;
import com.tianhua.codemaker.bean.config.FtlBean;
import com.tianhua.codemaker.bean.config.PomBean;
import com.tianhua.codemaker.bean.plantuml.PlantUmlContextBean;
import com.tianhua.codemaker.custom.bean.PomElementBean;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

/**
 * Description:
 * date: 2021/7/8
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 *
 */
@Component(value = "pomElementHandler")
@ElementTag(elementName = "clientpom,infrastpom,feignapipom," +
        "domainpom,apppom,adapterpom,startpom,parentpom,apipom,corepom,commonpom,providerpom")
public class PomElementHandler implements DomainElementHandler<PomElementBean> {

    @Override
    public PomElementBean getElementBeanList(PlantUmlContextBean plantUmlContextBean) {
        FtlBean ftlBean = plantUmlContextBean.getFtlBean();
        PomElementBean clientPomElementBean = new PomElementBean();
        PomBean pomBean = plantUmlContextBean.getPomBeanMap().get(ftlBean.getModuleName());
        clientPomElementBean.setPomBeanList(Lists.newArrayList(pomBean));
        return clientPomElementBean;
    }
}
