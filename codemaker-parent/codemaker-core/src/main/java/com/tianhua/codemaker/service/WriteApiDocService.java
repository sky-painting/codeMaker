package com.tianhua.codemaker.service;

import com.tianhua.codemaker.app.IWriteFileService;
import com.tianhua.codemaker.bean.WriteContentBean;
import com.tianhua.codemaker.bean.apidoc.ApiDocBean;
import com.tianhua.codemaker.bean.plantuml.ClassBean;
import com.tianhua.codemaker.bean.plantuml.InterfaceBean;
import com.tianhua.codemaker.enums.TemplateFileEnum;
import com.tianhua.codemaker.service.template.FreemarkerService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Description:
 * date: 2021/11/22
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
public class WriteApiDocService {
    @Autowired
    private FreemarkerService freemarkerService;


    /**
     * 写http协议接口文档
     * @param classBeanList
     * @param writeFileService
     * @param voBeanList
     */
    public void writeHttpApiDoc(List<ClassBean> classBeanList, IWriteFileService writeFileService,List<ClassBean> voBeanList) {
        if(CollectionUtils.isEmpty(classBeanList)){
            return;
        }
        Map<String,ClassBean> classVOBeanMap = voBeanList.stream().collect(Collectors.toMap(ClassBean::getClassName,o->o));

        for (ClassBean classBean : classBeanList){
            Map<String, Object> varMap = new HashMap<>();
            varMap.put("moduleDesc", classBean.getClassDesc());

            List<ApiDocBean> apiDocBeanList = new ArrayList<>();

            classBean.getMethodBeanList().forEach(methodBean -> apiDocBeanList.add(methodBean.buildApiDocBean(classVOBeanMap)));
            varMap.put("apiList", apiDocBeanList);

            String content = freemarkerService.parseTplApi(TemplateFileEnum.API_HTTP.getTempFileName(), varMap);
            WriteContentBean writeContentBean = WriteContentBean.builder().content(content)
                    .humpClassName(classBean.getClassName())
                    .templateName(TemplateFileEnum.API_HTTP.getTempFileName()).build();
            writeFileService.writeContent(writeContentBean);
        }

    }


    /**
     * 写rpc协议接口文档
     * @param interfaceBeanList
     * @param writeFileService
     * @param dtoBeanList
     */
    public void writeRpcApiDoc(List<InterfaceBean> interfaceBeanList, IWriteFileService writeFileService, List<ClassBean> dtoBeanList) {
        if(CollectionUtils.isEmpty(interfaceBeanList)){
            return;
        }
        Map<String,ClassBean> classDTOBeanMap = dtoBeanList.stream().collect(Collectors.toMap(ClassBean::getClassName,o->o));

        for (InterfaceBean interfaceBean : interfaceBeanList){
            Map<String, Object> varMap = new HashMap<>();
            varMap.put("moduleDesc", interfaceBean.getClassDesc());
            varMap.put("moduleApi", interfaceBean.getPackageName()+"."+interfaceBean.getClassName());

            List<ApiDocBean> apiDocBeanList = new ArrayList<>();

            interfaceBean.getMethodBeanList().forEach(methodBean -> {
                apiDocBeanList.add(methodBean.buildApiDocBean(classDTOBeanMap));
            });
            varMap.put("apiList", apiDocBeanList);

            String content = freemarkerService.parseTplApi(TemplateFileEnum.API_RPC.getTempFileName(), varMap);
            WriteContentBean writeContentBean = WriteContentBean.builder().content(content)
                    .humpClassName(interfaceBean.getClassName())
                    .templateName(TemplateFileEnum.API_HTTP.getTempFileName()).build();
            writeFileService.writeContent(writeContentBean);
        }
    }

    public void writeSqlDoc(List<String> sqlContentList, IWriteFileService writeFileService){
        StringBuilder stringBuilder = new StringBuilder("");
        sqlContentList.forEach(sql->stringBuilder.append(sql));
        WriteContentBean writeContentBean = WriteContentBean.builder().content(stringBuilder.toString())
                .templateName(TemplateFileEnum.SQL.getTempFileName()).build();
        writeFileService.writeContent(writeContentBean);
    }

}
