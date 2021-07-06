package com.coderman.codemaker.app;

import com.coderman.codemaker.service.IWriteFileService;

/**
 * Description:
 * date: 2021/6/22
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public interface AppService {
    /**
     * 根据模块名获取 模块文件写服务
     * @param moduleName
     * @return
     */
    IWriteFileService getModelAppService(String moduleName);

}
