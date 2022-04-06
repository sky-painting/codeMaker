package com.coderman.codemaker.dbops.service;

import com.coderman.codemaker.dberparse.bean.ParseRequestBean;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Description:
 * date: 2021/8/24
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public interface DBErParseService {
    /**
     * e-r图解析包装服务类
     * @param contentList
     * @param fileName
     * @return
     */
    List<String> parseERPlantUML(List<String> contentList,String fileName) throws IOException;
}
