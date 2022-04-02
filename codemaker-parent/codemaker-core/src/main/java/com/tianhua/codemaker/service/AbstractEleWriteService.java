package com.tianhua.codemaker.service;

import com.tianhua.codemaker.app.IWriteFileService;
import com.tianhua.codemaker.bean.WriteContentBean;
import com.tianhua.codemaker.bean.plantuml.ClassBean;
import com.tianhua.codemaker.bean.plantuml.EnumBean;
import com.tianhua.codemaker.bean.plantuml.InterfaceBean;
import com.tianhua.codemaker.config.AppServiceConfig;
import com.tianhua.codemaker.enums.ModuleEnum;
import com.tianhua.codemaker.enums.TemplateFileEnum;
import com.tianhua.codemaker.service.template.FreemarkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Description:
 * date: 2022/3/14
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component(value = "abstractEleWriteService")
public class AbstractEleWriteService {
    @Autowired
    private AppServiceConfig appServiceConfig;

    @Autowired
    private FreemarkerService freemarkerService;

    /**
     * 所有class类型的element统一写入口
     * @param classBeanList 要生成的class类列表
     * @param templateFileEnum 公用的模板
     * @param eleEnum 要生成的代码元素枚举
     */
    public void writeClassEle(List<ClassBean> classBeanList, TemplateFileEnum templateFileEnum, TemplateFileEnum eleEnum, IWriteFileService writeFileService) {
        boolean dynamicDDD = false;
        if(writeFileService == null){
            writeFileService = appServiceConfig.getModuleWriteService(templateFileEnum.getTempFileName());
        }else {
            dynamicDDD = true;
        }
        if (writeFileService == null) {
            return;
        }
        for (ClassBean classBean : classBeanList){
            Map<String, Object> varMap = classBean.buildVarMap();
            String boContent;
            if(dynamicDDD){
                boContent = freemarkerService.parseTplDynamicDDD(templateFileEnum.getTempFileName(), varMap);
            }else {
                boContent = freemarkerService.parseTpl(templateFileEnum.getTempFileName(), varMap);
            }
            WriteContentBean writeContentBean = classBean.buildWriteContentBean(boContent,eleEnum);
            writeFileService.writeContent(writeContentBean);
        }
    }


    /**
     * 所有interface类型的element统一写入口
     * @param interfaceBeanList
     * @param templateFileEnum
     * @param eleEnum
     */
    public void writeInterfaceEle(List<InterfaceBean> interfaceBeanList, TemplateFileEnum templateFileEnum, TemplateFileEnum eleEnum, IWriteFileService writeFileService){
        boolean dynamicDDD = false;
        if(writeFileService == null){
            writeFileService = appServiceConfig.getModuleWriteService(templateFileEnum.getTempFileName());
        }else {
            dynamicDDD = true;
        }
        if (writeFileService == null) {
            return;
        }
        for (InterfaceBean interfaceBean : interfaceBeanList){
            Map<String, Object> varMap = interfaceBean.buildVarMap();
            String interfaceContent;
            if(dynamicDDD){
                interfaceContent = freemarkerService.parseTplDynamicDDD(templateFileEnum.getTempFileName(), varMap);
            }else {
                interfaceContent = freemarkerService.parseTpl(templateFileEnum.getTempFileName(), varMap);
            }
            WriteContentBean writeContentBean = interfaceBean.buildWriteContentBean(interfaceContent,eleEnum);
            writeFileService.writeContent(writeContentBean);
        }
    }


    public void writeEnumEle(List<EnumBean> enumBeanList, IWriteFileService writeFileService){
        if(writeFileService == null){
            writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.ENUM.getTempFileName());
        }
        if (writeFileService == null) {
            return;
        }
        writeFileService = getRoute(enumBeanList.get(0).getPackageName(),writeFileService);
        //写枚举
        for (EnumBean enumBean : enumBeanList){
            Map<String, Object> varMap = enumBean.buildVarMap();
            String enumContent;
            enumContent = freemarkerService.parseTpl(TemplateFileEnum.ENUM.getTempFileName(), varMap);
            WriteContentBean writeContentBean = enumBean.buildWriteContentBean(enumContent,TemplateFileEnum.ENUM);
            writeFileService.writeContent(writeContentBean);
        }
    }




    private IWriteFileService getRoute(String packageName,IWriteFileService writeFileService){
        String appType = appServiceConfig.getApplicationType();
        if (appType.equals(ModuleEnum.SPRING_BOOT_WEB.getAppName())) {
            return writeFileService;
        } else if (appType.equals(ModuleEnum.DUBBO_API.getAppName()) && packageName.contains("api")) {
            return appServiceConfig.getWriteServiceByModuleName(ModuleEnum.DUBBO_API.getModuleName());
        } else if (appType.equals(ModuleEnum.COLA_ADAPTER.getAppName()) && packageName.contains("api")) {
            return appServiceConfig.getWriteServiceByModuleName(ModuleEnum.COLA_CLIENT.getModuleName());
        } else if (appType.equals(ModuleEnum.SC_FEIGN_API.getAppName()) && packageName.contains("api")) {
            return appServiceConfig.getWriteServiceByModuleName(ModuleEnum.SC_FEIGN_API.getModuleName());
        }
        return writeFileService;
    }





}
