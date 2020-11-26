package com.coderman.codemaker.dbergenerate.service;

import com.coderman.codemaker.dbergenerate.bean.ColumnBean;
import com.coderman.codemaker.dbergenerate.bean.TableBean;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * Description: 辅助类
 * date: 2020/10/20
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public class ErPictureServiceHelper {

    /**
     * 根据单个table的元数据转换e-r字符串
     * @param tableBean
     * @return
     */
    public String getEntityPlantUML(TableBean tableBean){
        if(tableBean == null || tableBean.getColumnBeanList() == null){
            return null;
        }

        StringBuilder builder = new StringBuilder();
        String tableInfo = tableBean.getTableName()+ " " + tableBean.getTableComment();
        builder.append("entity \" "+tableInfo+"\" as "+tableBean.getTableName()+" {\n");
        for (ColumnBean columnBean : tableBean.getColumnBeanList()){
            String columnInfo = columnBean.getColumnComment()+"/"+columnBean.getColumnType();
            builder.append("    "+columnBean.getColumnName()+": "+columnInfo+"\n");
        }
        builder.append("}\n");
        return builder.toString();
    }

    /**
     * 根据表间关系构建e-r 图
     * @param tableBeanList
     * @return
     */
    public    String getTableRelation(List<TableBean> tableBeanList){
        Map<String,List<String>> relationMap = getTableRelationMap(tableBeanList);
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String,List<String>> entry : relationMap.entrySet()){
            entry.getValue().forEach(subTable->{
                String relation = entry.getKey() + " ||--o{ " +subTable+"\n";
                builder.append(relation);
            });

        }
        return builder.toString();
    }

    /**
     * 获取表间关系，主要指主子表
     * @param tableBeanList
     * @return
     */
    public   Map<String,List<String>> getTableRelationMap(List<TableBean> tableBeanList){
        Map<String,Map<String,String>> columnMap = new HashMap<>();
        for (TableBean tableBean : tableBeanList){
            Map<String,String> map = tableBean.getColumnBeanList().stream().collect(Collectors.toMap(ColumnBean::getColumnName,ColumnBean::getColumnComment));
            columnMap.put(tableBean.getTableName(),map);
        }

        Map<String,List<String>> relationMap = new HashMap<>();
        for (TableBean tableBean : tableBeanList){
            String subTableId = tableBean.getTableName()+"_id";
            AtomicReference<String> subTable = new AtomicReference<>("");
            columnMap.forEach((k,v)->{
                if(v.get(subTableId) != null){
                    subTable.set(k);
                }
            });
            String subTableName = subTable.get();
            if(StringUtils.isNotEmpty(subTableName)){
                List<String> subTableList = relationMap.get(tableBean.getTableName());
                if(CollectionUtils.isEmpty(subTableList)){
                    subTableList = new ArrayList<>();
                }
                subTableList.add(subTableName);
                relationMap.put(tableBean.getTableName(),subTableList);
            }
        }
        return relationMap;
    }

}
