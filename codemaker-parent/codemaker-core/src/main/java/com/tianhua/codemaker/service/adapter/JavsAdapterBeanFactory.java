package com.tianhua.codemaker.service.adapter;

import com.tianhua.codemaker.api.IJavsAdapterSerivce;
import com.tianhua.codemaker.utils.SpringContextHolder;
import org.springframework.stereotype.Service;

/**
 * Description:
 * date: 2022/4/1
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
public class JavsAdapterBeanFactory {
    /**
     * 获取javs适配实现
     * @return
     */
    public IJavsAdapterSerivce getJavsAdapterService(){
        return (IJavsAdapterSerivce)SpringContextHolder.getBean("javsAdapterService");
    }


}
