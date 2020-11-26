package com.coderman.codemaker.dbops;

import com.coderman.codemaker.dbops.service.AppSqlSchemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class CodemakerDbopsApplication implements CommandLineRunner {
    @Autowired
    private AppSqlSchemaService appSqlSchemaService;

    public static void main(String[] args) {
        SpringApplication.run(CodemakerDbopsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        appSqlSchemaService.initSqlSchema();
    }
}
