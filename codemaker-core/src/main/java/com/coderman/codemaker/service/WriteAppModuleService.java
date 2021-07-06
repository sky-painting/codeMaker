package com.coderman.codemaker.service;

import com.coderman.codemaker.bean.ColumnBean;
import com.coderman.codemaker.bean.TableBean;
import com.coderman.codemaker.config.AppServiceConfig;
import com.coderman.codemaker.config.ProjectTemplateConfig;
import com.coderman.codemaker.enums.TemplateFileEnum;
import com.coderman.codemaker.utils.Constant;
import com.coderman.codemaker.utils.FreemarkerUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * 新版本的应用模块代码写服务，屏蔽底层写文件的细节，通过app和module隔离api和底层实现
 * 做到一套api支持多种应用框架风格的代码生成。
 * date: 2021/6/22
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */

@Component
public class WriteAppModuleService {

    @Autowired
    private AppServiceConfig appServiceConfig;

    @Autowired
    private ProjectTemplateConfig projectTemplateConfig;

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
        if(writeFileService == null){
            return;
        }
        writeFileService.writeContent(TemplateFileEnum.MAPPER_XML.getTempFileName(),content,humpClassName);
    }

    /**
     * 写entity文件
     *
     * @param content
     * @param humpClassName
     */
    public void writeEntity(String content, String humpClassName) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.ENTITY.getTempFileName());
        if(writeFileService == null){
            return;
        }
        writeFileService.writeContent(TemplateFileEnum.ENTITY.getTempFileName(),content,humpClassName);
    }

    /**
     * 写entity文件
     *
     * @param content
     * @param humpClassName
     */
    public void writeDO(String content, String humpClassName) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.DATA_OBJECT.getTempFileName());
        if(writeFileService == null){
            return;
        }
        writeFileService.writeContent(TemplateFileEnum.DATA_OBJECT.getTempFileName(),content,humpClassName);
    }


    /**
     * 写VO文件
     *
     * @param content
     * @param humpClassName
     */
    public void writeVO(String content, String humpClassName) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.VO.getTempFileName());
        if(writeFileService == null){
            return;
        }
        writeFileService.writeContent(TemplateFileEnum.VO.getTempFileName(),content,humpClassName);
    }

    /**
     * 写DTO文件
     *
     * @param content
     * @param humpClassName
     */
    public void writeDTO(String content, String humpClassName) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.DTO.getTempFileName());
        if(writeFileService == null){
            return;
        }
        writeFileService.writeContent(TemplateFileEnum.DTO.getTempFileName(),content,humpClassName);
    }

    /**
     * 写DTO文件
     *
     * @param content
     * @param humpClassName
     */
    public void writeBO(String content, String humpClassName) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.BUSINESS_OBJECT.getTempFileName());
        if(writeFileService == null){
            return;
        }
        writeFileService.writeContent(TemplateFileEnum.BUSINESS_OBJECT.getTempFileName(),content,humpClassName);
    }


    /**
     * 写mapper class文件
     *
     * @param content
     * @param humpClassName
     */
    public void writeMapper(String content, String humpClassName) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.MAPPER.getTempFileName());
        if(writeFileService == null){
            return;
        }
        writeFileService.writeContent(TemplateFileEnum.MAPPER.getTempFileName(),content,humpClassName);
    }

    /**
     * 写service文件
     *
     * @param content
     * @param humpClassName
     */
    public void writeService(String content, String humpClassName) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.SERVICE.getTempFileName());
        if(writeFileService == null){
            return;
        }
        writeFileService.writeContent(TemplateFileEnum.SERVICE.getTempFileName(),content,humpClassName);
    }


    /**
     * 写facade文件
     *
     * @param content
     * @param humpClassName
     */
    public void writeFacade(String content, String humpClassName) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.FACADE.getTempFileName());
        if(writeFileService == null){
            return;
        }
        writeFileService.writeContent(TemplateFileEnum.FACADE.getTempFileName(),content,humpClassName);
    }


    /**
     * 写facadeimpl文件
     *
     * @param content
     * @param humpClassName
     */
    public void writeFacadeImpl(String content, String humpClassName) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.FACADE_IMPL.getTempFileName());
        if(writeFileService == null){
            return;
        }
        writeFileService.writeContent(TemplateFileEnum.FACADE_IMPL.getTempFileName(),content,humpClassName);
    }


    /**
     * 写serviceImpl文件
     *
     * @param content
     * @param humpClassName
     */
    public void writeServiceImpl(String content, String humpClassName) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.SERVICE_IMPL.getTempFileName());
        if(writeFileService == null){
            return;
        }
        writeFileService.writeContent(TemplateFileEnum.SERVICE_IMPL.getTempFileName(),content,humpClassName);
    }


    /**
     * 写BaseController文件
     *
     * @param content
     */
    public void writeBaseController(String content) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.SERVICE_IMPL.getTempFileName());
        if(writeFileService == null){
            return;
        }
        writeFileService.writeContent(TemplateFileEnum.BASE_CONTROLLER.getTempFileName(),content,null);
    }

    /**
     * 写工具类文件
     *
     * @param content
     */
    public void writeSpringApplicationContext(String content) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.SPRING_APPLICATION_CONTEXT.getTempFileName());
        if(writeFileService == null){
            return;
        }
        writeFileService.writeContent(TemplateFileEnum.SPRING_APPLICATION_CONTEXT.getTempFileName(),content,"SpringApplicationContext.java");
    }


    /**
     * 写测试文件
     *
     * @param content
     */
    public void writeTest(String content, String humpClassName) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.TEST.getTempFileName());
        if(writeFileService == null){
            return;
        }
        writeFileService.writeContent(TemplateFileEnum.TEST.getTempFileName(),content,humpClassName);
    }

    /**
     * 写应用启动类
     *
     * @param content
     */
    public void writeApplication(String content) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.APPLICATION.getTempFileName());
        if(writeFileService == null){
            return;
        }
        writeFileService.writeContent(TemplateFileEnum.APPLICATION.getTempFileName(),content,"Application.java");
    }


    /**
     * 写应用启动类
     *
     * @param content
     */
    public void writeFacadeAop(String content) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.APPLICATION.getTempFileName());
        if(writeFileService == null){
            return;
        }
        writeFileService.writeContent(TemplateFileEnum.FACADE_AOP.getTempFileName(),content,"FacadeServiceAop.java");
    }


    /**
     * 写controller文件
     *
     * @param content
     * @param humpClassName
     */
    public void writeController(String content, String humpClassName) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.CONTROLLER.getTempFileName());
        if(writeFileService == null){
            return;
        }
        writeFileService.writeContent(TemplateFileEnum.CONTROLLER.getTempFileName(),content,humpClassName);
    }

    /**
     * 一次性生成单表需要的所有模块代码
     *
     * @param humpClassName
     * @param varMap
     */
    public void writeAll(String humpClassName, Map<String, Object> varMap, String fast) {
        if (StringUtils.isEmpty(fast)) {
            fast = "/"+appServiceConfig.getApplicationType()+"/";
        }
        String entityContent = freemarkerService.parseTpl(fast+TemplateFileEnum.ENTITY.getTempFileName(), varMap);
        this.writeEntity(entityContent, humpClassName);

        String serviceContent = freemarkerService.parseTpl(fast+TemplateFileEnum.SERVICE.getTempFileName(), varMap);
        this.writeService(serviceContent, humpClassName);

        String serviceImplContent = freemarkerService.parseTpl(fast+TemplateFileEnum.SERVICE_IMPL.getTempFileName(), varMap);
        this.writeServiceImpl(serviceImplContent, humpClassName);

        String mapperXmlContent = freemarkerService.parseTpl(fast+TemplateFileEnum.MAPPER_XML.getTempFileName(), varMap);
        this.writeMapperXml(mapperXmlContent, humpClassName);

        String mapperContent = freemarkerService.parseTpl(fast+TemplateFileEnum.MAPPER.getTempFileName(), varMap);
        this.writeMapper(mapperContent, humpClassName);

        String controllerContent = freemarkerService.parseTpl(fast+TemplateFileEnum.CONTROLLER.getTempFileName(), varMap);
        this.writeController(controllerContent, humpClassName);

        String voContent = freemarkerService.parseTpl(fast+TemplateFileEnum.VO.getTempFileName(), varMap);
        this.writeVO(voContent, humpClassName);

        String testContent = freemarkerService.parseTpl(fast+TemplateFileEnum.TEST.getTempFileName(), varMap);
        this.writeTest(testContent, humpClassName);

        String boContent = freemarkerService.parseTpl(fast+TemplateFileEnum.BUSINESS_OBJECT.getTempFileName(), varMap);
        this.writeBO(boContent, humpClassName);

        String dtoContent = freemarkerService.parseTpl(fast+TemplateFileEnum.DTO.getTempFileName(), varMap);
        this.writeDTO(dtoContent, humpClassName);


        String facadeContent = freemarkerService.parseTpl(fast+TemplateFileEnum.FACADE.getTempFileName(), varMap);
        this.writeFacade(facadeContent, humpClassName);


        String facadeImplContent = freemarkerService.parseTpl(fast+TemplateFileEnum.FACADE_IMPL.getTempFileName(), varMap);
        this.writeFacadeImpl(facadeImplContent, humpClassName);

        String doContent = freemarkerService.parseTpl(fast+TemplateFileEnum.DATA_OBJECT.getTempFileName(), varMap);
        this.writeDO(doContent, humpClassName);


    }




    /**
     * 写公共基础服务类
     *
     * @param varMap
     */
    public void writeCommon(Map<String, Object> varMap,String fast) {


        String baseControllerContent = freemarkerService.parseTpl(fast+ TemplateFileEnum.BASE_CONTROLLER.getTempFileName(), varMap);
        this.writeBaseController(baseControllerContent);

        String SpringApplicationContextContent = freemarkerService.parseTpl(fast+TemplateFileEnum.SPRING_APPLICATION_CONTEXT.getTempFileName(), varMap);
        this.writeSpringApplicationContext(SpringApplicationContextContent);

        String application = freemarkerService.parseTpl(fast+TemplateFileEnum.APPLICATION.getTempFileName(), varMap);
        this.writeApplication(application);

        String facadeAop = freemarkerService.parseTpl(fast+TemplateFileEnum.FACADE_AOP.getTempFileName(), varMap);
        this.writeFacadeAop(facadeAop);
    }

    /**
     * 整合e-r图生成工具
     *
     * @param tableBeanMap
     * @param columnBeanListMap
     */
    public void writeERPicture(Map<String, TableBean> tableBeanMap, Map<String, List<ColumnBean>> columnBeanListMap) {
        String filePath = projectTemplateConfig.getOutPath() + Constant.ER_PICTURE + "/" + projectTemplateConfig.getDbName() + ".puml";
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

}
