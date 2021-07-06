package com.coderman.codemaker.controller;

import com.coderman.codemaker.bean.ColumnBean;
import com.coderman.codemaker.bean.TableBean;
import com.coderman.codemaker.enums.TemplateFileEnum;
import com.coderman.codemaker.service.FreemarkerService;
import com.coderman.codemaker.service.WriteAppModuleService;
import com.coderman.codemaker.service.registry.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description: GeneratorController <br>
 * date: 2020/7/7 9:48 <br>
 * author: coderman <br>
 * version: 1.0 <br>
 * 为了保持代码生成的灵活性，对于每种类型的文件都单独写一个
 * 接口，变量也是按需要注册
 */
@RestController
public class GeneratorController {
    @Resource(name = "entityVarRegistry")
    private EntityVarRegistry entityVarRegistry;

    @Resource(name = "mapperVarRegistry")
    private MapperVarRegistry mapperVarRegistry;

    @Resource(name = "serviceVarRegistry")
    private ServiceVarRegistry serviceVarRegistry;

    @Resource(name = "serviceImplVarRegistry")
    private ServiceImplVarRegistry serviceImplVarRegistry;

    @Resource(name = "mapperXmlVarRegistry")
    private MapperXmlVarRegistry mapperXmlVarRegistry;

    @Resource(name = "testVarRegistry")
    private TestVarRegistry testVarRegistry;


    //@Autowired
    //private WriteFileService writeFileService;

    @Autowired
    private WriteAppModuleService writeFileService;


    @Autowired
    private FreemarkerService freemarkerService;


    /**
     * 生成模块的entity类
     * @return
     */
    @GetMapping("/getproject/entity")
    public String getEntityTemplate(){
        Map<String,Object> map = entityVarRegistry.getTemplateVar();

        Map<String, TableBean> tableBeanMap = (Map<String, TableBean>)map.get("table");
        Map<String, List<ColumnBean>> columnBeanListMap = (Map<String, List<ColumnBean>>)map.get("columns");
        tableBeanMap.forEach((k,v)->{
            Map<String,Object> varMap = new HashMap<>();
            varMap.put("table", v);
            varMap.put("columns", columnBeanListMap.get(k));
            varMap.put("package", map.get("package"));
            varMap.put("author", map.get("author"));
            String templateContent = freemarkerService.parseTpl(TemplateFileEnum.ENTITY.getTempFileName(),varMap);
            writeFileService.writeEntity(templateContent,v.getHumpClassName());
        });

        return "success";
    }

    /**
     * 生成模块的entity类
     * @return
     */
    @GetMapping("/getproject/do")
    public String getDataObjectTemplate(){
        Map<String,Object> map = entityVarRegistry.getTemplateVar();

        Map<String, TableBean> tableBeanMap = (Map<String, TableBean>)map.get("table");
        Map<String, List<ColumnBean>> columnBeanListMap = (Map<String, List<ColumnBean>>)map.get("columns");
        tableBeanMap.forEach((k,v)->{
            Map<String,Object> varMap = new HashMap<>();
            varMap.put("table", v);
            varMap.put("columns", columnBeanListMap.get(k));
            varMap.put("package", map.get("package"));
            varMap.put("author", map.get("author"));
            String templateContent = freemarkerService.parseTpl(TemplateFileEnum.DATA_OBJECT.getTempFileName(),varMap);
            writeFileService.writeDO(templateContent,v.getHumpClassName());
        });

        return "success";
    }

    /**
     * 生成模块的vo类
     * @return
     */
    @GetMapping("/getproject/vo")
    public String getVOTemplate(){
        Map<String,Object> map = entityVarRegistry.getTemplateVar();

        Map<String, TableBean> tableBeanMap = (Map<String, TableBean>)map.get("table");
        Map<String, List<ColumnBean>> columnBeanListMap = (Map<String, List<ColumnBean>>)map.get("columns");
        tableBeanMap.forEach((k,v)->{
            Map<String,Object> varMap = new HashMap<>();
            varMap.put("table", v);
            varMap.put("columns", columnBeanListMap.get(k));
            varMap.put("package", map.get("package"));
            varMap.put("author", map.get("author"));
            String templateContent = freemarkerService.parseTpl(TemplateFileEnum.VO.getTempFileName(),varMap);
            writeFileService.writeVO(templateContent,v.getHumpClassName());
        });

        return "success";
    }

