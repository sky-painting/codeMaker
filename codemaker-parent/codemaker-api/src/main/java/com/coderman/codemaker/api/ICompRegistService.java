package com.coderman.codemaker.api;

import com.coderman.codemaker.bean.component.ComponentContextBean;

/**
 * Description:组件注册服务接口，这里主要用来将相对独立的工具类注册到组件中
 * 整体的独立类当作一个组件看待，独立工具类配置在/component/single-class文件夹
 * 中，一个独立类一个配置。
 *
 * 由于独立类的注入扫描比较简单，这里相当于一个默认的实现，不需要用户二次开发
 * 用户只需要配置即可将独立的工具类应用在调用时序图文档里，并在生成的代码中被引用到
 *
 * date: 2021/12/24
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public interface ICompRegistService {
    /**
     * 将独立工具类的配置列表扫描并注册为组件
     * @return
     */
    ComponentContextBean registSingleClass();
}
