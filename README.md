# codeMaker


#### 介绍
为大规模微服务构建而创建的代码生成工具,属于天画项目中的基础产品
天画项目链接：https://gitee.com/sky-painting
coderMaker 立足于低代码平台，致力于解决软件开发过程中的效率问题
1. 不依赖任何代码生成工具
2. 本身基于mysql + mybatis + spring boot生成项目增删改查等功能
3. 项目工具目前主要是为了构建可复用的代码生成服务
4. 总体目标是为构建大规模分布式应用的技术底座，提高开发效率，专注业务领域，数据模型。
5. 为高并发,分布式,大数据等场景提供业务应用代码支持,辅助学习者方便快捷构建大量分布式微服务应用。
6. 支持多框架应用+动态ddd+动态调用方法绘制的Java代码生成平台

#### 项目计划
1. 参考todoList文件内容
2. 参考changeList文件内容

#### 设计理念
1. 所建即所得，文档即代码
2. 数据库模型与领域模型同步支持
3. 不做任何脚手架,不依赖任何特定框架版本
4. 按开发者习惯支持多模式生成

#### 功能列表
1. 提供数据库字段文本转sql脚本的能力
2. 提供便捷的sql脚本(支持分库分表)生成服务，帮助初始化数据库
3. 提供一键式的数据库e-r图生成服务，以及从plantUML-er图中解析还原到sql脚本的能力
4. 提供基于springboot,cola,dubbo应用框架和架构下的应用级代码生成服务
5. 提供基于plantuml类图的dynamic-ddd模块级的代码生成服务
6. 支持基于数据库表的代码生成和支持plantuml领域文档的代码生成服务
7. 支持基于数据库表+DDD+plantUML类图文档+plantUML调用时序文档的代码生成服务
8. 支持可适配可拔插的组件化模块化代码生成(待架构升级)

#### 软件架构
1.  整个项目分为多个工程模块

codermaker-core:代码生成的核心模块

codermaker-dberPicture:根据数据库表结构或者按照指定数据接口生成对应数据模型e-r图(基于plantUML)

codermaker-dbops:数据库表结构运维工具,根据配置的sql字段内容生成单表或者多表或者分库分表的sql脚本

codermaker-springboot:基于springboot应用生成的代码会放在这里

codemaker-dubbo:基于dubbo应用框架生成的代码会放在这里
  1. dubbo-api:dubbo应用的consumer接口声明子模块
  2. dubbo-common:dubbo应用的持久化层子模块
  3. dubbo-core:dubbo应用的provider实现层子模块
  
codemaker-dynamicddd:基于plantUML类图+DDD思想生成的代码会放在这里

codemaker-cola:基于cola应用框架生成的代码会放在这里
    1. cola-client:cola应用的rpc接口声明子模块
    2. cola-adapter:cola应用的适配层子模块
    3. cola-app:cola应用的应用层子模块
    4. cola-infrast:cola应用的基础设施层子模块
    5. cola-domain:cola应用的领域层子模块
  
2.  架构图
    1. v1版本
![image](doc/img/天画-低代码平台(codeMaker).png) 

    2. v2版本
![image](doc/img/天画-低代码平台(codeMaker)-v3.png) 


#### 版本变更

##### 版本-1.0.0

简介: 实现codermaker-core代码生成功能 

##### 版本-1.0.1
简介: 实现数据库表e-r图生成功能

##### 版本-1.0.2
简介:  实现代码生成极速模式

##### 版本-1.0.3
简介:  实现dubbo应用的代码生成

##### 版本-1.0.4
简介:  实现ddd业务模型的代码生成 

1. 基于plantUML文档生成代码，基于ddd思想和模式生成模块级的代码内容
2. 增加makeddd接口，支持生成dynamicddd模块的代码
3. 引入支持ddd代码生成的配置(projecttemplate-dynamicddd.properties)和代码模板(template/dynamicddd)
4. codemaker-core模块resources目录增加ddd-plantuml目录存放plantUML类图
5. 支持ddd的一些模式代码生成：实体模式,值对象模式,聚合根模式,工厂模式,仓库模式,防腐层模式,服务模式,模块模式,CQE模式,领域网关
6. 产出部分公共代码生成服务方法
7. 优化代码生成核心链路代码模型，针对ddd代码生成做了分层处理
8. 修复若干其他bug

##### 版本-0.0.2
简介:  本次版本新增codemaker-dberparse模块，支持plantuml er图解析为sql ddl create语句，已集成到codemaker-dbops
  模块中。
##### 版本-1.1.0
简介:  实现springboot,cola,dubbo+ddd的代码生成

