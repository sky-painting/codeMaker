package com.coderman.codemaker.app;

import com.coderman.codemaker.bean.WriteContentBean;

/**
 * Description:
 * date: 2021/6/18
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public interface IWriteFileService {

    /**
     * 抽象写模块class文件
     * @param writeContentBean
     */
    void writeContent(WriteContentBean writeContentBean);

}
