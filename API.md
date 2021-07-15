#### 一、单个代码元素生成接口

##### 1. 生成entity
1. 请求地址:http://127.0.0.1:8099/getproject/mapper
2. 调用方式:GET
3. 请求方式:HTTP
4. 请求参数:无
5. 返回结果:success
6. 特殊说明:此接口基本不再参与代码生成，entity模型后续会被DO(dataobject)
替代

##### 2. 生成mapper
1. 请求地址:http://127.0.0.1:8099/getproject/mapper
2. 调用方式:GET
3. 请求方式:HTTP
4. 请求参数:无
5. 返回结果:success
6. 特殊说明:基于数据库表的mapper代码生成,
    1. 在springboot项目是单模块存储,
    2. 在dubbo项目存储于dubbo-common子模块,
    3. 在cola项目存储于cola-infrast子模块


##### 3. 生成do
1. 请求地址:http://127.0.0.1:8099/getproject/do
2. 调用方式:GET
3. 请求方式:HTTP
4. 请求参数:无
5. 返回结果:success
6. 特殊说明:基于数据库表的do(dataobject,也就是entity)代码生成,
    1. 在springboot项目是单模块存储,
    2. 在dubbo项目存储于dubbo-common子模块,
    3. 在cola项目存储于cola-infrast子模块

##### 4. 生成vo
1. 请求地址:http://127.0.0.1:8099/getproject/vo
2. 调用方式:GET
3. 请求方式:HTTP
4. 请求参数:无
5. 返回结果:success
6. 特殊说明:基于数据库表或者plantUML类图中bo类的vo(viewobject)代码生成,
    1. 在springboot项目是单模块存储,
    2. 由于dubbo没有viewobject(VO)的概念，因此不支持vo生成
    3. 在cola项目存储于cola-adapter子模块

##### 5. 生成service+serviceimpl
1. 请求地址:http://127.0.0.1:8099/getproject/service
2. 调用方式:GET
3. 请求方式:HTTP
4. 请求参数:无
5. 返回结果:success
6. 特殊说明:基于数据库表的代码生成,
    1. 在springboot项目是单模块存储,
    2. 在dubbo项目存储于dubbo-core子模块
    3. 在cola项目存储于cola-domain子模块
    4. 由于plantUML类中也有service的概念，这里暂时只支持基于数据库表的service代码生成
    后续发版会补充完善

##### 6. 生成mapperxml
1. 请求地址:http://127.0.0.1:8099/getproject/mapperxml
2. 调用方式:GET
3. 请求方式:HTTP
4. 请求参数:无
5. 返回结果:success
6. 特殊说明:基于数据库表的代码生成,统一存储于resources/mapper文件夹下
    1. 在springboot项目是单模块存储,
    2. 在dubbo项目存储于dubbo-core子模块
    3. 在cola项目存储于cola-infrast子模块


##### 7. 生成controller
1. 请求地址:http://127.0.0.1:8099/getproject/controller
2. 调用方式:GET
3. 请求方式:HTTP
4. 请求参数:无
5. 返回结果:success
6. 特殊说明:基于数据库表或者plantUML.BO类的代码生成
    1. 在springboot项目是单模块存储,
    2. dubbo项目里没有controller的概念,因此这里不支持
    3. 在cola项目存储于cola-adapter子模块

##### 8. 生成test
1. 请求地址:http://127.0.0.1:8099/getproject/test
2. 调用方式:GET
3. 请求方式:HTTP
4. 请求参数:无
5. 返回结果:success
6. 特殊说明:基于数据库表的代码生成,统一生成在项目里的test目录下
    1. 在springboot项目是单模块存储,
    2. 在dubbo项目存储于dubbo-common子模块
    3. 在cola项目存储于cola-infrast子模块
    4. 该接口目前只支持facade,mapper接口测试类的生成，服务类的后续再迭代

