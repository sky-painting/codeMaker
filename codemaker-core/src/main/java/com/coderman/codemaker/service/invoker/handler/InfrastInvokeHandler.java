package com.coderman.codemaker.service.invoker.handler;

import com.coderman.codemaker.bean.invoke.InvokeContextBean;
import com.coderman.codemaker.service.invoker.InvokeHandler;
import org.springframework.stereotype.Service;

/**
 * Description:基础设施层infrast调用方 方法绘制处理器
 * date: 2021/10/21
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service(value = "infrastInvokeHandler")
public class InfrastInvokeHandler implements InvokeHandler {
    @Override
    public void dealInvoke(InvokeContextBean invokeBean) {
        invokeBean.getInvokerMethodBean().addInvokeRowContent(invokeBean.getCurrentInvokeRowContent(),invokeBean.getCurrentInvokeRowBean());
    }
}
