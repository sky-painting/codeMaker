package com.coderman.codemaker.service.registry;

import com.coderman.codemaker.bean.ColumnBean;
import com.coderman.codemaker.bean.TableBean;
import com.coderman.codemaker.service.AbstractVarRegistry;
import com.coderman.codemaker.service.TemlateVarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description: ControllerRegistry <br>
 * date: 2020/7/7 10:04 <br>
 * author: coderman <br>
 * version: 1.0 <br>
 */
@Component(value = "controllerVarRegistry")
public class ControllerVarRegistry extends AbstractVarRegistry {
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