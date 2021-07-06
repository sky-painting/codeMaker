package com.coderman.codemaker.controller;

import com.alibaba.fastjson.JSON;
import com.coderman.codemaker.bean.ColumnBean;
import com.coderman.codemaker.bean.TableBean;
import com.coderman.codemaker.service.WriteAppModuleService;
import com.coderman.codemaker.service.WriteFileService;
import com.coderman.codemaker.service.registry.DynamicDDDVarRegistry;
import com.coderman.codemaker.service.registry.MapperXmlVarRegistry;
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
    @Resource(name = "mapperXmlVarRegistry")
    private MapperXmlVarRegistry mapperXmlVarRegistry;

    @Resource(name = "dynamicDDDVarRegistry")
    private DynamicDDDVarRegistry dynamicDDDVarRegistry;


    @Autowired
    private WriteFileService writeFileService;

    @Autowired
    private WriteAppModuleService writeFileServiceV2;

    /**
     * 生成所有表对应的项目代码--极简模式
     * @return
     */
    @GetMapping("/makeall")
    public String makeAllProjectCode(){
        Map<String,Object> map = mapperXmlVarRegistry.getTemplateVar();

        Map<String, TableBean> tableBeanMap = (Map<String, TableBean>)map.get("table");
        System.out.println("tableBeanMap====="+ JSON.toJSONString(tableBeanMap));
        Map<String, List<ColumnBean>> columnBeanListMap = (Map<String, List<ColumnBean>>)map.get("columns");
        Map<String,Object> varMap = new HashMap<>();
        //循环写每个表对应的类
        tableBeanMap.forEach((k,v)->{
            varMap.put("table", v);
            varMap.put("columns", columnBeanListMap.get(k));
            varMap.put("package", map.get("package"));
            varMap.put("author", map.get("author"));
            writeFileServiceV2.writeAll(v.getHumpClassName(),varMap,"");
        });
        //写公共服务类
        writeFileServiceV2.writeCommon(varMap,"");
        //渲染e-r图
        writeFileServiceV2.writeERPicture(tableBeanMap,columnBeanListMap);

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
        System.out.println("tableBeanMap====="+ JSON.toJSONString(tableBeanMap));
        Map<String, List<ColumnBean>> columnBeanListMap = (Map<String, List<ColumnBean>>)map.get("columns");
        Map<String,Object> varMap = new HashMap<>();
        //循环写每个表对应的类
        tableBeanMap.forEach((k,v)->{
            varMap.put("table", v);
            varMap.put("columns", columnBeanListMap.get(k));
            varMap.put("package", map.get("package"));
            varMap.put("author", map.get("author"));
            writeFileService.writeAll(v.getHumpClassName(),varMap,"/fast/");
        });
        //写公共服务类
        writeFileService.writeCommon(varMap,"/fast/");
        //渲染e-r图
        writeFileService.writeERPicture(tableBeanMap,columnBeanListMap);

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
                writeFileServiceV2.writeAll(v.getHumpClassName(),varMap,"");
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
                writeFileServiceV2.writeAll(v.getHumpClassName(),varMap,"/fast/");
            }
        });

        return "success";
    }


    @GetMapping("/makeddd")
    public String makeDynamicDDD(){
        Map<String, Object> dynamicDDDMap = dynamicDDDVarRegistry.getRegistVarMap();
        writeFileServiceV2.writeDynamicDDD(dynamicDDDMap);
        return "success";
    }


}