    /**
     * 生成模块的mapper类
     * @return
     */
    @GetMapping("/getproject/mapper")
    public String getMapperTemplate(){
        Map<String,Object> map = mapperVarRegistry.getTemplateVar();
        Map<String, TableBean> tableBeanMap = (Map<String, TableBean>)map.get("table");
        Map<String, List<ColumnBean>> columnBeanListMap = (Map<String, List<ColumnBean>>)map.get("columns");
        tableBeanMap.forEach((k,v)->{
            Map<String,Object> varMap = new HashMap<>();
            varMap.put("table", v);
            varMap.put("columns", columnBeanListMap.get(k));
            varMap.put("package", map.get("package"));
            varMap.put("author", map.get("author"));
            String templateContent = freemarkerService.parseTpl(TemplateFileEnum.MAPPER.getTempFileName(),varMap);
            writeFileService.writeMapper(templateContent,v.getHumpClassName());

        });

        return "success";
    }

    /**
     * 生成模块的service类
     * @return
     */
    @GetMapping("/getproject/service")
    public String getServiceTemplate(){
        Map<String,Object> map = serviceVarRegistry.getTemplateVar();

        Map<String, TableBean> tableBeanMap = (Map<String, TableBean>)map.get("table");
        Map<String, List<ColumnBean>> columnBeanListMap = (Map<String, List<ColumnBean>>)map.get("columns");
        tableBeanMap.forEach((k,v)->{
            Map<String,Object> varMap = new HashMap<>();
            varMap.put("table", v);
            varMap.put("columns", columnBeanListMap.get(k));
            varMap.put("package", map.get("package"));
            varMap.put("author", map.get("author"));
            String templateContent = freemarkerService.parseTpl(TemplateFileEnum.SERVICE.getTempFileName(),varMap);
            writeFileService.writeService(templateContent,v.getHumpClassName());
        });

        return "success";
    }

    /**
     * 生成模块的serviceimpl类
     * @return
     */
    @GetMapping("/getproject/serviceimpl")
    public String getServiceImplTemplate(){
        Map<String,Object> map = mapperXmlVarRegistry.getTemplateVar();

        Map<String, TableBean> tableBeanMap = (Map<String, TableBean>)map.get("table");
        Map<String, List<ColumnBean>> columnBeanListMap = (Map<String, List<ColumnBean>>)map.get("columns");
        tableBeanMap.forEach((k,v)->{
            Map<String,Object> varMap = new HashMap<>();
            varMap.put("table", v);
            varMap.put("columns", columnBeanListMap.get(k));
            varMap.put("package", map.get("package"));
            varMap.put("author", map.get("author"));
            String templateContent = freemarkerService.parseTpl(TemplateFileEnum.SERVICE_IMPL.getTempFileName(),varMap);
            writeFileService.writeServiceImpl(templateContent,v.getHumpClassName());
        });

        return "success";
    }


    /**
     * 生成模块的mapperxml类
     * @return
     */
    @GetMapping("/getproject/mapperxml")
    public String getMapperXMLTemplate(){
        Map<String,Object> map = mapperXmlVarRegistry.getTemplateVar();

        Map<String, TableBean> tableBeanMap = (Map<String, TableBean>)map.get("table");
        Map<String, List<ColumnBean>> columnBeanListMap = (Map<String, List<ColumnBean>>)map.get("columns");
        tableBeanMap.forEach((k,v)->{
            Map<String,Object> varMap = new HashMap<>();
            varMap.put("table", v);
            varMap.put("columns", columnBeanListMap.get(k));
            varMap.put("package", map.get("package"));
            varMap.put("author", map.get("author"));
            String templateContent = freemarkerService.parseTpl(TemplateFileEnum.MAPPER_XML.getTempFileName(),varMap);
            writeFileService.writeMapperXml(templateContent,v.getHumpClassName());
        });

        return "success";
    }

    /**
     * 生成模块的controller类
     * @return
     */
    @GetMapping("/getproject/controller")
    public String getControllerTemplate(){
        Map<String,Object> map = serviceImplVarRegistry.getTemplateVar();

        Map<String, TableBean> tableBeanMap = (Map<String, TableBean>)map.get("table");
        Map<String, List<ColumnBean>> columnBeanListMap = (Map<String, List<ColumnBean>>)map.get("columns");
        tableBeanMap.forEach((k,v)->{
            Map<String,Object> varMap = new HashMap<>();
            varMap.put("table", v);
            varMap.put("columns", columnBeanListMap.get(k));
            varMap.put("package", map.get("package"));
            varMap.put("author", map.get("author"));
            String templateContent = freemarkerService.parseTpl(TemplateFileEnum.CONTROLLER.getTempFileName(),varMap);
            writeFileService.writeController(templateContent,v.getHumpClassName());
        });

        return "success";
    }

    /**
     * 生成模块的controller类
     * @return
     */
    @GetMapping("/getproject/test")
    public String getServiceTest(){
        Map<String,Object> map = testVarRegistry.getTemplateVar();

        Map<String, TableBean> tableBeanMap = (Map<String, TableBean>)map.get("table");
        Map<String, List<ColumnBean>> columnBeanListMap = (Map<String, List<ColumnBean>>)map.get("columns");
        tableBeanMap.forEach((k,v)->{
            Map<String,Object> varMap = new HashMap<>();
            varMap.put("table", v);
            varMap.put("columns", columnBeanListMap.get(k));
            varMap.put("package", map.get("package"));
            varMap.put("author", map.get("author"));
            String templateContent = freemarkerService.parseTpl(TemplateFileEnum.TEST.getTempFileName(),varMap);
            writeFileService.writeTest(templateContent,v.getHumpClassName());
        });

        return "success";
    }




