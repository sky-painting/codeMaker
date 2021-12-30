package com.coderman.codemaker.service.adapter;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:codeMaker的默认实现
 * date: 2021/10/26
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 * 该组件已过期，整体逻辑已通过组件化升级完成
 */
@Service(value = "defaultClazzWrapperImpl")
@Deprecated
public class DefaultClazzWrapperImpl implements IClazzAdapter{

    @Override
    public Map<String, String> getClazzWrapper() {
        return getMethodReturnClass();
    }

    private Map<String,String> getMethodReturnClass(){
        Map<String,String> methodReturnClassMap = new HashMap<>();
        //methodReturnClassMap.put("ResultDto","com.coderman.utils.response.ResultDto");
        //methodReturnClassMap.put("ResultDataDto","com.coderman.utils.response.ResultDataDto");
        //methodReturnClassMap.put("PageDTO","com.coderman.utils.response.PageDTO");
        //methodReturnClassMap.put("PageVO","com.coderman.utils.response.PageVO");
        return methodReturnClassMap;
    }

}
