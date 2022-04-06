package com.coderman.codemaker.dbops.service;

import com.coderman.codemaker.dberparse.ERPlantUMLParseService;
import com.coderman.codemaker.dberparse.ERPlantUMLParseServiceImpl;
import com.coderman.codemaker.dberparse.bean.ParseRequestBean;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
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
@Service
@Component
public class DBErParseServiceImpl implements DBErParseService{
    private ERPlantUMLParseService erPantUMLParseService = new ERPlantUMLParseServiceImpl();
    @Value("${sqlfile.path}")
    private String path;
    @Override
    public List<String> parseERPlantUML(List<String> contentList,String fileName) throws IOException {
        List<String> sqlContent =  erPantUMLParseService.parseERPlantUML(contentList);
        File file = new File(path+"/"+fileName+".sql");
       /* if(!file.exists()){
            file.createNewFile();
        }*/
        FileUtils.writeLines(file,sqlContent);
        return sqlContent;
    }
}
