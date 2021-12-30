# 天画-codeMaker 应用配置说明

##  application.properties
codeMaker代码生成服务的核心配置
```
# FREEMARKER (FreeMarkerAutoConfiguration)
# 太多了,不是重点,忽略
server.port=8099
management.port=8073

# 跨域(需要集成到公司服务的可以配置)
endpoints.cors.allowed-origins=http://127.0.0.1:8083
endpoints.cors.allowed-methods=GET,POST

#-------------以下是生成代码相关的业务配置----------
#数据库配置
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/school_manager?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC
#生成的项目需要链接项目的mysql数据库
spring.datasource.username=
spring.datasource.password=

#配置.xml文件路径
mybatis.config-locations=classpath:mybatis-config.xml
mybatis.mapper-locations=classpath:mapper/*.xml
#配置模型路径
mybatis.type-aliases-package=com.coderman.codemaker.bean
spring.application.name=codemaker
#生成的应用类型，支持springboot,dubbo,cola,springcloud
application.type=dubbo


#目标应用根包名称
application.global.package=com.coderman.infosys.auth
#作者
application.global.author=shenshuai

#接口方法返回声明需要依赖的包信息，适配实现
application.global.package.adapterbean=defaultClazzWrapperImpl

#组件化需要的maven repository本地路径,用来扫描依赖的组件jar包
application.maven.repo.path=jar:file://C:\\.m2\\repository

#代码生成需要的全局组件，框架中间件可以放到全局组件依赖配置里,类似于脚手架,或者自己封装的业务组件框架
application.component.scan.config=dubbo,spring-web,openfeign

#自定义的组件扫描bean,defaultCompScanService为codeMaker默认实现支持全局组件的配置，开发者可以参考进行自定义扫描组件实现替代掉默认的
application.component.scan.bean=defaultCompScanService

#自定义的组件装饰bean,defaultCompDecorateService默认实现支持全局组件的装饰，开发者可以参考进行自定义扫描组件实现替代掉默认的
application.component.decorate.bean=defaultCompDecorateService

```


##  projecttemplate-dubbo.properties
生成dubbo应用代码的配置
```
#数据库名称
dubbo.global.dbName=school_manager
#目标工程输出目录，这里填写对应的工程的绝对路径
dubbo.code.outpath.dubbo-common=E:\\workspace\\tianhua-workspace\\code-maker\\codemaker-dubbo\\dubbo-common
dubbo.code.outpath.dubbo-api=E:\\workspace\\tianhua-workspace\\code-maker\\codemaker-dubbo\\dubbo-api
dubbo.code.outpath.dubbo-core=E:\\workspace\\tianhua-workspace\\code-maker\\codemaker-dubbo\\dubbo-core
#应用服务的plantUML类图文件,不配置则走基于数据表的方式生成代码
codemaker.domain.plantuml=


#是否构建api 文档，否则进行构建,默认构建
dubbo.api.generator=true

#是否需要根据该参数设置请求参数的最后一级包名为request,默认false
dubbo.subpackage.request=false

#是否需要根据该参数设置相应参数的最后一级包名为response,默认false
dubbo.subpackage.response=false


#需要导入的组件列表，多个逗号分割
dubbo.component.scan.config=apiresult,infosysuser,hutool-core

#需要在领域文档和调用时序文档中识别的读操作统一语言
dubbo.component.dsl.read=check

#需要在领域文档和调用时序文档中识别的写操作统一语言
dubbo.component.dsl.write=settle


#应用级组件中间件工具包的组件扫描bean配置
dubbo.component.scan.beans=appCompScanService

#应用级组件中间件工具包的组件装饰bean配置
dubbo.component.decorate.beans=appCompDecorateService

#应用级代码工具类注册，项目初始化时可以帮助初始化对应的工具类
#后面生成代码的时候可以删掉工具类，只专注于生成业务代码
#格式说明 eg:BaseEvent:core 前面是需要初始化的类，后面是这个类放到哪个模块下
dubbo.component.init.clazz=BaseEvent:core,Application:core,SpringApplicationContext:core,AppEventPublisher:core

```

##  projecttemplate-springboot.properties
生成springboot应用代码的配置
```
#数据库名称
codemaker.global.dbName=school_manager
#maven坐标-GAV
codemaker.pom.groupId=com.snail.school.manager
codemaker.pom.artifactId=schoolmanager-web
codemaker.pom.version=1.0.0.SNAPSHOT
#目标工程输出目录，这里填写对应的codemaker-web工程的绝对路径
codemaker.code.outpath=E:\\workspace\\tianhua-workspace\\code-maker\\codemaker-springboot
#应用服务的plantUML类图文件,不配置则走基于数据表的方式生成代码
codemaker.domain.plantuml=

```

##  projecttemplate-dynamicddd.properties

生成dynamicddd模块的代码配置
```
#目标工程根包名称
dynamicddd.global.package=com.lightsnail.snailapp.usercrm
#作者
dynamicddd.global.author=fanchunshuai

#目标工程输出目录，这里填写对应的codemaker-dynamicddd工程的绝对路径
dynamicddd.code.outpath=E:\\workspace\\tianhua-workspace\\code-maker\\codemaker-dynamicddd
#领域plantUML 类图
dynamicddd.domain.plantuml=CommonAuth.puml
```

