package com.tianhua.codemaker.app.dynamicddd.handler;

import com.tianhua.codemaker.service.packageimport.ImportPackageServiceImpl;
import com.tianhua.codemaker.api.DomainElementHandler;
import com.tianhua.codemaker.bean.dddelement.CommandElementBean;
import com.tianhua.codemaker.bean.plantuml.ClassBean;
import com.tianhua.codemaker.bean.plantuml.PlantUmlContextBean;
import com.tianhua.codemaker.enums.DomainElementEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * date: 2021/6/29
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component(value = "appCmdElementHandler")
public class AppCmdElementHandler implements DomainElementHandler<CommandElementBean> {


    @Autowired
    private ImportPackageServiceImpl importPackageService;

    @Override
    public CommandElementBean getElementBeanList(PlantUmlContextBean plantUmlContextBean) {
        CommandElementBean commandElementBean = new CommandElementBean();
        List<ClassBean> cmdElementBeanList = new ArrayList<>();
        plantUmlContextBean.getClassBeanMap().forEach((k,v)->{
            if(classFilter(v.getClassName())){
                importPackageService.setPackageName(v,"app.command");
                String className = v.getClassName().substring(0,1).toUpperCase().concat(v.getClassName().substring(1));
                v.setClassName(className);
                cmdElementBeanList.add(v);
            }
        });
        cmdElementBeanList.stream().forEach(v-> importPackageService.dealImportClass(v,plantUmlContextBean));
        commandElementBean.setClassBeanList(cmdElementBeanList);
        return commandElementBean;
    }

    /**
     * 类过滤
     * @param className
     * @return
     */
    private boolean classFilter(String className){
         String[] cmdArr = DomainElementEnum.COMMAND.getElement().split(",");
         for (String cmd : cmdArr){
             if (className.toLowerCase().endsWith(cmd)) {
                 return true;
             }
         }
         return false;
    }
}

