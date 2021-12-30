package com.coderman.codemaker.service.invoker;

import com.coderman.codemaker.bean.invoke.InvokeContextBean;
import com.coderman.codemaker.bean.invoke.InvokeRowBean;
import com.coderman.codemaker.bean.plantuml.MethodBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:根据方法调用内容动态智能构建方法返回值
 * date: 2021/11/1
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
public class ReturnBodyFactoryService {


    /**
     * 更新调用方的方法返回值
     * @param invokeBean
     */
    public void refreshReturnBody(InvokeContextBean invokeBean){
        List<InvokeRowBean> invokeRowBeanList = invokeBean.getInvokerMethodBean().getInvokeRowBeanList();
        String returnClassType = invokeBean.getInvokerMethodBean().getReturnClassTypeNoWrapper();

        MethodBean invokerMethodBean = invokeBean.getInvokerMethodBean();
        String returnClassValue = "";
        for (InvokeRowBean invokeRowBean : invokeRowBeanList) {
            String invokeReturnClassName = invokeRowBean.getReturnClassName();
            if (returnClassType.equals(invokeReturnClassName)) {
                returnClassValue = invokeRowBean.getReturnClassValue();
                break;
            }
        }
        if(StringUtils.isEmpty(returnClassValue)){
            return;
        }

        if(!invokeBean.getInvokerMethodBean().wrapperResultDataDto()){
            returnClassValue = "return "+ returnClassValue+";";
        }

        else if(invokeBean.getInvokerMethodBean().wrapperResultDto()){
            String returnClassName = invokeBean.getInvokerMethodBean().getReturnClass().split("<")[0];
            returnClassValue = "return " + returnClassName + ".success("+returnClassValue+");";
        }else {
            returnClassValue = "return "+returnClassValue+";";
        }

        if(StringUtils.isEmpty(returnClassValue)){
            return;
        }
        String finalReturnClassValue = returnClassValue;
        invokeBean.getInvokerClassBean().getMethodBeanList()
                .stream()
                .forEach(methodBean -> {
                    if(methodBean.getReturnClass().equals(invokerMethodBean.getReturnClass()) && methodBean.getMethodName().equals(invokerMethodBean.getMethodName())){
                        methodBean.setReturnBody(finalReturnClassValue);
                    }
                });
    }

}
