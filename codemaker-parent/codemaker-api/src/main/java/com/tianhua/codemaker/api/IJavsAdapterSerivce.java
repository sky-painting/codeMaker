package com.tianhua.codemaker.api;

import com.tianhua.codemaker.bean.plantuml.PlantUmlContextBean;

/**
 * Description:
 * date: 2022/3/10
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public interface IJavsAdapterSerivce {
    /**
     * codeMaker适配javs翻译引擎
     * @param plantUmlContextBean
     * @param javsScriptProjectPath
     */
    void translateJavsScript(PlantUmlContextBean plantUmlContextBean, String javsScriptProjectPath);

    /**
     * 基于plantuml文档生成javs脚本项目
     * @param plantUmlContextBean
     * @param javsScriptProjectPath
     */
    void generateJavsScriptProject(PlantUmlContextBean plantUmlContextBean,String javsScriptProjectPath);
}
