package com.coderman.codemaker.dberparse;

import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Description:
 * date: 2021/8/12
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public class Main {
    public static void main(String[] args) {
        ERPlantUMLParseService erPantUMLParseService = new ERPlantUMLParseServiceImpl();

        erPantUMLParseService.parseERPlantUML(readPlantUMLContent());
    }

    /**
     * 读取plantUMl文件
     * @return
     */
    private static List<String> readPlantUMLContent(){
        try {
            File file = ResourceUtils.getFile("classpath:er-plantuml/"+"recruit-db.puml");
            return FileUtils.readLines(file,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
