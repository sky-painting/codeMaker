# codeMaker

## 模块说明

### 1. maven模块架构
![image](doc/img/codeMaker模块包.png)

### 2. 模块说明(浅红色部分)

####   2.1 codermaker-parent

codeMaker项目核心模块

#####   2.1.1 codermaker-api
codeMaker对外开放的低代码设计模型和二次开发接口,主要是组件扫描和代码元素装饰

#####   2.1.2 codermaker-apiImpl
codeMaker对外开放的默认组件扫描和代码装饰实现,可以在这个模块里实现二次开发

#####   2.1.3 codermaker-core
codeMaker的核心逻辑,支持多模式，多场景，多架构的Java后端代码生成核心实现模块

####   2.2 codermaker-dberparse
codermaker的组成套件 支持从plantUML文件中读取e-r数据库表结构图，构建sql DDDL

####   2.3 codermaker-dberPicture
codermaker的组成套件 支持导出目标数据库中的DDL模型到Plant UML文件中

####   2.4 codermaker-dbops
codermaker的组成套件 数据库表结构运维工具,根据配置的sql字段内容生成单表或者多表或者分库分表的sql脚本


### 3. 模块说明(浅蓝色部分) 

####   3.1 codermaker-springboot

基于springboot应用生成的代码会放在这里

####   3.2 codermaker-dubbo

基于dubbo应用框架生成的代码会放在这里

#####   3.2.1 dubbo-api

dubbo应用的consumer接口声明子模块

#####   3.2.2 dubbo-common

dubbo应用的持久化层子模块

#####   3.2.3 dubbo-core

dubbo应用的provider实现层子模块

####   3.3 codermaker-dynamicddd
基于plantUML类图+DDD思想生成的代码会放在这里,不依赖具体数据库,
存粹用来展示基于DDD的PlantUML建模文档生成的DDD代码是否符合预期

####   3.4 codermaker-cola
基于cola应用框架生成的代码会放在这里
#####   3.4.1 cola-client

cola应用的rpc接口声明子模块

#####   3.4.2 cola-adapter

cola应用的适配层子模块

#####   3.4.3 cola-app

cola应用的应用层子模块

#####   3.4.4 cola-infrast

cola应用的基础设施层子模块

#####   3.4.5 cola-domain

cola应用的领域层子模块

#####   3.4.6 cola-start

cola应用的启动子模块


#####   3.4.7 cola-feign-api

cola应用的springcloud支持feign调用模块

