package com.tianhua.codemaker.service.template;

import com.tianhua.codemaker.bean.ColumnBean;
import com.tianhua.codemaker.bean.TableBean;
import com.tianhua.codemaker.config.AppServiceConfig;
import com.tianhua.codemaker.dao.SqlMapper;
import com.coderman.codemaker.dberparse.ERPlantUMLParseService;
import com.coderman.codemaker.dberparse.bean.EntityBean;
import com.tianhua.codemaker.utils.StringHelperUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * description: TemlateVarService <br>
 * date: 2020/7/7 19:15 <br>
 * author: coderman <br>
 * version: 1.0 <br>
 */
@Service
public class TemlateVarService {

    private  final Logger LOGGER = LoggerFactory.getLogger(TemlateVarService.class);

    @Autowired
    private AppServiceConfig appServiceConfig;

    @Resource
    private SqlMapper sqlMapper;

    private Map<String, TableBean> tableBeanMap;

    private Map<String, List<ColumnBean>> columnBeanMap;

    private Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");

    /**
     * plantuml e-r图解析内容
     */
    private List<String> erContentList;

    /**
     * plantuml e-r图解析对象列表
     */
    private List<EntityBean> entityBeanList;


    @Autowired
    private ERPlantUMLParseService erPlantUMLParseService;

    @Autowired
    private ResourceLoader resourceLoader;

    /**
     * 获取加工后的tableBean元数据
     * @return
     */
    public Map<String, TableBean> exeGetTableBeanMap(){

        String dbName = appServiceConfig.getDbName();
        //不链接db
        if(!appServiceConfig.getLinkDB()){
            return buildTableBeanMap();
        }
        List<TableBean> tableBeanList = sqlMapper.getDBTableBeanList(dbName);
        Map<String, TableBean> tableBeanMap = new HashMap<>();
        tableBeanList.stream().forEach(e->{
            String humpTableName = getHumpTableName(e.getTableName());
            e.setHumpTableName(humpTableName);
            String humpClassName = StringHelperUtils.getHumpClassName(humpTableName);
            e.setHumpClassName(humpClassName);
            String tableName ;
            if(e.getTableName().contains("_")){
                String[] tableArr = e.getTableName().split("_");
                if(isNum(tableArr[tableArr.length - 1])){
                    String[] newArr = Arrays.copyOfRange(tableArr, 0, tableArr.length - 1);
                    tableName = StringUtils.join(newArr,"_");
                }else {
                    tableName = e.getTableName();
                }
            }else {
                tableName = e.getTableName();
            }
            e.setTableName(tableName);
            tableBeanMap.put(tableName,e);
        });
        return tableBeanMap;
    }

    /**
     * 获取加工后的columnBean元数据
     * @return
     */
    private Map<String, List<ColumnBean>> exeGetColumnBeanMap(){
        String dbName = appServiceConfig.getDbName();
        //不链接db
        if(!appServiceConfig.getLinkDB()){
            return buildColumnBeanMap();
        }

        List<ColumnBean> columnBeanList = sqlMapper.getColumnBeanList(dbName);
        columnBeanList.stream().forEach(e->{
            String columnFieldName = getHumpTableName(e.getColumnName());
            e.setColumnFieldName(columnFieldName);
            String columnTypeName = getColumnTypeName(e.getColumnType());
            e.setColumnTypeName(columnTypeName);
            String columnUperName = columnFieldName.substring(0,1).toUpperCase().concat(columnFieldName.substring(1));

            e.setColumnUperName(columnUperName);
            String tableName;
            if(e.getTableName().contains("_")){
                String[] tableArr = e.getTableName().split("_");
                if(isNum(tableArr[tableArr.length - 1])){
                    String[] newArr = Arrays.copyOfRange(tableArr, 0, tableArr.length - 1);
                    tableName = StringUtils.join(newArr,"_");
                }else {
                    tableName = e.getTableName();
                }
            }else {
                tableName = e.getTableName();
            }
            e.setTableName(tableName);
        });

        Set<String> set = new HashSet<>();
        List<ColumnBean> columnBeanListNew = new ArrayList<>();
        columnBeanList.stream().forEach(k->{
            String key = k.getTableName()+"-"+k.getColumnName();
            if(!set.contains(key)){
                set.add(key);
                columnBeanListNew.add(k);
            }
        });

        Map<String, List<ColumnBean>> stringListMap = columnBeanListNew.stream().collect(Collectors.groupingBy(ColumnBean::getTableName));
        return stringListMap;
    }




    /**
     * 获取表名对应的变量名
     * eg: user_info->userInfo
     * staff_education_info->staffEducationInfo
     *
     * @param tableName
     * @return
     */
    private String getHumpTableName(String tableName){
        String resultName = "";

        if(!tableName.contains("_")){
            resultName = tableName;
        }else {
            String[] tableNameArr = tableName.split("_");
            int length = tableNameArr.length;
            StringBuilder builder = new StringBuilder();

            if(isNum(tableNameArr[length - 1])){
                if(length == 2){
                    resultName = tableNameArr[0];
                }else {
                    builder.append(tableNameArr[0]);
                    for (int i = 1;i < length - 1;i++){
                        String tag = tableNameArr[i].substring(0,1).toUpperCase().concat(tableNameArr[i].substring(1));
                        builder.append(tag);
                    }
                    resultName = builder.toString();
                }
            }else {
                builder.append(tableNameArr[0]);
                for (int i = 1;i < length;i++){
                    String tag = tableNameArr[i].substring(0,1).toUpperCase().concat(tableNameArr[i].substring(1));
                    builder.append(tag);
                }
                resultName = builder.toString();
            }

        }
        return resultName;
    }

    private boolean isNum(String str){
        return pattern.matcher(str).matches();
    }



