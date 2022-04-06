#基于全文档模式(推荐)

## 说明
此模式是为了摆脱codeMaker必须要链接目标数据库才能获取数据模型的限制。基于此模型开发者只需要提供
数据模型和领域模型以及调用时序图文档(可选)即可完成整个项目的代码生成。

## 使用步骤
现在按照codeMaker内置的express数据库表结构进行演示，具体使用过程已在README文档的技术实现链接里。
这里再详细说明一下使用过程。

### 1.修改配置
1. 修改application.global.linkdb属性为false
2. 默认的数据库链接可以不用管，或者随便链接到一个数据源

### 1.修改codemaker-core工程下resources目录的项目配置文件application.properties
1. 修改application.global.linkdb属性为false，默认的数据库链接可以不用管，或者随便链接到一个数据源
2. 设置生成项目的应用类型(application.type)的属性
3. 设置目标应用根包名称
4. 设置项目构建的作者名称
5. 组件化相关配置(可选)
    1. 配置本地maven仓库的绝对路径
    2. 配置框架级中间件或者框架的组件列表(默认即可)
    3. 配置组件扫描bean(默认即可)
    4. 配置组件装饰bean(默认即可)
6. 配置示例
```properties
#数据库配置
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/infosys_auth?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC
spring.datasource.username=shenshuai
spring.datasource.password=

#应用类型配置，支持springboot,dubbo,cola,springcloud
application.type=cola

#目标应用根包名称
application.global.package=com.coderman.infosys.auth

# 作者
application.global.author=shenshuai

# 组件化需要的maven repository本地路径,用来扫描依赖的组件jar包
application.maven.repo.path=jar:file:///Users/shenshuai/.m2/repository

#代码生成需要的全局组件，框架中间件可以放到全局组件依赖配置里,类似于脚手架,或者自己封装的业务组件框架,属于框架中间件级别的
application.component.scan.config=dubbo,spring-web,openfeign

#自定义的组件扫描bean,defaultCompScanService为codeMaker默认实现支持全局组件的配置，开发者可以参考进行自定义扫描组件实现来替代掉默认的用以适配自己公司的技术栈
application.component.scan.bean=defaultCompScanService

#自定义的组件装饰bean,defaultCompDecorateService默认实现支持全局组件的装饰，开发者可以参考进行自定义装饰组件的实现来替代掉默认的用以适配自己公司的技术栈
application.component.decorate.bean=defaultCompDecorateService

```

### 3.根据配置的应用类型修改projecttemplate-application.properties配置
1. 配置数据库名称
2. 配置应用名称
3. 配置项目工程代码输出绝对路径
4. 配置应用服务的数据库模型plantUML类图文件(可选)
    1. 在codemaker-core模块下配置/er-express/infosys-express/infosys_express.puml
    2. infosys_express为数据库名称
5. 配置应用服务的DDD 领域模型plantUML类图文件(可选)
    1. 领域模型plantUML类图可以只包括DDD相关的代码元素
    2. 也可以引入接口API
    3. 也可以引入调用时序
    4. 领域模型plantUML类图文档参考/ddd-plantuml/infosys-express目录下的文件
    5. 调用时序plantUML文档参考/invoke-plantumlinfosys-express目录下的文件
6. 配置是否构建api文档(默认为true)
7. 配置需要导入的组件列表(业务组件,工具组件,可选)
    1. 框架级组件请参考/component目录下的dubbo,spring-web,openfeign文件夹
    2. 业务组件或者封装的工具组件请参考/component目录下的hutool-core,infosysuser,apiresult文件夹
    3. 独立类组件请参考/component目录下的/singleClass文件夹
    4. 如果需要新定义独立类组件并被初始化项目使用还需要在/template目录下的singleClass文件夹中配置ftl模板
8. 配置DDD中的读写领域特定语言标示(可选)
9. 配置子包是否按照request,response进行分包
10. 配置应用级组件的扫描bean和装饰bean(可选)
11. 配置代码工具类级别的初始化依赖工具集(可选)
12. 配置示例
```properties

#数据库名称
cola.global.dbName=infosys_auth

#应用名称
cola.global.applicationName=infosys-auth

#目标工程输出目录，这里填写对应的工程的绝对路径
cola.code.outpath.cola-adapter=/Users/shenshuai/myspace/code-maker/codemaker-cola/cola-adapter
cola.code.outpath.cola-infrast=/Users/shenshuai/myspace/code-maker/codemaker-cola/cola-infrast
cola.code.outpath.cola-app=/Users/shenshuai/myspace/code-maker/codemaker-cola/cola-app
cola.code.outpath.cola-domain=/Users/shenshuai/myspace/code-maker/codemaker-cola/cola-domain
cola.code.outpath.cola-client=/Users/shenshuai/myspace/code-maker/codemaker-cola/cola-client
cola.code.outpath.cola-start=/Users/shenshuai/myspace/code-maker/codemaker-cola/cola-start
cola.code.outpath.cola-feign-api=/Users/shenshuai/myspace/code-maker/codemaker-cola/cola-feign-api

#应用服务的plantUML类图文件,不配置则走基于数据表的方式生成代码
cola.domain.plantuml=auth-domainV4-feign.puml

#是否构建api 文档，否则进行构建,默认构建
cola.api.generator=true

# 需要导入的组件列表，多个逗号分割,适用于cola模块下依赖的业务组件包或者对外api接口包，或者cola项目本身已有的代码类,或者其他偏业务的工具类组件等等。
# 如要生成的项目会依赖 infosys-user 服务的api则在这里定义即可。
cola.component.scan.config=apiresult,infosysuser,hutool-core

# 需要在领域文档和调用时序文档中识别的读操作统一语言
# 纯数据库模式可以不需要
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

# 代码工具类注册，项目初始化时可以帮助初始化对应的工具类
# 后面生成代码的时候可以删掉工具类，只专注于生成业务代码
# 格式说明 eg:BaseEvent:core 前面是需要初始化的类，后面是这个类放到哪个模块下
cola.component.init.clazz=BaseEvent:domain,Application:start,BaseController:adapter,SpringApplicationContext:domain,AppEventPublisher:domain

```

### 4.codeMaker-core启动
1. codemaker-core项目已迁移到codemaker-parent父级工程下
2. 使用Main方法启动即可
    1. 启动时会通过类加载机制扫描配置的组件
    2. 如有报错信息请按上述步骤检查相关配置
### 5.准备生成代码
#### 第一种方式
直接访问:http://127.0.0.1:8099/makeall,构建所有代码元素
#### 第二种方式
访问:http://127.0.0.1:8099/getproject/mapper,构建指定代码元素
#### 第三种方式
访问:http://127.0.0.1:8099/makemodules?tableNames=,增量构建模块级别的对应代码元素

