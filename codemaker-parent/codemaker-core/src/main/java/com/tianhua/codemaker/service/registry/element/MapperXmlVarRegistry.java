package com.tianhua.codemaker.service.registry.element;

import com.tianhua.codemaker.bean.ColumnBean;
import com.tianhua.codemaker.bean.TableBean;
import com.tianhua.codemaker.service.registry.AbstractVarRegistry;
import com.tianhua.codemaker.service.template.TemlateVarService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * description: MapperXmlVarRegistry <br>
 * date: 2020/7/7 10:07 <br>
 * author: coderman <br>
 * version: 1.0 <br>
 */
@Component(value = "mapperXmlVarRegistry")
public class MapperXmlVarRegistry extends AbstractVarRegistry {
    @Autowired
    private TemlateVarService temlateVarService;
    @Override
    public Map<String, Object> getRegistVarMap() {
        Map<String, TableBean> tableBeanMap = temlateVarService.getTableBeanMap();


        Map<String, List<ColumnBean>> columnBeanListMap = temlateVarService.getColumnBeanMap();

        for (Map.Entry<String,TableBean> entry : tableBeanMap.entrySet()){
            List<String> columnNameList = columnBeanListMap.get(entry.getKey()).stream().map(ColumnBean::getColumnName).collect(Collectors.toList());
            List<String> columnFieldList = columnBeanListMap.get(entry.getKey()).stream().map(ColumnBean::getColumnFieldName).collect(Collectors.toList());

            entry.getValue().setColumnNameList(StringUtils.join(columnNameList,","));
            entry.getValue().setInsertColumnNameList(getInsertColumnNameList(columnFieldList).replace("#{id},",""));
            entry.getValue().setUpdateColumnNameList(getUpdateColumnNameList(columnNameList,columnFieldList).replace("id=#{id},",""));
            entry.getValue().setInsertColumnNames(getInsertColumnNamesStr(columnNameList));

        }
        List<String> sqlContentList = temlateVarService.getSqlContent();

        Map<String, Object> map = new HashMap<>();
        map.put("table",tableBeanMap);
        map.put("columns",columnBeanListMap);
        map.put("sql",sqlContentList);
        return map;
    }

    /**
     * 插入sql value生成
     * @param columnNameList
     * @return
     */
    private String getInsertColumnNameList(List<String> columnNameList){

        return "#{"+StringUtils.join(columnNameList,"},#{")+"}";
    }

    /**
     * 插入sql value生成
     * @param columnNameList
     * @return
     */
    private String getUpdateColumnNameList(List<String> columnNameList,List<String> columnFieldList){
        List<String> list = new ArrayList<>();
        for (int i = 0;i < columnNameList.size();i ++){
            String columnStr = columnNameList.get(i)+"=#{"+columnFieldList.get(i)+"}";
            list.add(columnStr);
        }
        return StringUtils.join(list,",");
    }

    private String getInsertColumnNamesStr(List<String> columnNameList){
        List<String> tmpList = new ArrayList<>();
        for (String columnName : columnNameList){
            if(columnName.equals("id")){
                continue;
            }
            tmpList.add(columnName);
        }
        return StringUtils.join(tmpList,",");
    }

    private String getInsertFieldStr(List<String> columnFieldList){
        List<String> tmpList = new ArrayList<>();
        for (String columnField : columnFieldList){
            if(columnField.equals("id")){
                continue;
            }
            tmpList.add(columnField);
        }
        return StringUtils.join(tmpList,",");
    }

}
