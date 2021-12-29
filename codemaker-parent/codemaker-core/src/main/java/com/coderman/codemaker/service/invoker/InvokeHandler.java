package com.coderman.codemaker.service.invoker;

import com.coderman.codemaker.bean.invoke.InvokeContextBean;

/**
 * Description: 动态方法调用绘制处理器入口
 * 主要处理convert和上下文等之间的代码生成关系分析等
 * date: 2021/10/21
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public interface InvokeHandler {


    /**
     * 调用方法绘制对于读写场景的方法处理整体入口
     * @param invokeBean
     */
    void dealInvoke(InvokeContextBean invokeBean);


}
