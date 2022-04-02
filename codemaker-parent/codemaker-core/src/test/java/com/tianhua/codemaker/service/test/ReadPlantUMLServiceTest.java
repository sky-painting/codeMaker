/*
package com.tianhua.codemaker.service.test;

import com.tianhua.codemaker.api.DomainElementHandler;
import com.tianhua.codemaker.api.IValidateService;
import com.tianhua.codemaker.bean.plantuml.PlantUmlContextBean;
import com.tianhua.codemaker.service.plantuml.ReadDomainPlantDocService;
import com.tianhua.codemaker.service.plantuml.ReadPlantUMLDocService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

*/
/**
 * Description:
 * date: 2022/1/25
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 *//*

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {com.tianhua.codemaker.Main.class})
public class ReadPlantUMLServiceTest {

    @Autowired
    private ReadDomainPlantDocService readDomainPlantDocService;

    @Resource(name = "validateUtilService")
    private IValidateService validateService;
    @Resource(name = "domainBoElementHandler")
    private DomainElementHandler domainElementHandler;

    @Test
    public void testRead(){
        PlantUmlContextBean plantUmlContextBean = readDomainPlantDocService.getPlantUmlContextBean("auth-domainV5.puml");
        plantUmlContextBean.setDerivedPlantUmlContextBean(new PlantUmlContextBean());
        domainElementHandler.getElementBeanList(plantUmlContextBean);
        validateService.dealValidate(plantUmlContextBean);
    }
}
*/
