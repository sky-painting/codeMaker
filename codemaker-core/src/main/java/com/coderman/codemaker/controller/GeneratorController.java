package com.coderman.codemaker.controller;

import com.coderman.codemaker.bean.ColumnBean;
import com.coderman.codemaker.bean.TableBean;
import com.coderman.codemaker.bean.WriteContentBean;
import com.coderman.codemaker.bean.plantuml.ClassBean;
import com.coderman.codemaker.bean.plantuml.EnumBean;
import com.coderman.codemaker.bean.plantuml.InterfaceBean;
import com.coderman.codemaker.enums.TemplateFileEnum;
import com.coderman.codemaker.service.WriteCodeService;
import com.coderman.codemaker.service.registry.element.*;
import com.coderman.codemaker.service.template.FreemarkerService;
import com.coderman.codemaker.service.WriteDynamicCodeService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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

    @Autowired
    private WriteCodeService writeFileService;


    @Autowired
    private FreemarkerService freemarkerService;

    @Autowired
    private WriteDynamicCodeService writeDynamicCodeService;

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
     * 生成模块的do类
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
        if(map.containsKey("dynamicddd")){
            List<ClassBean> classBeanList = (List<ClassBean>)map.get("adaptervo");
            writeDynamicCodeService.writeVO(classBeanList);
        }else {
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
        }


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
            templateContent = freemarkerService.parseTpl(TemplateFileEnum.SERVICE_IMPL.getTempFileName(),varMap);
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
        if(map.containsKey("dynamicddd")){
            List<ClassBean> classBeanList = (List<ClassBean>)map.get("controller");
            writeDynamicCodeService.writeController(classBeanList);
        }else {
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

        }

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
        if(map.containsKey("dynamicddd")){
            List<InterfaceBean> interfaceBeanList = (List<InterfaceBean>)map.get("derivefacade");
            writeDynamicCodeService.writeFacade(interfaceBeanList);

            List<ClassBean> classBeanList = (List<ClassBean>)map.get("derivefacadeimpl");
            writeDynamicCodeService.writeFacadeImpl(classBeanList);
        }else {
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

                templateContent = freemarkerService.parseTpl(TemplateFileEnum.FACADE_IMPL.getTempFileName(),varMap);
                writeFileService.writeFacadeImpl(templateContent,v.getHumpClassName());
            });
        }

        return "success";
    }


    /**
     * 生成模块的dto类
     * @return
     */
    @GetMapping("/getproject/dto")
    public String getDTOTemplate(){
        Map<String,Object> map = entityVarRegistry.getTemplateVar();
        if(map.containsKey("dynamicddd")){
            List<ClassBean> classBeanList = (List<ClassBean>)map.get("derivedto");
            writeDynamicCodeService.writeDTO(classBeanList);
        }else {
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
        }

        return "success";
    }

    /**
     * 生成模块的bo类
     * @return
     */
    @GetMapping("/getproject/bo")
    public String getBOTemplate(){
        Map<String,Object> map = entityVarRegistry.getTemplateVar();
        if(map.containsKey("dynamicddd")){
            List<ClassBean> classBeanList = (List<ClassBean>)map.get("domainbo");
            writeDynamicCodeService.writeBO(classBeanList);
        }else {
            Map<String, TableBean> tableBeanMap = (Map<String, TableBean>)map.get("table");
            Map<String, List<ColumnBean>> columnBeanListMap = (Map<String, List<ColumnBean>>)map.get("columns");
            tableBeanMap.forEach((k,v)->{
                Map<String,Object> varMap = new HashMap<>();
                varMap.put("table", v);
                varMap.put("columns", columnBeanListMap.get(k));
                varMap.put("package", map.get("package"));
                varMap.put("author", map.get("author"));
                String templateContent = freemarkerService.parseTpl(TemplateFileEnum.BUSINESS_OBJECT.getTempFileName(),varMap);
                WriteContentBean writeContentBean = WriteContentBean.builder().content(templateContent)
                            .templateName(TemplateFileEnum.BUSINESS_OBJECT.getTempFileName())
                            .humpClassName(v.getHumpClassName()).build();

                writeFileService.writeBO(writeContentBean);
            });
        }

        return "success";
    }


    /**
     * 生成模块的valueobject+enum类
     * @return
     */
    @GetMapping("/getproject/valueobject")
    public String getValueObject(){
        Map<String,Object> map = entityVarRegistry.getTemplateVar();
        if(map.containsKey("dynamicddd")){
            List<ClassBean> valueObjectBeanList = (List<ClassBean>)map.get("domainvalueobject");
            List<EnumBean> enumBeanList = (List<EnumBean>)map.get("valueobjectenum");
            writeDynamicCodeService.writeValueObject(valueObjectBeanList);
            writeDynamicCodeService.writeEnum(enumBeanList);
        }else {
            log.warn("valueobject class generation depends on plantuml..........!!!!!!!");
        }

        return "success";
    }

    /**
     * 生成模块的msgbody类
     * @return
     */
    @GetMapping("/getproject/msgbody")
    public String getMsgBody(){
        Map<String,Object> map = entityVarRegistry.getTemplateVar();
        if(map.containsKey("dynamicddd")){
            List<ClassBean> classBeanList = (List<ClassBean>)map.get("domainmsg");
            writeDynamicCodeService.writeMsgBody(classBeanList);
        }else {
            log.warn("msgbody class generation depends on plantuml..........!!!!!!!");
        }
        return "success";
    }

    /**
     * 生成模块的gataway类
     * @return
     */
    @GetMapping("/getproject/gataway")
    public String getDomainGataway(){
        Map<String,Object> map = entityVarRegistry.getTemplateVar();
        if(map.containsKey("dynamicddd")){
            List<InterfaceBean> gatawayBeanList = (List<InterfaceBean>)map.get("gataway");
            List<InterfaceBean> repositoryBeanList = (List<InterfaceBean>)map.get("repository");
            gatawayBeanList.addAll(repositoryBeanList);
            writeDynamicCodeService.writeDomainGataway(gatawayBeanList);

            List<ClassBean> gatawayImplBeanList = (List<ClassBean>)map.get("gatawayimpl");
            writeDynamicCodeService.writeGatawayImpl(gatawayImplBeanList);

            List<ClassBean> repositoryImplBeanList = (List<ClassBean>)map.get("repositoryimpl");
            writeDynamicCodeService.writeRepositoryImpl(repositoryImplBeanList);
        }else {
            //gataway需要依赖plantUML类图标示，否则基于数据库表生成会显得不伦不类
            log.warn("gataway class generation depends on plantuml..........!!!!!!!");
        }
        return "success";
    }


    /**
     * 生成模块的acladapter类
     * @return
     */
    @GetMapping("/getproject/acladapter")
    public String getAclAdapter(){
        Map<String,Object> map = entityVarRegistry.getTemplateVar();
        if(map.containsKey("dynamicddd")){
            List<InterfaceBean> aclBeanList = (List<InterfaceBean>)map.get("infrastacl");
            List<ClassBean> aclImplBeanList = (List<ClassBean>)map.get("infrastaclimpl");

            List<ClassBean> aclParamBeanList = (List<ClassBean>)map.get("infrastaclparam");
            writeDynamicCodeService.writeAclInterface(aclBeanList);
            writeDynamicCodeService.writeAclInterfaceImpl(aclImplBeanList);
            writeDynamicCodeService.writeAclInterfaceParam(aclParamBeanList);
        }else {
            //gataway需要依赖plantUML类图标示，否则基于数据库表生成会显得不伦不类
            log.warn("gataway class generation depends on plantuml..........!!!!!!!");
        }
        return "success";
    }

    /**
     * 生成模块的command类
     * @return
     */
    @GetMapping("/getproject/command")
    public String getCommand(){
        Map<String,Object> map = entityVarRegistry.getTemplateVar();
        if(map.containsKey("dynamicddd")){
            List<ClassBean> commandBeanList = (List<ClassBean>)map.get("cmd");
            writeDynamicCodeService.writeCommand(commandBeanList);
        }else {
            //gataway需要依赖plantUML类图标示，否则基于数据库表生成会显得不伦不类
            log.warn("gataway class generation depends on plantuml..........!!!!!!!");
        }
        return "success";
    }

    /**
     * 生成模块的executor类
     * @return
     */
    @GetMapping("/getproject/executor")
    public String getExecutor(){
        Map<String,Object> map = entityVarRegistry.getTemplateVar();
        if(map.containsKey("dynamicddd")){
            List<ClassBean> exeBeanList = (List<ClassBean>)map.get("exeClass");
            List<InterfaceBean> exeInterfaceBeanList = (List<InterfaceBean>)map.get("exeInterface");
            writeDynamicCodeService.writeAppExeImpl(exeBeanList);
            writeDynamicCodeService.writeAppExeInterface(exeInterfaceBeanList);
        }else {
            //gataway需要依赖plantUML类图标示，否则基于数据库表生成会显得不伦不类
            log.warn("gataway class generation depends on plantuml..........!!!!!!!");
        }
        return "success";
    }


    /**
     * 生成模块的command类
     * @return
     */
    @GetMapping("/getproject/factory")
    public String getFactory(){
        Map<String,Object> map = entityVarRegistry.getTemplateVar();
        if(map.containsKey("dynamicddd")){
            List<ClassBean> factoryBeanList = (List<ClassBean>)map.get("domainfactory");
            writeDynamicCodeService.writeFactory(factoryBeanList);
        }else {
            //factory需要依赖plantUML类图标示，否则基于数据库表生成会显得不伦不类
            log.warn("factory class generation depends on plantuml..........!!!!!!!");
        }
        return "success";
    }


    /**
     * 生成模块的dtoboconvert接口
     * @return
     */
    @GetMapping("/getproject/dtoboconvert")
    public String getDTOBOConvert(){
        Map<String,Object> map = entityVarRegistry.getTemplateVar();
        if(map.containsKey("dynamicddd")){
            List<InterfaceBean> dtoboconvertBeanList = (List<InterfaceBean>)map.get("dtoboconvert");
            writeDynamicCodeService.writeDTOBOConvert(dtoboconvertBeanList);
        }else {
            //dtoboconvert需要依赖plantUML类图标示，否则基于数据库表生成会显得不伦不类
            log.warn("dtoboconvert class generation depends on plantuml..........!!!!!!!");
        }
        return "success";
    }

    /**
     * 生成模块的dtoboconvert接口
     * @return
     */
    @GetMapping("/getproject/voboconvert")
    public String getVOBOConvert(){
        Map<String,Object> map = entityVarRegistry.getTemplateVar();
        if(map.containsKey("dynamicddd")){
            List<InterfaceBean> voboconvertBeanList = (List<InterfaceBean>)map.get("voboconvert");
            writeDynamicCodeService.writeVOBOConvert(voboconvertBeanList);
        }else {
            //voboconvert需要依赖plantUML类图标示，否则基于数据库表生成会显得不伦不类
            log.warn("voboconvert class generation depends on plantuml..........!!!!!!!");
        }
        return "success";
    }
    /**
     * 生成模块的dtoboconvert接口
     * @return
     */
    @GetMapping("/getproject/doboconvert")
    public String getDOBOConvert(){
        Map<String,Object> map = entityVarRegistry.getTemplateVar();
        if(map.containsKey("dynamicddd")){
            List<InterfaceBean> doboconvertBeanList = (List<InterfaceBean>)map.get("doboconvert");
            writeDynamicCodeService.writeDOBOConvert(doboconvertBeanList);
        }else {
            log.warn("doboconvert class generation depends on plantuml..........!!!!!!!");
        }
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
