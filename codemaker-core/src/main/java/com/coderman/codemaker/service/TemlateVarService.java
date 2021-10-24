package com.coderman.codemaker.service;

import com.coderman.codemaker.bean.ColumnBean;
import com.coderman.codemaker.bean.TableBean;
import com.coderman.codemaker.config.AppServiceConfig;
import com.coderman.codemaker.dao.SqlMapper;
import com.coderman.codemaker.utils.StringHelperUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
     * 获取加工后的tableBean元数据
     * @return
     */
    public Map<String, TableBean> exeGetTableBeanMap(){
        String dbName = appServiceConfig.getDbName();
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

}