1. 支持cola应用架构代码生成,增加cola应用的template代码模板
2. 增加基于plantuml类图的领域服务代码生成接口
   /getproject/valueobject
   /getproject/msgbody
   /getproject/gataway
   /getproject/acladapter
   /getproject/command
   /getproject/executor
   /getproject/factory
   /getproject/dtoboconvert
   /getproject/voboconvert
   /getproject/doboconvert
3. 精简不同应用框架的代码生成配置
    在每个应用框架配置下增加dubbo.domain.plantuml配置项,配置plantuml类图文件名称,支持基于类图-ddd的代码生成
    如果不配置则不能借助plantuml类图生成基于领域服务ddd的代码，而是生成基于数据库表的常规代码
    去除以下三个配置，集中到application.properties文件中
    *.global.package
    *.global.author
    *.global.applicationName

4. 在springboot,cola,dubbo的代码模板目录下增加ddd元素的tempalte代码模板
5. 将dynamic-ddd的代码生成服务整合到springboot,cola,dubbo的代码生成服务中，支持基于DDD思想的代码生成
6. 扩展plantuml中类图标签,基于BO派生多个代码生成对象（vo,dto,facade,doboconvert,controller,voboconvert,dtoboconvert）
7. 整合底层代码支持一套api,一套服务支撑springboot,cola,dubbo应用级代码生成和dynamic-ddd模块级代码生成
8. 修复多个兼容性bug
9. 整体上支持基于数据库表结构的代码生成和基于plantUML类图文档的代码生成
 
##### 版本-1.2.0-alpha
简介: 实现springboot,cola,dubbo+ddd的代码生成的基础上增加解析调用时序图的逻辑，将调用时序代码逻辑融入到生成的代码方法里
1. 支持event模型生成,
2. 支持mqconsumer,applistener,mqproducer,mqhandler生成
3. 屏蔽扩展类dto带有bo属性的字段
4. 增加对plantuml domain类图内容的校验，比如重复字段和重复方法
5. 扩展动态ddd的代码生成能力，比如一个BO下出现多个facade,controller的接口
6. 提高plantuml内容的解析兼容性和稳定性
7. 增加解析plantUML调用时序文档的能力，并将调用逻辑融入代码生成的方法里
8. 支持读写分离的dubbo,restController接口调用
9. 支持将领域值对象枚举类暴露到rpc client端
10. 优化convert代码生成
11. gatawayimpl 与 repositoryimpl分开生成
12. 优化包路径生成模式
13. 优化访问描述符和代码注释
14. 读取plantuml文件对于方法的解析将方法参数独立解析出来

##### 版本-1.2.1-beta
1. 支持默认带工具类SpringEventPublisher帮助事件发布与消费异步化
2. 重构读取plantUML文件的相关逻辑降低复杂度
3. 重新梳理读取plantUML文件的相关路径,优化为按项目维度分别存放
4. 支持复杂场景下的代码绘制能力,进行精细化控制(如listbo,listvo的转换,代码参数动态绘制引用等)
5. 支持分页PageVO,PageDTO,PageBO生成到接口方法里
6. 支持通过时序图的调用方法列表补充构建接口和类的方法，辅助完善业务调用流程(如xxMapper.getByCode不在XXMapper里，则通过时序图里的信息进行动态补充)
7. 根据动态调用方法绘制内容将return body也进行动态化绘制
8. 增加适配模块解除对codemaker本身的定制化类的依赖(ResultDto,ResultDataDto,PageVO,PageDTO)，可辅助二次开发增加企业适配能力
9. 基于数据库生成模式下的底层增加分页page查询方法和count查询方法
10. 重构app层下的springboot,cola,dubbo写文件实现逻辑，降低复杂度
11. 重构service层下的模版方法模式和建造者模式代码逻辑，降低复杂度
12. 增加mapper接口和controller接口的参数校验注解(要引用的注解类可先写死，后续优化)
13. 增加时序图调用方法参数动态推导功能(如xxxGataWay.getBySystemName没有在领域文档中定义，在时序图中有定义但是没有写明参数，这里可以做动态推导)
14. 补充bo关联表时缺失的主键id
15. 增加!字符屏蔽不需要解析的planUML文档内容,便于调试代码生成


