package com.coderman.codemaker.dbops.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * description: GeneratorDBController <br>
 * date: 2020/7/10 12:44 <br>
 * author: coderman <br>
 * version: 1.0 <br>
 */
@RestController
public class GeneratorDBController {

    /**
     * 初始化应用表结构
     *
     * @param appName  应用名称
     * @param dbName   数据库名称
     * @return
     */
    @GetMapping(value = "/initshardingdb")
    public String generatorDB(@RequestParam(name = "appName") String appName,
                              @RequestParam(name = "dbName") String dbName){
        try {
            String path = ResourceUtils.getURL("classpath:").getPath()+"sql";
            File file = new File(path);
            File[] appFileArr = file.listFiles();
            File appFile = null;
            for (File targetFile : appFileArr){
                if(targetFile.getName().equals(appName)){
                    appFile = targetFile;
                }
            }
            if(appFile == null){
                return "appName:"+appName+" not find for init dir.";
            }
            File [] dbFileArr = appFile.listFiles();
            File dbFile =  null;
            for (File targetFile : dbFileArr){
                if(targetFile.getName().equals(dbName)){
                    dbFile = targetFile;
                }
            }
            if(dbFile == null){
                return "dbName:"+dbName+" not find for init dir.";
            }


            String shardingDbFilePath = dbFile.getAbsolutePath()+"\\"+"dbsharding.txt";
            System.out.println("shardingFilePath = "+shardingDbFilePath);
            List<String> dataList = FileUtils.readLines(new File(shardingDbFilePath),"UTF-8");
            int shardingNum;
            if(dataList == null || dataList.size() == 0){
                shardingNum = 1;
            }else {
                shardingNum = Integer.parseInt(dataList.get(0));
            }
            if(shardingNum < 0){
                return "dbsharding number is error.";
            }

            Map<String,List<String>>  createDBSqlMap = getCreateDBSql(dbName,shardingNum);

            String shardingTableFilePath = dbFile.getAbsolutePath()+"\\"+"tablesharding.txt";
            List<String> tableShardingList = FileUtils.readLines(new File(shardingTableFilePath),"UTF-8");
            for (String tableSharding : tableShardingList){
                String [] shardingArr = tableSharding.split(":");
                String tableName = shardingArr[0];
                int tableNum = Integer.parseInt(shardingArr[1]);
                //平均每个库有多少张表，tableNum总共有多少张表
                int everyDBTableNum = tableNum / shardingNum;
                File finalDbFile = dbFile;
                createDBSqlMap.forEach((k, v)->{
                    for (int i = 0 ;i < everyDBTableNum;i++){
                        String realTableName = tableName+"_"+i;
                        //处理单库，单表的情况
                        if(everyDBTableNum == 1){
                            realTableName = tableName;
                        }
                        StringBuilder builder = new StringBuilder();
                        builder.append("DROP TABLE IF EXISTS `"+k+"`.`"+realTableName+"`;\n");
                        builder.append("CREATE TABLE `"+k+"`.`"+realTableName+"`");
                        String columnFilePath = finalDbFile.getAbsolutePath()+"\\"+tableName+".txt";
                        try {
                            List<String> list = FileUtils.readLines(new File(columnFilePath),"UTF-8");
                            v.add(builder.toString());
                            v.addAll(list);
                            v.add("\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
            String appSqlPath = dbFile.getAbsolutePath()+"\\"+appName+".sql";
            System.out.println("appSqlPath = ="+appSqlPath);

            List<String> resultList = new ArrayList<>();
            createDBSqlMap.forEach((k, v)->{
                resultList.addAll(v);
            });
            String sqlPath = Thread.currentThread().getContextClassLoader().getResource("").getPath()
                    +"sql\\"+appName+"\\"+dbName+"\\"+dbName+".sql";
            FileUtils.writeLines(new File(sqlPath),resultList);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }

    /**
     * 获取创建数据库的语句
     * @param dbName
     * @param shardingNum
     * @return
     */
    private Map<String,List<String>> getCreateDBSql(String dbName, int shardingNum){
        //drop database if exists `test-pro`;
        //create database `test-pro` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
        TreeMap<String,List<String>> listMap = new TreeMap<>();
        String x = shardingNum+"";
        for (int i = 0; i < shardingNum;i++){
            List<String>  dbNameList = new ArrayList<>();

            String realDbName = dbName+"_"+i;
            if(shardingNum == 1){
                realDbName = dbName;
            }
            StringBuilder builder = new StringBuilder("drop database if exists `"+realDbName+"`;\n");
            builder.append("create database `"+realDbName+"` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;\n");
            dbNameList.add(builder.toString());
            listMap.put(realDbName,dbNameList);
        }
        return listMap;
    }
}
