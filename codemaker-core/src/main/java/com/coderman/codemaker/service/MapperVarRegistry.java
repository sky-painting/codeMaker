package com.coderman.codemaker.service;

import com.coderman.codemaker.bean.ColumnBean;
import com.coderman.codemaker.bean.TableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description: MapperVarRegistry <br>
 * date: 2020/7/7 10:05 <br>
 * author: coderman <br>
 * version: 1.0 <br>
 */
@Component(value = "mapperVarRegistry")
public class MapperVarRegistry extends AbstractVarRegistry {
    @Autowired
    private TemlateVarService temlateVarService;

    @Override
    public Map<String, Object> getRegistVarMap() {
        Map<String, TableBean> tableBeanMap = temlateVarService.getTableBeanMap();
        Map<String, List<ColumnBean>> columnBeanListMap = temlateVarService.getColumnBeanMap();
        Map<String, Object> map = new HashMap<>();
        map.put("table",tableBeanMap);
        map.put("columns",columnBeanListMap);
        return map;
    }
}