####  配置文件说明
1.application.properties:代码生成服务的核心配置
```
# FREEMARKER (FreeMarkerAutoConfiguration)
spring.freemarker.allow-request-override=false
spring.freemarker.allow-session-override=false
spring.freemarker.cache=false
spring.freemarker.charset=UTF-8
spring.freemarker.check-template-location=true
spring.freemarker.content-type=text/html
spring.freemarker.enabled=true
spring.freemarker.expose-request-attributes=false
spring.freemarker.expose-session-attributes=false
spring.freemarker.expose-spring-macro-helpers=true
spring.freemarker.prefer-file-system-access=true
# 这是重点， 会过滤.ftl后缀的文件
spring.freemarker.suffix=.ftl
# spring boot 默认的页面模板存放目录
spring.freemarker.template-loader-path=classpath:/template/
spring.freemarker.settings.template_update_delay=0
spring.freemarker.settings.default_encoding=UTF-8
spring.freemarker.settings.classic_compatible=true

server.port=8099
management.port=8073

# 跨域(需要集成到公司服务的可以配置)
endpoints.cors.allowed-origins=http://127.0.0.1:8083
endpoints.cors.allowed-methods=GET,POST

#-------------以下是生成代码相关的业务配置----------
#数据库配置
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/school_manager?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC
spring.datasource.username=
spring.datasource.password=

#配置.xml文件路径
mybatis.config-locations=classpath:mybatis-config.xml
mybatis.mapper-locations=classpath:mapper/*.xml
#配置模型路径
mybatis.type-aliases-package=com.coderman.codemaker.bean
spring.application.name=codemaker
#生成的应用类型，支持springboot,dubbo,cola
application.type=dubbo

```
2.projecttemplate-dubbo.properties:生成dubbo应用代码的配置
```
#数据库名称
dubbo.global.dbName=school_manager
#目标工程输出目录，这里填写对应的工程的绝对路径
dubbo.code.outpath.dubbo-common=E:\\workspace\\tianhua-workspace\\code-maker\\codemaker-dubbo\\dubbo-common
dubbo.code.outpath.dubbo-api=E:\\workspace\\tianhua-workspace\\code-maker\\codemaker-dubbo\\dubbo-api
dubbo.code.outpath.dubbo-core=E:\\workspace\\tianhua-workspace\\code-maker\\codemaker-dubbo\\dubbo-core
#应用服务的plantUML类图文件,不配置则走基于数据表的方式生成代码
codemaker.domain.plantuml=
```

3.projecttemplate-springboot.properties:生成springboot应用代码的配置
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

4.projecttemplate-dynamicddd.properties:生成dynamicddd模块的代码配置

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

5.projecttemplate-cola.properties:生成cola应用的代码配置
```
#数据库名称
cola.global.dbName=school_manager

cola.global.applicationName=school-manager

#目标工程输出目录，这里填写对应的工程的绝对路径
cola.code.outpath.cola-adapter=/Users/dasouche/scworkspace/sourceSpace/code-maker/codemaker-cola/cola-adapter
cola.code.outpath.cola-infrast=/Users/dasouche/scworkspace/sourceSpace/code-maker/codemaker-cola/cola-infrast
cola.code.outpath.cola-app=/Users/dasouche/scworkspace/sourceSpace/code-maker/codemaker-cola/cola-app
cola.code.outpath.cola-domain=/Users/dasouche/scworkspace/sourceSpace/code-maker/codemaker-cola/cola-domain
cola.code.outpath.cola-client=/Users/dasouche/scworkspace/sourceSpace/code-maker/codemaker-cola/cola-client

#应用服务的plantUML类图文件,不配置则走基于数据表的方式生成代码
cola.domain.plantuml=

```


#### sql表结构生成服务使用流程
##### 数据库支持
目前仅支持mysql数据库,其他数据库可基于本项目二次开发进行支持

#### 代码生成服务使用流程
##### 1.应用级项目生成
1.  配置codemaker-core的application.properties中的数据库访问配置项，配置需要生成的应用类型(application.type取值:支持springboot,dubbo,cola)
2.  根据应用类型配置对应的projecttemplate-*.properties配置文件
3.  配置完成之后启动codemaker-core工程，默认端口为8099
4.  打开浏览器访问http://localhost:8099/makeall生成极简模式代码，生成的代码会放在codemaker-web下面的对应目录下
5.  打开浏览器访问http://localhost:8099/makeallfast生成极速模式代码，生成的代码会放在codemaker-web下面的对应目录下
6.  如有特殊需求进行定制则可通过codemaker-core工程中的resources/template模板进行调整


##### 2.dynamicddd模块级代码生成
1. 将项目的plantUML类图放到codemaker-core的resources下的ddd-plantuml文件夹
2. 配置projecttemplate-dynamicddd.properties
3. 启动服务,浏览器打开输入http://localhost:8099/makeddd
4. 执行完毕查看codemaker-dynamicddd工程模块下的代码是否已经生成

##### 3.接口文档
[API.md](API.md)



#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支,需求todo参考todoList文档
3.  提交代码
4.  新建 Pull Request


#### 码云特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5.  码云官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)