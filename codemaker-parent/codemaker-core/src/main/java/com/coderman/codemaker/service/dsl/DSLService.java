package com.coderman.codemaker.service.dsl;

import com.coderman.codemaker.config.AppServiceConfig;
import com.coderman.codemaker.enums.dynamic.ReadWriteTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description:扩展出的DSL 统一语言
 * 归类读写场景
 * date: 2021/11/24
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
public class DSLService {
    @Autowired
    private AppServiceConfig appServiceConfig;

    /**
     * 注册自定义的读写操作统一语言
     */
    public void registCustomDsl(){
        ReadWriteTypeEnum.putReadDSL(appServiceConfig.getReadDslSet());
        ReadWriteTypeEnum.putWriteDSL(appServiceConfig.getWriteDslSet());
    }
}
