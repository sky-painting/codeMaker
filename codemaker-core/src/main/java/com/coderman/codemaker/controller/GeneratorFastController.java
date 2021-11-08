package com.coderman.codemaker.controller;

import com.coderman.codemaker.bean.ColumnBean;
import com.coderman.codemaker.bean.TableBean;
import com.coderman.codemaker.enums.TemplateFileEnum;
import com.coderman.codemaker.service.write.WriteSpringbootService;
import com.coderman.codemaker.service.registry.element.*;
import com.coderman.codemaker.utils.FreemarkerUtils;
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
public class GeneratorFastController {
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


    @Autowired
    private WriteSpringbootService writeFileService;


    /**
     * 生成模块的entity类
     * @return
     */
    @GetMapping("/getproject/fast/entity")
    public String getEntityTemplate(){
        Map<String, Object> map = entityVarRegistry.getTemplateVar();

        Map<String, TableBean> tableBeanMap = (Map<String, TableBean>)map.get("table");
        Map<String, List<ColumnBean>> columnBeanListMap = (Map<String, List<ColumnBean>>)map.get("columns");
        tableBeanMap.forEach((k,v)->{
            Map<String, Object> varMap = new HashMap<>();
            varMap.put("table", v);
            varMap.put("columns", columnBeanListMap.get(k));
            varMap.put("package", map.get("package"));
            varMap.put("author", map.get("author"));

            String templateContent = FreemarkerUtils.parseTpl("/fast/"+ TemplateFileEnum.ENTITY.getTempFileName(),varMap);
            writeFileService.writeEntity(templateContent,v.getHumpClassName());
        });

        return "success";
    }

    /**
     * 生成模块的vo类
     * @return
     */
    @GetMapping("/getproject/fast/vo")
    public String getVOTemplate(){
        Map<String, Object> map = entityVarRegistry.getTemplateVar();

        Map<String, TableBean> tableBeanMap = (Map<String, TableBean>)map.get("table");
        Map<String, List<ColumnBean>> columnBeanListMap = (Map<String, List<ColumnBean>>)map.get("columns");
        tableBeanMap.forEach((k,v)->{
            Map<String, Object> varMap = new HashMap<>();
            varMap.put("table", v);
            varMap.put("columns", columnBeanListMap.get(k));
            varMap.put("package", map.get("package"));
            varMap.put("author", map.get("author"));
            String templateContent = FreemarkerUtils.parseTpl("/fast/"+ TemplateFileEnum.VO.getTempFileName(),varMap);
            writeFileService.writeVO(templateContent,v.getHumpClassName());
        });

        return "success";
    }

    /**
     * 生成模块的mapper类
     * @return
     */
    @GetMapping("/getproject/fast/mapper")
    public String getMapperTemplate(){
        Map<String, Object> map = mapperVarRegistry.getTemplateVar();
        Map<String, TableBean> tableBeanMap = (Map<String, TableBean>)map.get("table");
        Map<String, List<ColumnBean>> columnBeanListMap = (Map<String, List<ColumnBean>>)map.get("columns");
        tableBeanMap.forEach((k,v)->{
            Map<String, Object> varMap = new HashMap<>();
            varMap.put("table", v);
            varMap.put("columns", columnBeanListMap.get(k));
            varMap.put("package", map.get("package"));
            varMap.put("author", map.get("author"));
            String templateContent = FreemarkerUtils.parseTpl("/fast/"+ TemplateFileEnum.MAPPER.getTempFileName(),varMap);
            writeFileService.writeMapper(templateContent,v.getHumpClassName());

        });

        return "success";
    }

    /**
     * 生成模块的service类
     * @return
     */
    @GetMapping("/getproject/fast/service")
    public String getServiceTemplate(){
        Map<String, Object> map = serviceVarRegistry.getTemplateVar();

        Map<String, TableBean> tableBeanMap = (Map<String, TableBean>)map.get("table");
        Map<String, List<ColumnBean>> columnBeanListMap = (Map<String, List<ColumnBean>>)map.get("columns");
        tableBeanMap.forEach((k,v)->{
            Map<String, Object> varMap = new HashMap<>();
            varMap.put("table", v);
            varMap.put("columns", columnBeanListMap.get(k));
            varMap.put("package", map.get("package"));
            varMap.put("author", map.get("author"));
            String templateContent = FreemarkerUtils.parseTpl("/fast/"+ TemplateFileEnum.SERVICE.getTempFileName(),varMap);
            writeFileService.writeService(templateContent,v.getHumpClassName());
        });

        return "success";
    }

