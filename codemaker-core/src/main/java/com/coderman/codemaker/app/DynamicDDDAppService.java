package com.coderman.codemaker.app;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Description:
 * date: 2021/6/29
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component(value = "dynamicDDDAppService")
public class DynamicDDDAppService implements AppService {
    @Resource(name = "dynamicDDDWriteServiceImpl")
    private IWriteFileService dynamicDDDWriteService;

    @Override
    public IWriteFileService getModelAppService(String moduleName) {
        return dynamicDDDWriteService;
    }
}
