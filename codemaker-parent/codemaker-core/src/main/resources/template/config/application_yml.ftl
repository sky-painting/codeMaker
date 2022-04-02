server:
  port: 8080

spring:
  application:
    name: ${projectBean.applicationName}
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/${projectBean.dataBaseName}?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC
    username:
    password:

#mybatis
mybatis:
  config-locations: classpath:mybatis-config.xml
  mapper-locations: classpath:mapper/*.xml
  type-aliases-package: ${packageInfrast}.dao.dataobject

<#list configFileList as config>
${config}
</#list>


