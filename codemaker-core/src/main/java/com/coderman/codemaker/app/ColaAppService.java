package com.coderman.codemaker.app;

import com.coderman.codemaker.service.IWriteFileService;
import org.springframework.stereotype.Component;

/**
 * Description:
 * date: 2021/6/22
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component(value = "colaAppService")
public class ColaAppService implements AppService{
    @Override
    public IWriteFileService getModelAppService(String moduleName) {
        return null;
    }
}
