server.port=8083
management.port=8073

# 跨域
endpoints.cors.allowed-origins=http://127.0.0.1:8083
endpoints.cors.allowed-methods=GET,POST


#数据库配置
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/coder_bank_product?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=root

#配置.xml文件路径
mybatis.config-locations=classpath:mybatis-config.xml
mybatis.mapper-locations=classpath:mapper/*.xml
#配置模型路径
mybatis.type-aliases-package=com.coderman.codemaker.bean