    /**
     * 生成模块的serviceimpl类
     * @return
     */
    @GetMapping("/getproject/fast/serviceimpl")
    public String getServiceImplTemplate(){
        Map<String, Object> map = mapperXmlVarRegistry.getTemplateVar();

        Map<String, TableBean> tableBeanMap = (Map<String, TableBean>)map.get("table");
        Map<String, List<ColumnBean>> columnBeanListMap = (Map<String, List<ColumnBean>>)map.get("columns");
        tableBeanMap.forEach((k,v)->{
            Map<String, Object> varMap = new HashMap<>();
            varMap.put("table", v);
            varMap.put("columns", columnBeanListMap.get(k));
            varMap.put("package", map.get("package"));
            varMap.put("author", map.get("author"));
            String templateContent = FreemarkerUtils.parseTpl("/fast/"+ TemplateFileEnum.SERVICE_IMPL.getTempFileName(),varMap);
            writeFileService.writeServiceImpl(templateContent,v.getHumpClassName());
        });

        return "success";
    }


    /**
     * 生成模块的mapperxml类
     * @return
     */
    @GetMapping("/getproject/fast/mapperxml")
    public String getMapperXMLTemplate(){
        Map<String, Object> map = serviceImplVarRegistry.getTemplateVar();

        Map<String, TableBean> tableBeanMap = (Map<String, TableBean>)map.get("table");
        Map<String, List<ColumnBean>> columnBeanListMap = (Map<String, List<ColumnBean>>)map.get("columns");
        tableBeanMap.forEach((k,v)->{
            Map<String, Object> varMap = new HashMap<>();
            varMap.put("table", v);
            varMap.put("columns", columnBeanListMap.get(k));
            varMap.put("package", map.get("package"));
            varMap.put("author", map.get("author"));
            String templateContent = FreemarkerUtils.parseTpl("/fast/"+ TemplateFileEnum.MAPPER_XML.getTempFileName(),varMap);
            writeFileService.writeMapperXml(templateContent,v.getHumpClassName());
        });

        return "success";
    }

    /**
     * 生成模块的controller类
     * @return
     */
    @GetMapping("/getproject/fast/controller")
    public String getControllerTemplate(){
        Map<String, Object> map = serviceImplVarRegistry.getTemplateVar();

        Map<String, TableBean> tableBeanMap = (Map<String, TableBean>)map.get("table");
        Map<String, List<ColumnBean>> columnBeanListMap = (Map<String, List<ColumnBean>>)map.get("columns");
        tableBeanMap.forEach((k,v)->{
            Map<String, Object> varMap = new HashMap<>();
            varMap.put("table", v);
            varMap.put("columns", columnBeanListMap.get(k));
            varMap.put("package", map.get("package"));
            varMap.put("author", map.get("author"));
            String templateContent = FreemarkerUtils.parseTpl("/fast/"+ TemplateFileEnum.CONTROLLER.getTempFileName(),varMap);
            writeFileService.writeController(templateContent,v.getHumpClassName());
        });

        return "success";
    }

    /**
     * 生成模块的controller类
     * @return
     */
    @GetMapping("/getproject/fast/test")
    public String getServiceTest(){
        Map<String, Object> map = testVarRegistry.getTemplateVar();

        Map<String, TableBean> tableBeanMap = (Map<String, TableBean>)map.get("table");
        Map<String, List<ColumnBean>> columnBeanListMap = (Map<String, List<ColumnBean>>)map.get("columns");
        tableBeanMap.forEach((k,v)->{
            Map<String, Object> varMap = new HashMap<>();
            varMap.put("table", v);
            varMap.put("columns", columnBeanListMap.get(k));
            varMap.put("package", map.get("package"));
            varMap.put("author", map.get("author"));
            String templateContent = FreemarkerUtils.parseTpl("/fast/"+ TemplateFileEnum.TEST.getTempFileName(),varMap);
            writeFileService.writeTest(templateContent,v.getHumpClassName());
        });

        return "success";
    }


}
