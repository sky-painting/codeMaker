package com.tianhua.codemaker.service.registry.element;

import com.tianhua.codemaker.bean.ColumnBean;
import com.tianhua.codemaker.bean.TableBean;
import com.tianhua.codemaker.service.registry.AbstractVarRegistry;
import com.tianhua.codemaker.service.template.TemlateVarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description: ServiceImplVarRegistry <br>
 * date: 2020/7/7 10:08 <br>
 * author: coderman <br>
 * version: 1.0 <br>
 */
@Component(value = "serviceImplVarRegistry")
public class ServiceImplVarRegistry extends AbstractVarRegistry {
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