##  projecttemplate-cola.properties

生成cola应用的代码配置
```
#数据库名称
cola.global.dbName=school_manager

cola.global.applicationName=school-manager

#目标工程输出目录，这里填写对应的工程的绝对路径
cola.code.outpath.cola-adapter=E:\\workspace\\tianhua-workspace\\code-maker\\codemaker-cola\\cola-adapter
cola.code.outpath.cola-infrast=E:\\workspace\\tianhua-workspace\\code-maker\\codemaker-cola\\cola-infrast
cola.code.outpath.cola-app=E:\\workspace\\tianhua-workspace\\code-maker\\codemaker-cola\\cola-app
cola.code.outpath.cola-domain=E:\\workspace\\tianhua-workspace\\code-maker\\codemaker-cola\\cola-domain
cola.code.outpath.cola-client=E:\\workspace\\tianhua-workspace\\code-maker\\codemaker-cola\\cola-client

#应用服务的plantUML类图文件,不配置则走基于数据表的方式生成代码
cola.domain.plantuml=

#是否构建api 文档，否则进行构建,默认构建
cola.api.generator=true

#需要导入的组件列表，多个逗号分割,适用于cola模块下依赖的业务组件包或者对外api接口包，或者cola项目本身已有的代码类,或者其他偏业务的工具类组件等等。
#如要生成的项目会依赖 infosys-user 服务的api则在这里定义即可。
cola.component.scan.config=apiresult,infosysuser,hutool-core

#需要在领域文档和调用时序文档中识别的读操作统一语言
cola.component.dsl.read=check

#需要在领域文档和调用时序文档中识别的写操作统一语言
cola.component.dsl.write=settle,apply

#是否需要根据该参数设置请求参数的最后一级包名为request,默认false
cola.subpackage.request=true

#是否需要根据该参数设置相应参数的最后一级包名为response,默认false
cola.subpackage.response=true



#应用级组件中间件工具包的组件扫描bean配置
cola.component.scan.beans=appCompScanService

#应用级组件中间件工具包的组件装饰bean配置
cola.component.decorate.beans=appCompDecorateService

#应用级代码工具类注册，项目初始化时可以帮助初始化对应的工具类
#后面生成代码的时候可以删掉工具类，只专注于生成业务代码
#格式说明 eg:BaseEvent:core 前面是需要初始化的类，后面是这个类放到哪个模块下
cola.component.init.clazz=BaseEvent:domain,Application:start,BaseController:adapter,SpringApplicationContext:domain,AppEventPublisher:domain

```

##  projecttemplate-springcloud.properties
生成springcloud应用代码的配置

生成cola应用的代码配置
```
#数据库名称
springcloud.global.dbName=infosys_auth

#项目名称
springcloud.global.applicationName=infosys-auth

#maven坐标-GAV
springcloud.pom.groupId=com.snail.school.manager
springcloud.pom.artifactId=schoolmanager-web
springcloud.pom.version=1.0.0.SNAPSHOT
#目标工程输出目录，这里填写对应的codemaker-web工程的绝对路径
springcloud.code.feign-api=E:\\workspace\\tianhua-workspace\\code-maker\\codemaker-springcloud\\springcloud-api
springcloud.code.feign-provider=E:\\workspace\\tianhua-workspace\\code-maker\\codemaker-springcloud\\springcloud-provider

#应用服务的plantUML类图文件,不配置则走基于数据表的方式生成代码
springcloud.domain.plantuml=auth-domainV4-feign.puml
#springcloud.domain.plantuml=SchoolManager.puml


#是否构建api 文档，否则进行构建,默认构建
springcloud.api.generator=true

#需要导入的组件列表，多个逗号分割,适用于cola模块下依赖的业务组件包或者对外api接口包，或者cola项目本身已有的代码类,或者其他偏业务的工具类组件等等。
#如要生成的项目会依赖 infosys-user 服务的api则在这里定义即可
springcloud.component.scan.config=apiresult


#需要在领域文档和调用时序文档中识别的读操作统一语言
springcloud.component.dsl.read=check

#需要在领域文档和调用时序文档中识别的写操作统一语言
springcloud.component.dsl.write=settle

#是否需要根据该参数设置请求参数的最后一级包名为request,默认false
springcloud.subpackage.request=false

#是否需要根据该参数设置相应参数的最后一级包名为response,默认false
springcloud.subpackage.response=false



#应用级组件中间件工具包的组件扫描bean配置,如无特定技术栈，无需更换该组件
springcloud.component.scan.beans=appCompScanService


#应用级组件中间件工具包的组件装饰bean配置,如无特定技术栈，无需更换该组件
springcloud.component.decorate.beans=appCompDecorateService


#应用级代码工具类注册，项目初始化时可以帮助初始化对应的工具类
#后面生成代码的时候可以删掉工具类，只专注于生成业务代码
#格式说明 eg:BaseEvent:core 前面是需要初始化的类，后面是这个类放到哪个模块下
springcloud.component.init.clazz=BaseEvent:provider,Application:provider,SpringApplicationContext:provider,AppEventPublisher:provider
```