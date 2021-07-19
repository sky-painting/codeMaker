# codeMaker


#### 介绍
为大规模微服务构建而创建的代码生成工具,属于天画项目中的基础产品
天画项目链接：https://gitee.com/sky-painting
coderMaker 立足于低代码平台，致力于解决软件开发过程中的效率问题
1.  不依赖任何代码生成工具
2.  基于mysql + mybatis + spring boot生成项目增删改查等功能
3.  项目工具目前主要是为了构建可复用的代码生成服务，后续会继续沉淀其他代码生成服务
4.  总体目标是为构建大规模springboot应用的技术底座，提高开发效率，专注业务领域，数据模型。
5.  目前已演化为支持多框架应用+动态ddd的Java代码生成平台

#### 项目计划
1. 参考todoList文件内容
2. 参考changeList文件内容

#### 功能列表
0.  所建即所得，文档即代码的代码生成服务理念
1.  提供便捷且所建即所得的代码生成服务
2.  提供便捷的sql脚本(支持分库分表)生成服务
3.  提供一键式的数据库e-r图生成服务
4.  提供基于springboot,cola,dubbo应用框架和架构下的应用级代码生成服务
5.  提供基于plantuml类图的dynamic-ddd模块级的代码生成服务
6.  支持基于数据库表的代码生成和支持plantuml领域文档的代码生成服务

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
![image](doc/img/天画-低代码平台(codeMaker)-v2(2).png) 


#### 版本变更

1.  实现codermaker-core代码生成功能 1.0.0
2.  实现数据库表sql生成功能 1.0.0
3.  实现数据库表e-r图生成功能 1.0.1
4.  实现代码生成极速模式  1.0.2
5.  实现dubbo应用的代码生成  1.0.3，详情见changeList文件
6.  实现ddd业务模型的代码生成  1.0.4，详情见changeList文件
7.  实现springboot,cola,dubbo+ddd的代码生成  1.1.0，详情见changeList文件

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