package com.coderman.codemaker.dbops.controller;

import com.coderman.codemaker.dbops.bean.AppSqlSchemaBean;
import com.coderman.codemaker.dbops.bean.ColumnOpsBean;
import com.coderman.codemaker.dbops.service.AppSqlSchemaService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * description: DBOpsController <br>
 * date: 2020/8/9 12:21 <br>
 * author: coderman <br>
 * version: 1.0 <br>
 */
@RestController
public class DBOpsController {
    @Autowired
    private AppSqlSchemaService appSqlSchemaService;

    protected Logger logger = LoggerFactory.getLogger(DBOpsController.class);


    /**
     * 生成运维分库分表变更语句
     * 对应resources sql文件夹下目录名
     * @param appName 应用名称
     * @param dbName 数据库名称
     * @param opsName 操作
     * @return
     */
    @GetMapping(value = "/dbops")
    public String generatorDB(@RequestParam(name = "appName") String appName,
                              @RequestParam(name = "dbName") String dbName,
                              @RequestParam(name = "opsName") String opsName) {
        try {
            String path = ResourceUtils.getURL("classpath:").getPath() + "sql";
            path = path + "/" + appName + "/" + dbName + "/ops/" + opsName + ".txt";
            logger.info("path = {0}",path);
            File file = new File(path);
            if (!file.exists()) {
                return "file is not exist ,please check it !";
            }

            List<String> dataList = FileUtils.readLines(file, "UTF-8");
            List<ColumnOpsBean> columnOpsBeanList = new ArrayList<>();

            for (String updateSql : dataList) {
                if(updateSql.length() < 5){
                    continue;
                }
                ColumnOpsBean columnOpsBean = new ColumnOpsBean();
                String [] array = updateSql.split(":");
                columnOpsBean.setOpsColumn(array[2]);
                columnOpsBean.setOpsName(array[0]);
                columnOpsBean.setTableName(array[1]);
                columnOpsBeanList.add(columnOpsBean);
            }
            AppSqlSchemaBean appSqlSchemaBean = appSqlSchemaService.getByAppName(appName);
            int dbSharding = appSqlSchemaBean.getSqlSchemaBeanMap().get(dbName).getDbSharding();
            Map<String, Integer> tableShardingMap = appSqlSchemaBean.getSqlSchemaBeanMap().get(dbName).getTableShardingMap();
            List<String> resultSqlList = new ArrayList<>();
            for (int i = 0;i < dbSharding;i++){
                for (ColumnOpsBean columnOpsBean : columnOpsBeanList){
                    Integer tableSharding = tableShardingMap.get(columnOpsBean.getTableName());
                    for (int x = 0;x < tableSharding;x++) {
                        String sql = columnOpsBean.getOpsName()+" " + dbName + "_"+i+"."+columnOpsBean.getTableName()+"_"+x+" "+columnOpsBean.getOpsColumn();
                        resultSqlList.add(sql);
                    }
                }
            }

            resultSqlList.stream().forEach(o->{
                System.out.println(o);
            });

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }
}
