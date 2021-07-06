package com.coderman.codemaker.service.registry;

import com.coderman.codemaker.bean.ColumnBean;
import com.coderman.codemaker.bean.TableBean;
import com.coderman.codemaker.service.AbstractVarRegistry;
import com.coderman.codemaker.service.TemlateVarService;
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
            entry.getValue().setColumnNameList(StringUtils.join(columnNameList,","));
            entry.getValue().setInsertColumnNameList(getInsertColumnNameList(columnNameList).replace("#{id},",""));
            entry.getValue().setUpdateColumnNameList(getUpdateColumnNameList(columnNameList).replace("id=#{id},",""));
            entry.getValue().setInsertColumnNames(getInsertColumnNamesStr(columnNameList));
        }
        Map<String, Object> map = new HashMap<>();
        map.put("table",tableBeanMap);
        map.put("columns",columnBeanListMap);


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
    private String getUpdateColumnNameList(List<String> columnNameList){
        List<String> list = new ArrayList<>();
        for (String columnName : columnNameList){
            String columnStr = columnName+"=#{"+columnName+"}";
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

}
