package com.tianhua.codemaker.service.adapter;

import java.util.Map;

/**
 * Description:类适配层
 * 解除对coderman-utils工具包的强依赖关系
 * date: 2021/10/26
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Deprecated
public interface IClazzAdapter {
    /**
     * 提供对facade,controller的方法内容返回包装类的相关信息
     * 如resultDataDto,ResultDto,PageVO,PageDTO等
     * @return
     */
    Map<String,String> getClazzWrapper();
}
