package com.coderman.codemaker.api;

import com.coderman.codemaker.bean.component.ComponentContextBean;

import java.util.List;
import java.util.Map;

/**
 * Description:组件扫描注册服务类
 *
 * date: 2021/11/22
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@FunctionalInterface
public interface ICompScanService {
    /**
     * 组件扫描核心接口
     * @return
     */
    Map<String,ComponentContextBean> scanComponent(List<String> componentList);
}
