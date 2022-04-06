server.port=8099

#数据库配置
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/infosys_auth?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC
spring.datasource.username=
spring.datasource.password=

#配置.xml文件路径
mybatis.config-locations=classpath:mybatis-config.xml
mybatis.mapper-locations=classpath:mapper/*.xml
#配置模型路径
mybatis.type-aliases-package=com.tianhua.codemaker.bean
spring.application.name=codemaker

