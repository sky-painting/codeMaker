#基于数据库模式+动态DDD模式+动态调用时序(推荐)

## 说明
这里是基于 
[基于PlantUML 的动态DDD+数据库模式](USEDBYDBANDDDD.md)
做的升级, 由于动态DDD模式需要依赖plantUML类图文档，因此基于这个模式加上plantUML代码调用时序图文档即可
变成数据库模式+动态DDD模式+动态调用时序文档模式的复合模式或者混合模式。

## 使用步骤
使用步骤与[基于PlantUML 的动态DDD+数据库模式](USEDBYDBANDDDD.md)一样，这里简单说一下如何加调用时序文档
通过对PlantUML领域文档中的实体增加扩展key:invokeFileKey来扩展该实体对应的调用时序文档，可以有多个。

