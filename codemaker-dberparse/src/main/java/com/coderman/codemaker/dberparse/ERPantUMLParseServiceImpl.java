package com.coderman.codemaker.dberparse;

import com.alibaba.fastjson.JSON;
import com.coderman.codemaker.dberparse.bean.EntityBean;
import com.coderman.codemaker.dberparse.bean.EntityFieldBean;
import com.coderman.codemaker.dberparse.bean.ParseRequestBean;
import com.coderman.codemaker.dberparse.enums.ColumnTypeEnums;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * date: 2021/8/12
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public class ERPantUMLParseServiceImpl implements ERPantUMLParseService {
    @Override
    public List<String> parseERPlantUML(List<String> contentList) {
        List<EntityBean> list = getPlantUmlContextBean(contentList);
        System.out.println(JSON.toJSONString(list));
        List<String> ddlList = new ArrayList<>();
        list.stream().forEach(tableEntity-> ddlList.add(getDDLSQL(tableEntity)));
        return ddlList;
    }


    /**
     * 通过表字段模型构建DDL sql
     * @param tableBean
     * @return
     */
    private String getDDLSQL(EntityBean tableBean){
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("CREATE TABLE `" + tableBean.getTableName()+"`(");
        for (EntityFieldBean entityFieldBean : tableBean.getColumnBeanList()){
            sqlBuilder.append("`" + entityFieldBean.getColumnName() + "` ");
            sqlBuilder.append(entityFieldBean.getColumnType());
            if(entityFieldBean.getColumnName().equals("id")){
                sqlBuilder.append(" NOT NULL AUTO_INCREMENT ");
            }else {
                sqlBuilder.append(" NOT NULL DEFAULT  "+entityFieldBean.getDefaultValue());
            }
            sqlBuilder.append(" COMMENT '"+entityFieldBean.getColumnDesc()+"',");
        }

        if(!CollectionUtils.isEmpty(tableBean.getIndexBeanList())){
            for (EntityFieldBean indexFieldBean : tableBean.getIndexBeanList()){
                if(indexFieldBean.getPkey() != null && indexFieldBean.getPkey()){
                    sqlBuilder.append(" PRIMARY KEY (`"+indexFieldBean.getColumnName()+"`),");
                    continue;
                }

                if(indexFieldBean.getUKey() != null && indexFieldBean.getUKey()){
                    String uKeyName = getUkeyIndexName(indexFieldBean.getColumnName());
                    String uColumnNames = getUkeyColumnName(indexFieldBean.getColumnName());
                    sqlBuilder.append(" UNIQUE KEY `"+uKeyName+"` ("+uColumnNames+") COMMENT '联合唯一索引',");
                    continue;
                }

                sqlBuilder.append(" KEY `idx_"+indexFieldBean.getColumnName()+"` (`"+indexFieldBean.getColumnName()+"`),");
            }

        }


        sqlBuilder = sqlBuilder.deleteCharAt(sqlBuilder.length()-1);
        sqlBuilder.append(") ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT = '"+tableBean.getTableDesc()+"';\n");
        System.out.println(sqlBuilder.toString());
        return sqlBuilder.toString();
    }



    private String getUkeyIndexName(String columns){
        String [] arr = columns.split(",");
        StringBuilder builder = new StringBuilder("uniq");
        for (String col : arr){
            builder.append("_"+col.substring(0,1));
        }
        return builder.toString();
    }

    /**
     * 处理联合主键
     * @param columns
     * @return
     */
    private String getUkeyColumnName(String columns){
        String [] arr = columns.split(",");
        StringBuilder builder = new StringBuilder();
        for (String col : arr){
            builder.append("`"+col+"`,");
        }
        return  builder.deleteCharAt(builder.length()-1).toString();
    }


    /**
     * 解析plantUML文件内容
     * @return
     */
    private List<EntityBean> getPlantUmlContextBean(List<String> contentList){
        List<String> elementList = new ArrayList<>();
        List<EntityBean> entityBeanList = new ArrayList<>();
        //对entity进行解析
        for(String str : contentList){
            if(StringUtils.isEmpty(str) || str.contains("@startuml") || str.contains("package")){
                continue;
            }

            if(str.trim().contains("{")){
                elementList.add(str.trim());
                continue;
            }

            if(str.trim().contains("}")){
                elementList.add(str.trim());
                parseClassElement(elementList,entityBeanList);
                elementList.clear();
                continue;
            }
            if(str.trim().contains(":") || (str.trim().contains("key") && str.trim().contains("extend"))){
                elementList.add(str.trim());
            }
        }

        return entityBeanList;
    }


    /**
     * 解析文件内容整体路由
     * @param elementList
     * @param entityBeanList
     */
    private void parseClassElement(List<String> elementList, List<EntityBean> entityBeanList){
        EntityBean columnBean = buildClassBean(elementList);
        entityBeanList.add(columnBean);
    }



    /**
     * 解析class类型数据
     * @param elementList
     * @return
     */
    private EntityBean buildClassBean(List<String> elementList ){
        EntityBean entityBean = new EntityBean();

        String[] array = elementList.get(0).trim().replace("{","").trim().split(" ");

        String classMetaInfoArr = array[1];
        List<EntityFieldBean> columnBeanList = getColumnBeanList(elementList.subList(1,elementList.size()));
        List<EntityFieldBean> indexBeanList = getIndexBeanList(elementList.subList(1,elementList.size()));
        entityBean.setColumnBeanList(columnBeanList);
        entityBean.setIndexBeanList(indexBeanList);
        if(classMetaInfoArr.contains("-")){
            entityBean.setTableName(classMetaInfoArr.split("-")[0].replace("\"",""));
            entityBean.setTableDesc(classMetaInfoArr.split("-")[1].replace("\"",""));
        }else {
            entityBean.setTableName(classMetaInfoArr.replace("\"",""));
            entityBean.setTableDesc(array[2].replace("\"",""));
        }

        return entityBean;
    }


    /**
     * 获取表字段信息
     * @param elementList
     * @return
     */
    private List<EntityFieldBean> getColumnBeanList(List<String> elementList){
        List<EntityFieldBean> fieldBeanList = new ArrayList<>();

        for (String fieldStr : elementList){
            if(fieldStr.contains("key") && fieldStr.contains("extend")){
                break;
            }

            if(!fieldStr.trim().contains(":")){
                continue;
            }

            String[] fieldArr = fieldStr.trim().split(":");
            EntityFieldBean entityFieldBean = new EntityFieldBean();
            String [] tagArr = fieldArr[1].split("/");

            entityFieldBean.setColumnDesc(tagArr[0]);
            entityFieldBean.setColumnName(fieldArr[0]);
            if(tagArr.length == 2) {
                entityFieldBean.setColumnType(tagArr[1]);
            }
            if(tagArr.length == 3) {
                entityFieldBean.setDefaultValue(tagArr[1]);
                entityFieldBean.setColumnType(tagArr[2]);
            }
            if (StringUtils.isEmpty(entityFieldBean.getDefaultValue())){
                if(ColumnTypeEnums.isInt(entityFieldBean.getColumnType())){
                    entityFieldBean.setDefaultValue("0");
                }
                if(ColumnTypeEnums.isVarchar(entityFieldBean.getColumnType())){
                    entityFieldBean.setDefaultValue("''");
                }
                if(ColumnTypeEnums.isDate(entityFieldBean.getColumnType())){
                    entityFieldBean.setDefaultValue("'2000-01-01 00:00:00'");
                }
            }
            entityFieldBean.setNullable(false);
            fieldBeanList.add(entityFieldBean);
        }
        return fieldBeanList;
    }

    /**
     * 获取索引信息
     * @param elementList
     * @return
     */
    private List<EntityFieldBean> getIndexBeanList(List<String> elementList){
        List<EntityFieldBean> indexBeanList = new ArrayList<>();
        int index  = 0;
        for (int i = 0;i < elementList.size();i ++){

            if(elementList.get(i).contains("key") && elementList.get(i).contains("extend")){
                index = i+1;
                break;
            }
        }


        for (String fieldStr : elementList.subList(index,elementList.size())){
            EntityFieldBean indexFieldBean =new EntityFieldBean();
            if(!fieldStr.contains(":")){
                continue;
            }
            String [] indexArr = fieldStr.split(":");
            if(indexArr[0].toLowerCase().equals(Constants.PKEY)){
                indexFieldBean.setPkey(true);
                indexFieldBean.setColumnName(indexArr[1]);
                indexBeanList.add(indexFieldBean);
            }
            if(indexArr[0].toLowerCase().equals(Constants.UKEY)){
                indexFieldBean.setUKey(true);
                indexFieldBean.setColumnName(indexArr[1]);
                indexBeanList.add(indexFieldBean);
            }
            if(indexArr[0].toLowerCase().equals(Constants.KEY)){
                indexFieldBean.setColumnName(indexArr[1]);
                indexBeanList.add(indexFieldBean);
            }

        }
        return indexBeanList;
    }

}
