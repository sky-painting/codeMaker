package com.tianhua.codemaker.controller;

import com.tianhua.codemaker.bean.ColumnBean;
import com.tianhua.codemaker.bean.TableBean;
import com.tianhua.codemaker.service.WriteCodeService;
import com.tianhua.codemaker.service.WriteDynamicCodeService;
import com.tianhua.codemaker.service.write.WriteSpringbootService;
import com.tianhua.codemaker.service.registry.element.DynamicDDDVarRegistry;
import com.tianhua.codemaker.service.registry.element.MapperXmlVarRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;


/**
 * description: CodeMakerController <br>
 * date: 2020/7/8 13:24 <br>
 * author: coderman <br>
 * version: 1.0 <br>
 * 提供统一的，一站式的代码生成接口服务
 */
@RestController
public class CodeMakerController {

    private Logger logger = LoggerFactory.getLogger(CodeMakerController.class);

    @Resource(name = "mapperXmlVarRegistry")
    private MapperXmlVarRegistry mapperXmlVarRegistry;

    @Resource(name = "dynamicDDDVarRegistry")
    private DynamicDDDVarRegistry dynamicDDDVarRegistry;


    @Autowired
    private WriteSpringbootService writeFileService;

    @Autowired
    private WriteDynamicCodeService writeDynamicCodeService;

    @Autowired
    private WriteCodeService writeCodeService;
    /**
     * 生成所有表对应的项目代码--极简模式
     * @return
     */
    @GetMapping("/makeall")
    public String makeAllProjectCode(){
        Map<String,Object> map = mapperXmlVarRegistry.getTemplateVar();

        if(map.containsKey("dynamicddd")){
            writeDynamicCodeService.writeAllWithDDD(map);
        }else {
            logger.info("纯数据库模式生成---->");
            Map<String, TableBean> tableBeanMap = (Map<String, TableBean>)map.get("table");
            Map<String, List<ColumnBean>> columnBeanListMap = (Map<String, List<ColumnBean>>)map.get("columns");
            Map<String,Object> varMap = new HashMap<>();
            //循环写每个表对应的类
            tableBeanMap.forEach((k,v)->{
                varMap.put("table", v);
                varMap.put("columns", columnBeanListMap.get(k));
                varMap.put("package", map.get("package"));
                varMap.put("author", map.get("author"));
                varMap.put("packageInfrast", map.get("packageInfrast"));
                varMap.put("packageDomain", map.get("packageDomain"));

                writeCodeService.writeAll(v.getHumpClassName(),varMap,"");
            });
            //写公共服务类
            writeCodeService.writeCommon(varMap);
            //写纯DB模式下的配置信息和pom文件
            writeDynamicCodeService.writeConfigForDBModel(varMap);
            //渲染e-r图
            writeCodeService.writeERPicture(tableBeanMap,columnBeanListMap);
        }

        return "success";
    }

    /**
     * 生成所有表对应的项目代码--极速模式
     * @return
     */
    @GetMapping("/makeallfast")
    public String makeAllProjectCodeFast(){
        Map<String,Object> map = mapperXmlVarRegistry.getTemplateVar();

        Map<String, TableBean> tableBeanMap = (Map<String, TableBean>)map.get("table");
        Map<String, List<ColumnBean>> columnBeanListMap = (Map<String, List<ColumnBean>>)map.get("columns");
        Map<String,Object> varMap = new HashMap<>();
        //循环写每个表对应的类
        tableBeanMap.forEach((k,v)->{
            varMap.put("table", v);
            varMap.put("columns", columnBeanListMap.get(k));
            varMap.put("package", map.get("package"));
            varMap.put("author", map.get("author"));
            varMap.put("packageInfrast", map.get("packageInfrast"));
            varMap.put("packageDomain", map.get("packageDomain"));

            writeFileService.writeAll(v.getHumpClassName(),varMap,"/fast/");
        });

        //写公共服务类
        writeCodeService.writeCommon(varMap);
        //写纯DB模式下的配置信息和pom文件
        writeDynamicCodeService.writeConfigForDBModel(varMap);
        //渲染e-r图
        writeCodeService.writeERPicture(tableBeanMap,columnBeanListMap);

        return "success";
    }



    /**
     * 生成指定的表对应的项目代码--极简模式
     * @param tableNames
     * @return
     */
    @GetMapping("/makemodules")
    public String makeModulesCode(@RequestParam(name = "tableNames") String tableNames){

        String [] tablesArray = tableNames.split(",");
        Set<String> tableSet = new HashSet<>();
        for(String table : tablesArray){
            tableSet.add(table);
        }

        Map<String,Object> map = mapperXmlVarRegistry.getTemplateVar();

        Map<String, TableBean> tableBeanMap = (Map<String, TableBean>)map.get("table");
        Map<String, List<ColumnBean>> columnBeanListMap = (Map<String, List<ColumnBean>>)map.get("columns");
        tableBeanMap.forEach((k,v)->{
            if(tableSet.contains(v.getTableName())){
                Map<String,Object> varMap = new HashMap<>();
                varMap.put("table", v);
                varMap.put("columns", columnBeanListMap.get(k));
                varMap.put("package", map.get("package"));
                varMap.put("author", map.get("author"));
                varMap.put("packageInfrast", map.get("packageInfrast"));
                varMap.put("packageDomain", map.get("packageDomain"));

                writeCodeService.writeAll(v.getHumpClassName(),varMap,"");
            }
        });

        return "success";
    }
    /**
     * 生成指定的表对应的项目代码--极速模式
     * @param tableNames
     * @return
     */
    @GetMapping("/makemodulesfast")
    public String makeModulesCodeFast(@RequestParam(name = "tableNames") String tableNames){

        String [] tablesArray = tableNames.split(",");
        Set<String> tableSet = new HashSet<>();
        for(String table : tablesArray){
            tableSet.add(table);
        }

        Map<String,Object> map = mapperXmlVarRegistry.getTemplateVar();

        Map<String, TableBean> tableBeanMap = (Map<String, TableBean>)map.get("table");
        Map<String, List<ColumnBean>> columnBeanListMap = (Map<String, List<ColumnBean>>)map.get("columns");
        tableBeanMap.forEach((k,v)->{
            if(tableSet.contains(v.getTableName())){
                Map<String,Object> varMap = new HashMap<>();
                varMap.put("table", v);
                varMap.put("columns", columnBeanListMap.get(k));
                varMap.put("package", map.get("package"));
                varMap.put("author", map.get("author"));
                varMap.put("packageInfrast", map.get("packageInfrast"));
                varMap.put("packageDomain", map.get("packageDomain"));

                writeCodeService.writeAll(v.getHumpClassName(),varMap,"/fast/");
            }
        });

        return "success";
    }


    @GetMapping("/makeddd")
    public String makeDynamicDDD(){
        Map<String, Object> dynamicDDDMap = dynamicDDDVarRegistry.getRegistVarMap();
        writeDynamicCodeService.writeDynamicDDD(dynamicDDDMap);
        return "success";
    }


}