##### 9. 生成facade+facadeimpl
1. 请求地址:http://127.0.0.1:8099/getproject/facade
2. 调用方式:GET
3. 请求方式:HTTP
4. 请求参数:无
5. 返回结果:success
6. 特殊说明:基于数据库表或者plantUML.BO类的代码生成
    1. 在springboot项目不支持facade类的生成
    2. 在dubbo项目存储于dubbo-api子模块
    3. 接口在cola项目存储于cola-client子模块，实现类在cola-app子模块中


##### 10. 生成dto
1. 请求地址:http://127.0.0.1:8099/getproject/dto
2. 调用方式:GET
3. 请求方式:HTTP
4. 请求参数:无
5. 返回结果:success
6. 特殊说明:基于数据库表或者plantUML.BO类的代码生成
    1. 在springboot项目是单模块存储,
    2. 在dubbo项目存储于dubbo-api子模块
    3. 在cola项目存储于cola-client子模块

##### 11. 生成bo
1. 请求地址:http://127.0.0.1:8099/getproject/bo
2. 调用方式:GET
3. 请求方式:HTTP
4. 请求参数:无
5. 返回结果:success
6. 特殊说明:基于数据库表或者plantUML.BO(Business Object)类的代码生成
    1. 在springboot项目是单模块存储,
    2. 在dubbo项目存储于dubbo-core子模块
    3. 在cola项目存储于cola-domain子模块

##### 12. 生成valueobject+enum
1. 请求地址:http://127.0.0.1:8099/getproject/valueobject
2. 调用方式:GET
3. 请求方式:HTTP
4. 请求参数:无
5. 返回结果:success
6. 特殊说明:基于plantUML.BO,plantUML.Enum类(xxxConfig,xxxEum)的代码生成
    1. 在springboot项目是单模块存储,
    2. 在dubbo项目存储于dubbo-core子模块
    3. 在cola项目存储于cola-domain子模块

##### 13. 生成msgbody
1. 请求地址:http://127.0.0.1:8099/getproject/msgbody
2. 调用方式:GET
3. 请求方式:HTTP
4. 请求参数:无
5. 返回结果:success
6. 特殊说明:基于plantUML.msgbody类的代码生成
    1. 在springboot项目是单模块存储,
    2. 在dubbo项目存储于dubbo-core子模块
    3. 在cola项目存储于cola-domain子模块

##### 14. 生成gataway+repository+gatawayimpl+repositoryimpl
1. 请求地址:http://127.0.0.1:8099/getproject/gataway
2. 调用方式:GET
3. 请求方式:HTTP
4. 请求参数:无
5. 返回结果:success
6. 特殊说明:基于plantUML.gataway,repository类的代码生成
    1. 在springboot项目是单模块存储,
    2. 在dubbo项目存储于dubbo-core子模块
    3. 在cola项目存储于cola-domain子模块,实现类在cola-infrast子模块中


##### 15. 生成acladapter+acladapterimpl+aclparam
1. 请求地址:http://127.0.0.1:8099/getproject/acladapter
2. 调用方式:GET
3. 请求方式:HTTP
4. 请求参数:无
5. 返回结果:success
6. 特殊说明:基于plantUML.acladapter类的代码生成
    1. 在springboot项目是单模块存储,
    2. 在dubbo项目存储于dubbo-core子模块
    3. 接口和实现类和请求参数在cola项目存储于cola-infrast子模块中

##### 16. 生成command
1. 请求地址:http://127.0.0.1:8099/getproject/command
2. 调用方式:GET
3. 请求方式:HTTP
4. 请求参数:无
5. 返回结果:success
6. 特殊说明:基于plantUML.command类的代码生成，支持继承类的解析
    1. 在springboot项目是单模块存储,
    2. 在dubbo项目存储于dubbo-core子模块
    3. 在cola项目存储于cola-app子模块中


##### 17. 生成executor
1. 请求地址:http://127.0.0.1:8099/getproject/executor
2. 调用方式:GET
3. 请求方式:HTTP
4. 请求参数:无
5. 返回结果:success
6. 特殊说明:基于plantUML.exe类的代码生成，支持接口和抽象类的解析
    1. 在springboot项目是单模块存储,
    2. 在dubbo项目存储于dubbo-core子模块
    3. 在cola项目存储于cola-app子模块中

