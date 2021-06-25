# codeMaker

#### 介绍
为大规模微服务构建而创建的代码生成工具,属于天画项目中的基础产品
天画项目链接：https://gitee.com/sky-painting
coderMaker 立足于低代码平台，致力于解决软件开发过程中的效率问题
1.  不依赖任何代码生成工具
2.  基于mysql + mybatis + spring boot生成项目增删改查等功能
3.  项目工具目前主要是为了构建可复用的代码生成服务，后续会继续沉淀其他代码生成服务
4.  总体目标是为构建大规模springboot应用的技术底座，提高开发效率，专注业务领域，数据模型。

#### 项目计划
1.  目前第一版mvp已经基本可用，后续继续优化(80%)
2.  后续会按计划增加可复用的配置模板，比如application.properties
3.  按需生成公共服务类
4.  按需生成公共组件配置，如nacos,redis,sentinel等

#### 功能列表
1.  提供便捷且所见即所得的代码生成服务
2.  提供便捷的sql脚本(支持分库分表)生成服务
3.  提供一键式的数据库e-r图生成服务
4.  提供基于COLA架构下的代码生成服务
#### 软件架构
1.  整个项目分为多个工程模块
codermaker-core:根据数据库表结构和配置的代码模板生成项目代码
codermaker-dberPicture:根据数据库表结构或者按照指定数据接口生成对应数据模型e-r图(基于plantUML)
codermaker-dbops:数据库表结构运维工具,根据配置的sql字段内容生成单表或者多表或者分库分表的sql脚本
codermaker-web:根据codermaker-core项目生成的代码会在这个工程里展示,所见即所得,所操作即所得.
2.  codemaker-core是代码生成的核心部分
3.  codemaker-core下的resources目录是配置文件
4.  架构图
![image](doc/img/天画-低代码平台(codeMaker).png) 

#### 版本变更

1.  实现codermaker-core代码生成功能 1.0.0
2.  实现数据库表sql生成功能 1.0.0
3.  实现数据库表e-r图生成功能 1.0.1
4.  实现代码生成极速模式  1.0.2

####  projecttemplate.properties配置文件
#####  目标工程根包名称
codemaker.global.package=com.lightsnail.tianhua.datafactory
#####  作者
codemaker.global.author=fanchunshuai
#####  数据库名称
codemaker.global.dbName=data_factory
#####  项目名称
codemaker.pom.projectName=tianhua-datafactory
#####  maven坐标-GAV
codemaker.pom.groupId=com.lightsnail.tianhua.datafactory
codemaker.pom.artifactId=tianhua-datafactory
codemaker.pom.version=1.0.0.SNAPSHOT
#####  目标工程输出目录，这里填写对应的codemaker-web工程的绝对路径
codemaker.code.outpath=E:\\workspace\\tianhua-workspace\\code-maker\\codemaker-web
#####  引用的springboot版本
codemaker.spring.parentversion=2.3.1.RELEASE


#### sql表结构生成服务使用流程
##### 数据库支持
目前仅支持mysql数据库,其他数据库可基于本项目二次开发进行支持

#### 代码生成服务使用流程

1.  配置codemaker-core的application.properties中的数据库访问配置项，其他默认即可
2.  根据说明配置codemaker-core的projecttemplate.properties配置文件
3.  配置完成之后启动codemaker-core工程，默认端口为8099
4.  打开浏览器访问http://localhost:8099/makeall生成极简模式代码，生成的代码会放在codemaker-web下面的对应目录下，所操作即所得
5.  打开浏览器访问http://localhost:8099/makeallfast生成极速模式代码，生成的代码会放在codemaker-web下面的对应目录下，所操作即所得
6.  如有特殊需求进行定制则可通过codemaker-core工程中的resources/template模板进行调整

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