#基于PlantUML的领域文档使用说明

## 说明
由于CodeMaker会依赖PLANTUML文档进行代码构建，所以开发者可以把PLANTUML文档
当作代码绘制的蓝图或者画板,来构建代码元素和代码调用内容。

这里如果需要可视化文档的话建议安装一下相关idea或者eclipse插件,由于大型plantUML
文档可能会很大会比较占用内存,所以熟悉plantUML语法的话可以先在文本编辑器上编辑好再
复制到plantUML文档中.

在/ddd-plantuml/infosys-auth/auth-domain.puml等plantUML文件中
只使用plantUML的一些少量关键字来构建,因为不是专门解析plantUML的工具,所以这里就自己做了解析,同时按照
低代码的场景来在BO类中扩展了很多key在下面的列表中将重点介绍一些这些key的使用说明。
## 扩展key使用说明
#### tableKey
用来标示该BO(领域实体)关联的表名
#### controllerKey
用来标示该BO扩展出来的controllerAPI,可以多个如读写分离
#### dtoKeyList
用来标示该BO扩展出来的DTO
#### voKeyList
用来标示该BO扩展出来的VO
#### queryDtoKey
用来标示该BO扩展出来的查询DTO对象
#### queryVoKey
用来标示该BO扩展出来的查询VO对象
#### invokeFileKey
用来标示该BO对应的调用时序图文档,可以多个
#### contextKey
用来标示BO或者接口所属的上下文,支持按上下文分包