##### 18. 生成factory
1. 请求地址:http://127.0.0.1:8099/getproject/factory
2. 调用方式:GET
3. 请求方式:HTTP
4. 请求参数:无
5. 返回结果:success
6. 特殊说明:基于plantUML.factory类的代码生成
    1. 在springboot项目是单模块存储,
    2. 在dubbo项目存储于dubbo-core子模块
    3. 在cola项目存储于cola-domain子模块中


##### 19. 生成dtoboconvert
1. 请求地址:http://127.0.0.1:8099/getproject/dtoboconvert
2. 调用方式:GET
3. 请求方式:HTTP
4. 请求参数:无
5. 返回结果:success
6. 特殊说明:基于plantUML.bo 派生类的代码生成
    1. springboot项目不支持
    2. 在dubbo项目存储于dubbo-core子模块
    3. 在cola项目存储于cola-app子模块中


##### 20. 生成voboconvert
1. 请求地址:http://127.0.0.1:8099/getproject/voboconvert
2. 调用方式:GET
3. 请求方式:HTTP
4. 请求参数:无
5. 返回结果:success
6. 特殊说明:基于plantUML.bo 派生类的代码生成
    1. 在springboot项目是单模块存储,
    2. dubbo项目不支持
    3. 在cola项目存储于cola-adapter子模块中

##### 21. 生成doboconvert
1. 请求地址:http://127.0.0.1:8099/getproject/doboconvert
2. 调用方式:GET
3. 请求方式:HTTP
4. 请求参数:无
5. 返回结果:success
6. 特殊说明:基于数据库表和plantuml扩展key的代码生成
    1. 在springboot项目是单模块存储,
    2. 在dubbo项目存储于dubbo-core子模块
    3. 在cola项目存储于cola-infrast子模块中


##### 22. 生成erpicture
1. 请求地址:http://127.0.0.1:8099/getproject/erpicture
2. 调用方式:GET
3. 请求方式:HTTP
4. 请求参数:无
5. 返回结果:success
6. 特殊说明:基于数据库表生成，统一存储于resources/erpicture目录下
    1. 在springboot项目是单模块存储,
    2. 在dubbo项目存储于dubbo-core子模块
    3. 在cola项目存储于cola-infrast子模块中



#### 二、整体一站式的代码生成接口
##### 1. 极简模式下生成所有代码
1. 请求地址:http://127.0.0.1:8099/makeall
2. 调用方式:GET
3. 请求方式:HTTP
4. 请求参数:无
5. 返回结果:success
6. 特殊说明:

##### 2. 极速模式下生成所有代码
1. 请求地址:http://127.0.0.1:8099/makeallfast
2. 调用方式:GET
3. 请求方式:HTTP
4. 请求参数:无
5. 返回结果:success
6. 特殊说明:此接口只支持sprinboot项目下的代码生成


##### 3.生成指定的表对应的项目代码
1. 请求地址:http://127.0.0.1:8099/makemodules
2. 调用方式:GET
3. 请求方式:HTTP
4. 请求参数:tableNames:多个表的表名
5. 返回结果:success
6. 特殊说明:



##### 4.生成指定的表对应的项目代码--极速模式
1. 请求地址:http://127.0.0.1:8099/makemodulesfast
2. 调用方式:GET
3. 请求方式:HTTP
4. 请求参数:tableNames:多个表的表名
5. 返回结果:success
6. 特殊说明:


##### 5.基于plantUML类图文档生成基于DDD的模块代码
1. 请求地址:http://127.0.0.1:8099/makeddd
2. 调用方式:GET
3. 请求方式:HTTP
4. 请求参数:无
5. 返回结果:success
6. 特殊说明:该接口不依赖于数据库配置,只生成基于plantUML类图的ddd代码，
    代码存储于dynamic-ddd模块中
    
#### 三、极速模式下的代码生成接口   
说明:极速模式下的代码生成目前只适用于springboot的项目工程。
新版本的迭代会逐步支持dubbo,cola,dynamic的极速模式。