    /**
     * 获取字段类型对应的javaentity的java类型
     * @param columnType
     * @return
     */
    private String getColumnTypeName(String columnType){
        if(columnType.toLowerCase().contains("blob") || columnType.toLowerCase().contains("text")){
            return "String";
        }
        else if(columnType.contains("bigint")){
            return "Long";
        }
        else if(columnType.contains("int")){
            return "Integer";
        }
        else if(columnType.contains("varchar")){
            return "String";
        }
        else if(columnType.contains("boolean")){
            return "Boolean";
        }
        else if(columnType.contains("date")){
            return "Date";
        }
        else if(columnType.contains("double")){
            return "Double";
        }
        else if(columnType.contains("timestamp")){
            return "Date";
        }
        else if(columnType.contains("time")){
            return "Date";
        }
        else if(columnType.toUpperCase().contains("DECIMAL")){
            return "BigDecimal";
        }else if(columnType.contains("json")){
            return "String";
        }
        return null;
    }

    public Map<String, TableBean> getTableBeanMap() {

        if(tableBeanMap == null){
            LOGGER.info("init tableBeanMap start");
            tableBeanMap = exeGetTableBeanMap();
        }
        return tableBeanMap;
    }

    public Map<String, List<ColumnBean>> getColumnBeanMap() {
        if(columnBeanMap == null){
            LOGGER.info("init columnBeanMap start");
            columnBeanMap = exeGetColumnBeanMap();
        }
        return columnBeanMap;
    }


    /**
     * 读取plantUMl文件
     * @return
     */
    private  List<String> readPlantUMLContent(){
        try {
            org.springframework.core.io.Resource resource = resourceLoader.getResource("classpath:er-plantuml/"+appServiceConfig.getApplicationName() + "/" + appServiceConfig.getDbName() + ".puml");
            File file = resource.getFile();
            //File file = ResourceUtils.getFile("classpath:er-plantuml/"+appServiceConfig.getApplicationName() + "/" + appServiceConfig.getDbName() + ".puml");
            return FileUtils.readLines(file,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从plantuml文档中构建实体模型
     * @return
     */
    public List<EntityBean> getEntityBeanList(){
        if(CollectionUtils.isEmpty(this.entityBeanList)){
            this.erContentList = readPlantUMLContent();
            this.entityBeanList = erPlantUMLParseService.getPlantUmlContextBean(erContentList);
        }
        return this.entityBeanList;
    }


    /**
     * 从e-r图文档中构建tableBean模型
     * @return
     */
    private Map<String, TableBean> buildTableBeanMap(){
        List<EntityBean> entityBeanList = getEntityBeanList();
        Map<String, TableBean> tableBeanMap = new HashMap<>();
        for (EntityBean entityBean : entityBeanList){
            String humpTableName = getHumpTableName(entityBean.getTableName());
            TableBean tableBean = new TableBean();

            tableBean.setTableComment(entityBean.getTableDesc());
            tableBean.setHumpTableName(humpTableName);

            String humpClassName = StringHelperUtils.getHumpClassName(humpTableName);
            tableBean.setHumpClassName(humpClassName);

            String tableName ;
            if(entityBean.getTableName().contains("_")){
                String[] tableArr = entityBean.getTableName().split("_");
                if(isNum(tableArr[tableArr.length - 1])){
                    String[] newArr = Arrays.copyOfRange(tableArr, 0, tableArr.length - 1);
                    tableName = StringUtils.join(newArr,"_");
                }else {
                    tableName = entityBean.getTableName();
                }
            }else {
                tableName = entityBean.getTableName();
            }
            tableBean.setTableName(tableName);
            tableBeanMap.put(tableName,tableBean);
        }
        return tableBeanMap;
    }

    /**
     * 从e-r图文档中构建columnBean模型
     * @return
     */
    private Map<String, List<ColumnBean>> buildColumnBeanMap() {
        List<EntityBean> entityBeanList = getEntityBeanList();
        Map<String, List<ColumnBean>> columnBeanMap = new HashMap<>();
        for (EntityBean entityBean : entityBeanList) {
            List<ColumnBean> columnBeans = new ArrayList<>();
            entityBean.getColumnBeanList().forEach(entityFieldBean -> {
                ColumnBean columnBean = new ColumnBean();
                String columnFieldName = getHumpTableName(entityFieldBean.getColumnName());
                columnBean.setColumnFieldName(columnFieldName);

                String columnTypeName = getColumnTypeName(entityFieldBean.getColumnType());
                columnBean.setColumnTypeName(columnTypeName);
                String columnUperName = columnFieldName.substring(0,1).toUpperCase().concat(columnFieldName.substring(1));
                columnBean.setColumnUperName(columnUperName);
                columnBean.setColumnName(entityFieldBean.getColumnName());
                columnBean.setColumnComment(entityFieldBean.getColumnDesc());
                String tableName;
                if(entityBean.getTableName().contains("_")){
                    String[] tableArr = entityBean.getTableName().split("_");
                    if(isNum(tableArr[tableArr.length - 1])){
                        String[] newArr = Arrays.copyOfRange(tableArr, 0, tableArr.length - 1);
                        tableName = StringUtils.join(newArr,"_");
                    }else {
                        tableName = entityBean.getTableName();
                    }
                }else {
                    tableName = entityBean.getTableName();
                }
                columnBean.setTableName(tableName);
                columnBeans.add(columnBean);
            });
            columnBeanMap.put(entityBean.getTableName(),columnBeans);
        }
        return columnBeanMap;
    }

    /**
     * 获取解析的sql内容
     * @return
     */
    public List<String> getSqlContent(){
        return erPlantUMLParseService.parseERPlantUML(this.erContentList);
    }
}
