package com.coderman.codemaker.dbops.controller;

import com.coderman.codemaker.dberparse.bean.ParseRequestBean;
import com.coderman.codemaker.dbops.service.DBErParseService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Description:
 * date: 2021/8/24
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@RestController
public class GeneratorSqlDDLController {
    @Autowired
    private DBErParseService dbErParseService;

    /**
     * 从uml类图文件中生成DDL语句
     * @param fileName
     * @return
     */
    @GetMapping(value = "/getsqlddl")
    public String getSqlDDL(@RequestParam(name = "fileName") String fileName){
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:er-plantuml/"+fileName+".puml");
            dbErParseService.parseERPlantUML(FileUtils.readLines(file,"UTF-8"),fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success:"+"fileName.sql 文件在er-sql文件夹下";

    }
}
