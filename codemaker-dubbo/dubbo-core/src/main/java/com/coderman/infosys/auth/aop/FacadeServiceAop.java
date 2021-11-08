package com.coderman.infosys.auth.aop;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.reflection.ExceptionUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
*
*/
@Component
@Aspect
@Slf4j
public class FacadeServiceAop {
    @Around(value = "execution( public * com.coderman.infosys.auth.app.facadeimpl..*(..))")
    public Object transferException(ProceedingJoinPoint joinPoint) throws Exception {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        try {
            log.info("=", method.getDeclaringClass().getName(), method.getName(), JSON.toJSONString(joinPoint.getArgs()));
            Object result = joinPoint.proceed();
            log.info("=", method.getDeclaringClass().getName(), method.getName(), JSON.toJSONString(result));
            return result;
        } catch (Throwable exception) {
            throw new Exception();
        }
    }
}
