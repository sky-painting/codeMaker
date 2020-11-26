package com.coderman.codemaker.dbops.service;

import com.alibaba.fastjson.JSON;
import com.coderman.codemaker.dbops.bean.AppSqlSchemaBean;
import com.coderman.codemaker.dbops.bean.SqlSchemaBean;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description: AppSqlSchemaService <br>
 * date: 2020/8/9 12:35 <br>
 * author: coderman <br>
 * version: 1.0 <br>
 * 项目启动时通过该服务加载分库分表配置数据
 */
@Service
public class AppSqlSchemaService {
    /**
     * resources/sql下的所有应用的分库分表配置
     */
    private Map<String, AppSqlSchemaBean> appSqlSchemaBeanMap  = new HashMap<>();

    public String initSqlSchema() throws IOException {
        String path = ResourceUtils.getURL("classpath:").getPath()+"sql";
        File file = new File(path);
        File[] appFileArr = file.listFiles();
        for (File targetFile : appFileArr){

            AppSqlSchemaBean appSqlSchemaBean = new AppSqlSchemaBean();
            appSqlSchemaBean.setAppName(targetFile.getName());
            Map<String,SqlSchemaBean> sqlSchemaBeanMap = new HashMap<>();

            File [] appChildFileArr = targetFile.listFiles();
            if(appChildFileArr == null || appChildFileArr.length == 0) {
                continue;
            }
            for (File appChild : appChildFileArr){
                if(!appChild.isDirectory()){
                    continue;
                }


                SqlSchemaBean sqlSchemaBean = new SqlSchemaBean();
                sqlSchemaBean.setSchemaName(appChild.getName());
                String dbShardingFilePath = appChild.getAbsolutePath()+"/dbsharding.txt";
                String tableShardingFilePath = appChild.getAbsolutePath()+"/tablesharding.txt";
                List<String> dataList = FileUtils.readLines(new File(dbShardingFilePath),"UTF-8");
                if(dataList == null || dataList.size() == 0){
                    sqlSchemaBean.setDbSharding(0);
                }else {
                    Integer dbShardingNum = Integer.parseInt(dataList.get(0));
                    sqlSchemaBean.setDbSharding(dbShardingNum);
                }
                Map<String,Integer> tableShardingMap = new HashMap<>();
                List<String> tableShardingList = FileUtils.readLines(new File(tableShardingFilePath),"UTF-8");
                if(tableShardingList == null || tableShardingList.size() == 0){
                    sqlSchemaBean.setTableShardingMap(tableShardingMap);
                }else {
                    for (String tableSharding : tableShardingList){
                        String [] arr = tableSharding.split(":");
                        tableShardingMap.put(arr[0],Integer.parseInt(arr[1]));
                    }
                    sqlSchemaBean.setTableShardingMap(tableShardingMap);
                }


                Map<String,List<String>> tableSchemaMap = new HashMap<>();
                File [] sqlSchemaFileArr = appChild.listFiles();
                for(File sqlSchemaFile : sqlSchemaFileArr){
                    if(sqlSchemaFile.getName().contains("dbsharding") ||sqlSchemaFile.getName().contains("tablesharding")){
                        continue;
                    }
                    if(sqlSchemaFile.isDirectory()){
                        continue;
                    }
                    List<String> tableSchemaList = FileUtils.readLines(sqlSchemaFile,"UTF-8");
                    tableSchemaMap.put(sqlSchemaFile.getName(),tableSchemaList);
                }
                sqlSchemaBean.setTableSchemaMap(tableSchemaMap);
                sqlSchemaBeanMap.put(appChild.getName(),sqlSchemaBean);
                appSqlSchemaBean.setSqlSchemaBeanMap(sqlSchemaBeanMap);
            }
            appSqlSchemaBeanMap.put(targetFile.getName(),appSqlSchemaBean);
        }
        System.out.println(JSON.toJSONString(appSqlSchemaBeanMap));
        return "success";
    }

    /**
     * 获取APP下的分库分表配置
     * @param appName
     * @return
     */
    public AppSqlSchemaBean getByAppName(String appName){
        return appSqlSchemaBeanMap.get(appName);
    }


}
