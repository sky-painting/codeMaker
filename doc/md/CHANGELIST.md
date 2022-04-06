# codeMaker

## 版本变更说明

### V1.0.0
1. 实现codermaker-core代码生成功能
2. 实现数据库表sql生成功能

### V1.0.1
1. 实现数据库表e-r图生成功能

### V1.0.2
1. 实现代码生成极速模式

### V1.0.3
1. 重构代码生成逻辑核心服务,引入应用层，支持多应用类型代码生成架构
2. 支持一套api操作接口生成dubbo,springboot应用代码 
3. 增加dubbo应用的代码模板，支持maven多模块
4. 引入bo,facade,facadeimpl,aop,dto等代码类生成模板 
5. dubbo项目支持facade层和service层双服务层业务架构
6. application.properties增加application.type属性，值为springboot,dubbo,cola
7. 增加projecttemplate-dubbo.properties属性文件，支持dubbo项目代码生成
8. 原有配置文件projecttemplate.properties改名为projecttemplate-springboot.properties
9. 修复若干其他bug

### V1.0.4
1. 基于plantUML文档生成代码，基于ddd思想和模式生成模块级的代码内容
2. 增加makeddd接口，支持生成dynamicddd模块的代码
3. 引入支持ddd代码生成的配置(projecttemplate-dynamicddd.properties)和代码模板(template/dynamicddd)
4. codemaker-core模块resources目录增加ddd-plantuml目录存放plantUML类图
5. 支持ddd的一些模式代码生成：实体模式,值对象模式,聚合根模式,工厂模式,仓库模式,防腐层模式,服务模式,模块模式,CQE模式,领域网关
6. 产出部分公共代码生成服务方法
7. 优化代码生成核心链路代码模型，针对ddd代码生成做了分层处理
8. 修复若干其他bug

### V1.1.0
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


### V0.0.2
1. 本次版本新增codemaker-dberparse模块，支持plantuml er图解析为sql ddl create语句，已集成到codemaker-dbops
   模块中。


### V1.2.0-alpha
1. 支持event模型生成,
2. 支持mqconsumer,applistener,mqproducer,mqhandler生成
3. 屏蔽扩展类dto带有bo属性的字段
4. 增加对plantuml domain类图内容的校验，比如重复字段和重复方法
5. 扩展动态ddd的代码生成能力，比如一个BO下出现多个facade,controller的接口 
6. 提高plantuml内容的解析兼容性和稳定性
7. 支持动态链路调用plantuml-调用链路图解析
8. 支持读写分离的dubbo,restController接口调用 
9. 支持将领域值对象枚举类暴露到dubbo client端
10. 优化convert代码生成
11. gatawayimpl 与 repositoryimpl分开生成
12. 优化包路径生成模式
13. 优化访问描述符和代码注释
14. 读取plantuml文件对于方法的解析将方法参数独立解析出来


### V1.2.1-beta
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




### 1.2.2 版本(主要支持配置化，组件插件化，模块化架构,去除对coderutils的强依赖)
1. 支持按领域上下文和request,response分包策略，达到动态分包的效果(可全部平铺也可以按上下文分子包)
2. 增加接口按服务维度持久化功能,基于文件(辅助对上下游适配层进行方法生成)，api文件生成-->集中式(这里持久化可能不是最优方案，已通过组件化架构升级解决)
3. 重构包动态引用模块，支持自定义引用包配置化,配置文件配置,支持组件插件化架构(已通过组件化架构升级解决)
4. 支持从plantuml领域文档中导出接口文档
5. 支持动态query查询对象完善动态查询逻辑
6. 增加自定义class类模版组件类，注册其他外部jar包类或者公共业务类，如PageBO,XxxUtils,MybatisConfig或者CommonService等(已通过组件化架构升级解决)
7. 支持业务领域DSL统一语言(读写操作)注册功能，扩展读写场景模式校验 ReadWriteTypeEnum-->基于自定义配置
8. 增加cache类代码元素生成
9. 支持事件发布异步化的代码绘制(产生事件->发布->消费事件)
10. 增加多个配置项支持组件插件化-->具体配置项
11. 去除IClazzAdapter包模块，升级为plantUML文档依赖的可配置包
12. 去除对coderman-utils包的强依赖,将其通过组件化架构改为弱依赖
13. 通过派生类上下文对象提前初始化重构类转换工厂，减少冗余代码
14. 对相对独立的工具类代码进行组件化管理，按场景需要进行动态化配置
15. 删除相对独立的工具类代码生成逻辑，根据16条整体改善
16. 增加cola应用的cola-feign-api模块，支持spring cloud 远程调用
17. 增加codemaker-springcloud应用，来专门支持springcloud技术栈
18. 增加feign类代码元素，与dubbo类似，提供api接口和参数并提供动态生成能力
19. 重构codeMaker模块,增加codemaker-api,codemaker-apiImpl模块开放低代码设计模型,组件扫描,代码元素装饰接口能力
20. 根据20条的改动整体支持组件化和提供二次开发能力


### 1.3.0 版本(进行一次整体的架构升级)
1.支持在调用时序中增加自定义流程控制语句如if else,fori,fore,fors,filter,mappingby,groupby(该方案容易导致时序图过于细化同时时序图不容易表达代码化的东西，已通过javsScript解决)
2.支持可配置化的多种入参校验模式策略(方案设计)
3.支持在plantuml领域文档中增加方法注解并解析生成到项目代码里(transactional,lock,logrecord注解等)
4.降低对数据库链接的强依赖，以数据库e-r图或者excel表作为数据库元数据信息的来源(链接默认数据库即可)
5.自定义代码元素模板/片段，并融入到代码生成流程中，进一步开放代码生成能力接口(方案设计)(代码元素模板自定义实现完成)
6.自定义代码元素模板/片段，并融入到动态调用时序中(同第一点，代码片段可能需要结合JavsScript构建方法内容)
7.在代码生成流程中调用时序中增加代码段(同第一点)
8.在代码生成流程中调用时序识别范型
9.增加配置项相关代码生成(方案设计)
10.将数据库sql文件复制到项目工程里
11.将plantUMl文档调用时序文档复制到项目工程里
12.打通语雀等api文档管理软件(技术上存在问题,不一定完全兼容)
13.管理pom依赖,走自动生成,统一maven技术栈
14.扩展plantUML生成代码时允许依赖外部代码类(如plantUML类中定义了xxxBO,但是需要显示的继承BaseBO,BaseBO可以不用单独声明class)(?)
15.基于plantUML 的e-r图模式构建数据库模型
16.支持mybatis-plus组件,网关组件,脚手架等案例适配(mp,liteflow)
17.使用map+function重构各个应用类型下的代码生成逻辑
18.支持每个组件的配置项同步到项目工程中
19.新建codemaker-javs集成javs引擎
20.增加参数配置是否集成javs并生成javs脚本项目
21.支持纯数据库模式引入配置信息生成，自定义类信息生成，支持自定义组件导入
22.支持极速模式引入配置信息生成，自定义类信息生成，支持自定义组件导入