    /**
     * 生成dubbo-api的接口服务
     * @return
     */
    @GetMapping("/getproject/facade")
    public String getFacadeTemplate(){
        Map<String,Object> map = serviceVarRegistry.getTemplateVar();

        Map<String, TableBean> tableBeanMap = (Map<String, TableBean>)map.get("table");
        Map<String, List<ColumnBean>> columnBeanListMap = (Map<String, List<ColumnBean>>)map.get("columns");
        tableBeanMap.forEach((k,v)->{
            Map<String,Object> varMap = new HashMap<>();
            varMap.put("table", v);
            varMap.put("columns", columnBeanListMap.get(k));
            varMap.put("package", map.get("package"));
            varMap.put("author", map.get("author"));
            String templateContent = freemarkerService.parseTpl(TemplateFileEnum.FACADE.getTempFileName(),varMap);
            writeFileService.writeFacade(templateContent,v.getHumpClassName());
        });

        return "success";
    }

    /**
     * 生成dubbo-api的接口服务
     * @return
     */
    @GetMapping("/getproject/facadeimpl")
    public String getFacadeImplTemplate(){
        Map<String,Object> map = serviceVarRegistry.getTemplateVar();

        Map<String, TableBean> tableBeanMap = (Map<String, TableBean>)map.get("table");
        Map<String, List<ColumnBean>> columnBeanListMap = (Map<String, List<ColumnBean>>)map.get("columns");
        tableBeanMap.forEach((k,v)->{
            Map<String,Object> varMap = new HashMap<>();
            varMap.put("table", v);
            varMap.put("columns", columnBeanListMap.get(k));
            varMap.put("package", map.get("package"));
            varMap.put("author", map.get("author"));
            String templateContent = freemarkerService.parseTpl(TemplateFileEnum.FACADE_IMPL.getTempFileName(),varMap);
            writeFileService.writeFacadeImpl(templateContent,v.getHumpClassName());
        });

        return "success";
    }


    /**
     * 生成模块的vo类
     * @return
     */
    @GetMapping("/getproject/dto")
    public String getDTOTemplate(){
        Map<String,Object> map = entityVarRegistry.getTemplateVar();

        Map<String, TableBean> tableBeanMap = (Map<String, TableBean>)map.get("table");
        Map<String, List<ColumnBean>> columnBeanListMap = (Map<String, List<ColumnBean>>)map.get("columns");
        tableBeanMap.forEach((k,v)->{
            Map<String,Object> varMap = new HashMap<>();
            varMap.put("table", v);
            varMap.put("columns", columnBeanListMap.get(k));
            varMap.put("package", map.get("package"));
            varMap.put("author", map.get("author"));
            String templateContent = freemarkerService.parseTpl(TemplateFileEnum.DTO.getTempFileName(),varMap);
            writeFileService.writeDTO(templateContent,v.getHumpClassName());
        });

        return "success";
    }

    /**
     * 生成模块的vo类
     * @return
     */
    @GetMapping("/getproject/bo")
    public String getBOTemplate(){
        Map<String,Object> map = entityVarRegistry.getTemplateVar();

        Map<String, TableBean> tableBeanMap = (Map<String, TableBean>)map.get("table");
        Map<String, List<ColumnBean>> columnBeanListMap = (Map<String, List<ColumnBean>>)map.get("columns");
        tableBeanMap.forEach((k,v)->{
            Map<String,Object> varMap = new HashMap<>();
            varMap.put("table", v);
            varMap.put("columns", columnBeanListMap.get(k));
            varMap.put("package", map.get("package"));
            varMap.put("author", map.get("author"));
            String templateContent = freemarkerService.parseTpl(TemplateFileEnum.BUSINESS_OBJECT.getTempFileName(),varMap);
            writeFileService.writeBO(templateContent,v.getHumpClassName());
        });

        return "success";
    }



    /**
     * 生成项目e-r图
     * @return
     */
    @GetMapping("/getproject/erpicture")
    public String getERPictureTemplate(){
        Map<String,Object> map = serviceImplVarRegistry.getTemplateVar();

        Map<String, TableBean> tableBeanMap = (Map<String, TableBean>)map.get("table");
        Map<String, List<ColumnBean>> columnBeanListMap = (Map<String, List<ColumnBean>>)map.get("columns");
        writeFileService.writeERPicture(tableBeanMap,columnBeanListMap);
        return "success";
    }

}
