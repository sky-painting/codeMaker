package com.tianhua.codemaker.service;

import com.tianhua.codemaker.app.IWriteFileService;
import com.tianhua.codemaker.bean.ColumnBean;
import com.tianhua.codemaker.bean.GlobalConstant;
import com.tianhua.codemaker.bean.TableBean;
import com.tianhua.codemaker.bean.WriteContentBean;
import com.tianhua.codemaker.config.AppServiceConfig;
import com.tianhua.codemaker.enums.TemplateFileEnum;
import com.tianhua.codemaker.service.dberpicture.DBErPictureService;
import com.tianhua.codemaker.service.template.FreemarkerService;
import com.tianhua.codemaker.utils.Constant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * 此服务是老版本，只支持基于数据库的代码生成，新版本的应用模块代码写服务，屏蔽底层写文件的细节，通过app和module隔离api和底层实现
 * 做到一套api支持多种应用框架风格的代码生成。
 * date: 2021/6/22
 *
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */

@Component
@Slf4j
public class WriteCodeService {

    @Autowired
    private AppServiceConfig appServiceConfig;

    @Autowired
    private DBErPictureService erPictureService;

    @Autowired
    private FreemarkerService freemarkerService;

    /**
     * 写mapper xml文件
     *
     * @param content
     * @param humpClassName
     */
    public void writeMapperXml(String content, String humpClassName) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.MAPPER_XML.getTempFileName());
        if (writeFileService == null) {
            return;
        }

        WriteContentBean writeContentBean = WriteContentBean.builder().content(content)
                .templateName(TemplateFileEnum.MAPPER_XML.getTempFileName())
                .humpClassName(humpClassName).build();

        writeFileService.writeContent(writeContentBean);
    }

    /**
     * 写entity文件
     *
     * @param content
     * @param humpClassName
     */
    public void writeEntity(String content, String humpClassName) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.ENTITY.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        WriteContentBean writeContentBean = WriteContentBean.builder().content(content)
                .templateName(TemplateFileEnum.ENTITY.getTempFileName())
                .humpClassName(humpClassName).build();

        writeFileService.writeContent(writeContentBean);
    }

    /**
     * 写do文件
     *
     * @param content
     * @param humpClassName
     */
    public void writeDO(String content, String humpClassName) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.DATA_OBJECT.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        String classPackageName = content.split("\n")[0].replace("package","").replace(" ","").replace(";","");

        WriteContentBean writeContentBean = WriteContentBean.builder().content(content)
                .templateName(TemplateFileEnum.DATA_OBJECT.getTempFileName())
                .classPackageName(classPackageName)
                .humpClassName(humpClassName).build();

        writeFileService.writeContent(writeContentBean);
    }


    /**
     * 写VO文件
     *
     * @param content
     * @param humpClassName
     */
    public void writeVO(String content, String humpClassName) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.VO.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        WriteContentBean writeContentBean = WriteContentBean.builder().content(content)
                .templateName(TemplateFileEnum.VO.getTempFileName())
                .humpClassName(humpClassName).build();

        writeFileService.writeContent(writeContentBean);
    }

    /**
     * 写DTO文件
     *
     * @param content
     * @param humpClassName
     */
    public void writeDTO(String content, String humpClassName) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.DTO.getTempFileName());
        if (writeFileService == null) {
            return;
        }

        WriteContentBean writeContentBean = WriteContentBean.builder().content(content)
                .templateName(TemplateFileEnum.DTO.getTempFileName())
                .humpClassName(humpClassName).build();
        writeFileService.writeContent(writeContentBean);
    }

    /**
     * 写BO文件
     *
     * @param content
     * @param humpClassName
     */
    public void writeBO(String content, String humpClassName) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.BUSINESS_OBJECT.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        WriteContentBean writeContentBean = WriteContentBean.builder().content(content)
                .templateName(TemplateFileEnum.BUSINESS_OBJECT.getTempFileName())
                .humpClassName(humpClassName).build();
        writeFileService.writeContent(writeContentBean);
    }

    /**
     * 写BO文件
     * @param writeContentBean
     */
    public void writeBO(WriteContentBean writeContentBean) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.BUSINESS_OBJECT.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        writeFileService.writeContent(writeContentBean);
    }

    /**
     * 写mapper class文件
     *
     * @param content
     * @param humpClassName
     */
    public void writeMapper(String content, String humpClassName) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.MAPPER.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        WriteContentBean writeContentBean = WriteContentBean.builder().content(content)
                .templateName(TemplateFileEnum.MAPPER.getTempFileName())
                .humpClassName(humpClassName).build();
        writeFileService.writeContent(writeContentBean);
    }

    /**
     * 写service文件
     *
     * @param content
     * @param humpClassName
     */
    public void writeService(String content, String humpClassName) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.SERVICE.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        WriteContentBean writeContentBean = WriteContentBean.builder().content(content)
                .templateName(TemplateFileEnum.SERVICE.getTempFileName())
                .humpClassName(humpClassName).build();
        writeFileService.writeContent(writeContentBean);
    }


    /**
     * 写facade文件
     *
     * @param content
     * @param humpClassName
     */
    public void writeFacade(String content, String humpClassName) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.FACADE.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        WriteContentBean writeContentBean = WriteContentBean.builder().content(content)
                .templateName(TemplateFileEnum.FACADE.getTempFileName())
                .humpClassName(humpClassName).build();
        writeFileService.writeContent(writeContentBean);
    }


    /**
     * 写facadeimpl文件
     *
     * @param content
     * @param humpClassName
     */
    public void writeFacadeImpl(String content, String humpClassName) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.FACADE_IMPL.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        WriteContentBean writeContentBean = WriteContentBean.builder().content(content)
                .templateName(TemplateFileEnum.FACADE_IMPL.getTempFileName())
                .humpClassName(humpClassName).build();
        writeFileService.writeContent(writeContentBean);
    }


    /**
     * 写serviceImpl文件
     *
     * @param content
     * @param humpClassName
     */
    public void writeServiceImpl(String content, String humpClassName) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.SERVICE_IMPL.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        WriteContentBean writeContentBean = WriteContentBean.builder().content(content)
                .templateName(TemplateFileEnum.SERVICE_IMPL.getTempFileName())
                .humpClassName(humpClassName).build();
        writeFileService.writeContent(writeContentBean);
    }


    /**
     * 写BaseController文件
     *
     * @param content
     */
    public void writeBaseController(String content) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.BASE_CONTROLLER.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        WriteContentBean writeContentBean = WriteContentBean.builder().content(content)
                .templateName(TemplateFileEnum.BASE_CONTROLLER.getTempFileName())
                .humpClassName("").build();
        writeFileService.writeContent(writeContentBean);
    }

    /**
     * 写工具类文件
     *
     * @param content
     */
    public void writeSpringApplicationContext(String content) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.SPRING_APPLICATION_CONTEXT.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        WriteContentBean writeContentBean = WriteContentBean.builder().content(content)
                .templateName(TemplateFileEnum.SPRING_APPLICATION_CONTEXT.getTempFileName())
                .humpClassName("SpringApplicationContext.java").build();
        writeFileService.writeContent(writeContentBean);
    }

    /**
     * 写测试文件
     *
     * @param content
     */
    public void writeTest(String content, String humpClassName) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.TEST.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        WriteContentBean writeContentBean = WriteContentBean.builder().content(content)
                .templateName(TemplateFileEnum.TEST.getTempFileName())
                .humpClassName(humpClassName).build();
        writeFileService.writeContent(writeContentBean);
    }

    /**
     * 写应用启动类
     *
     * @param content
     */
    public void writeApplication(String content) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.APPLICATION.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        WriteContentBean writeContentBean = WriteContentBean.builder().content(content)
                .templateName(TemplateFileEnum.APPLICATION.getTempFileName())
                .humpClassName("Application.java").build();
        writeFileService.writeContent(writeContentBean);
    }


    /**
     * 写应用启动类
     *
     * @param content
     */
    public void writeFacadeAop(String content) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.APPLICATION.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        WriteContentBean writeContentBean = WriteContentBean.builder().content(content)
                .templateName(TemplateFileEnum.FACADE_AOP.getTempFileName())
                .humpClassName("FacadeServiceAop.java").build();
        writeFileService.writeContent(writeContentBean);
    }


    /**
     * 写controller文件
     *
     * @param content
     * @param humpClassName
     */
    public void writeController(String content, String humpClassName) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.CONTROLLER.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        WriteContentBean writeContentBean = WriteContentBean.builder().content(content)
                .templateName(TemplateFileEnum.CONTROLLER.getTempFileName())
                .humpClassName(humpClassName).build();
        writeFileService.writeContent(writeContentBean);
    }

    /**
     * 一次性生成单表需要的所有模块代码
     *
     * @param humpClassName
     * @param varMap
     */
    public void writeAll(final String humpClassName, Map<String, Object> varMap, String fast) {
        if (StringUtils.isEmpty(fast)) {
            fast = "/" + appServiceConfig.getApplicationType() + "/";
        }
        String entityContent = freemarkerService.parseTpl(fast + TemplateFileEnum.ENTITY.getTempFileName(), varMap);
        this.writeEntity(entityContent, humpClassName + "Entity");

        String serviceContent = freemarkerService.parseTpl(fast + TemplateFileEnum.SERVICE.getTempFileName(), varMap);
        this.writeService(serviceContent, humpClassName + "Service");

        String serviceImplContent = freemarkerService.parseTpl(fast + TemplateFileEnum.SERVICE_IMPL.getTempFileName(), varMap);
        this.writeServiceImpl(serviceImplContent, humpClassName + "ServiceImpl");

        String mapperXmlContent = freemarkerService.parseTpl(fast + TemplateFileEnum.MAPPER_XML.getTempFileName(), varMap);
        this.writeMapperXml(mapperXmlContent, humpClassName + "Mapper");

        String mapperContent = freemarkerService.parseTpl(fast + TemplateFileEnum.MAPPER.getTempFileName(), varMap);
        this.writeMapper(mapperContent, humpClassName+ "Mapper");

        String controllerContent = freemarkerService.parseTpl(fast + TemplateFileEnum.CONTROLLER.getTempFileName(), varMap);
        this.writeController(controllerContent, humpClassName+ "Controller");

        String voContent = freemarkerService.parseTpl(fast + TemplateFileEnum.VO.getTempFileName(), varMap);
        this.writeVO(voContent, humpClassName + "VO");

        String testContent = freemarkerService.parseTpl(fast + TemplateFileEnum.TEST.getTempFileName(), varMap);
        if(appServiceConfig.getApplicationType().equals("springboot")){
            this.writeTest(testContent, humpClassName +"Service");
        }else {
            this.writeTest(testContent, humpClassName + "Facade");
        }

        String boContent = freemarkerService.parseTpl(fast + TemplateFileEnum.BUSINESS_OBJECT.getTempFileName(), varMap);
        this.writeBO(boContent, humpClassName + "BO");

        String dtoContent = freemarkerService.parseTpl(fast + TemplateFileEnum.DTO.getTempFileName(), varMap);
        this.writeDTO(dtoContent, humpClassName + "DTO");


        String facadeContent = freemarkerService.parseTpl(fast + TemplateFileEnum.FACADE.getTempFileName(), varMap);
        this.writeFacade(facadeContent, humpClassName + "Facade");


        String facadeImplContent = freemarkerService.parseTpl(fast + TemplateFileEnum.FACADE_IMPL.getTempFileName(), varMap);
        this.writeFacadeImpl(facadeImplContent, humpClassName + "FacadeImpl");

        String doContent = freemarkerService.parseTpl(fast + TemplateFileEnum.DATA_OBJECT.getTempFileName(), varMap);
        this.writeDO(doContent, humpClassName + "DO");
    }

    /**
     * 写公共基础服务类
     * @param varMap
     */
    public void writeCommon(Map<String, Object> varMap) {
        Map<String,List<String>> initClassMap = appServiceConfig.getInitClassMapList();
        if(initClassMap == null || initClassMap.isEmpty()){
            return;
        }

        for (Map.Entry<String,List<String>> entry : initClassMap.entrySet()){
            for (String classTemplate : entry.getValue()){
                String clazzContent = freemarkerService.parseTplCommon(classTemplate, varMap);
                this.writeInitClass(entry.getKey(),clazzContent,classTemplate);
            }
        }
    }

    /**
     * 整合e-r图生成工具
     *
     * @param tableBeanMap
     * @param columnBeanListMap
     */
    public void writeERPicture(Map<String, TableBean> tableBeanMap, Map<String, List<ColumnBean>> columnBeanListMap) {
        //这里走了e-r图构建了，所以不需要再次渲染e-r图了
        if(!appServiceConfig.getLinkDB()){
            return;
        }

        String filePath = appServiceConfig.getErPictureOutPath() + Constant.ER_PICTURE + "/" + appServiceConfig.getDbName() + ".puml";
        List<com.coderman.codemaker.dbergenerate.bean.TableBean> tableBeanList = new ArrayList<>();
        tableBeanMap.forEach((k, v) -> {
            com.coderman.codemaker.dbergenerate.bean.TableBean tableBean = new com.coderman.codemaker.dbergenerate.bean.TableBean();
            tableBean.setTableComment(v.getTableComment());
            tableBean.setTableName(v.getTableName());
            List<ColumnBean> columnBeanList = columnBeanListMap.get(k);
            List<com.coderman.codemaker.dbergenerate.bean.ColumnBean> columnBeanList1 = new ArrayList<>();
            columnBeanList.forEach(columnBean -> {
                com.coderman.codemaker.dbergenerate.bean.ColumnBean columnBean1 = new com.coderman.codemaker.dbergenerate.bean.ColumnBean();
                columnBean1.setColumnComment(columnBean.getColumnComment());
                columnBean1.setColumnKey(columnBean.getColumnKey());
                columnBean1.setColumnName(columnBean.getColumnName());
                columnBean1.setTableName(columnBean.getTableName());
                columnBean1.setColumnType(columnBean.getColumnType());
                columnBean1.setDataType(columnBean.getDataType());
                columnBeanList1.add(columnBean1);
            });
            tableBean.setColumnBeanList(columnBeanList1);
            tableBeanList.add(tableBean);
        });
        erPictureService.getErPicture(filePath, tableBeanList);
    }

    /**
     * 写初始化工具类的统一方法
     * @param moduleName
     * @param content
     */
    public void writeInitClass(String moduleName, String content,String className){
        if(StringUtils.isEmpty(content)){
            return;
        }
        IWriteFileService writeFileService = appServiceConfig.getWriteServiceByModuleName(moduleName);
        if (writeFileService == null) {
            return;
        }

        String classPackage = content.split(";")[0].replace("package","").trim();

        WriteContentBean writeContentBean = WriteContentBean.builder().content(content)
                .templateName(GlobalConstant.SINGLE_CLASS_COMMON)
                .classPackageName(classPackage)
                .humpClassName(className).build();
        writeFileService.writeContent(writeContentBean);
    }

    /**
     * 写配置文件的统一方法
     * @param moduleName
     * @param content
     */
    public void writeConfig(String moduleName, String content,String configFileName){
        if(StringUtils.isEmpty(content)){
            return;
        }
        IWriteFileService writeFileService = appServiceConfig.getWriteServiceByModuleName(moduleName);
        if (writeFileService == null) {
            return;
        }

        WriteContentBean writeContentBean = WriteContentBean.builder().content(content)
                .templateName(GlobalConstant.CONFIG)
                .humpClassName(configFileName).build();
        writeFileService.writeContent(writeContentBean);
    }


}
