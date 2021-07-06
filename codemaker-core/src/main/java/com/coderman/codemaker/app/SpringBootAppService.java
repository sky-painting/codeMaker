package com.coderman.codemaker.app;

import com.coderman.codemaker.service.IWriteFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Description:
 * date: 2021/6/22
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component(value = "springBootAppService")
public class SpringBootAppService implements AppService{
    @Resource(name = "springBootWriteService")
    private IWriteFileService iWriteFileService;

    @Override
    public IWriteFileService getModelAppService(String moduleName) {
        return iWriteFileService;
    }
}
