package com.tianhua.codemaker.app.springboot;

import com.tianhua.codemaker.app.FunctionCodeAppWriteService;
import com.tianhua.codemaker.app.WriteService;
import com.tianhua.codemaker.bean.*;
import com.tianhua.codemaker.config.ProjectTemplateSpringbootConfig;
import com.tianhua.codemaker.enums.TemplateFileEnum;
import com.tianhua.codemaker.app.IWriteFileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



/**
 * Description:
 * date: 2021/6/22
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component(value = "springBootWriteService")
public class SpringBootWriteServiceImpl extends WriteService implements IWriteFileService {

    @Autowired
    private ProjectTemplateSpringbootConfig projectTemplateConfig;

    @Autowired
    private FunctionCodeAppWriteService functionCodeAppWriteService;

    private static final String moduleCode = "springboot";

    @Override
    public void writeContent(WriteContentBean writeContentBean) {
        ClassContentBean classContentBean = writeContentBean.buildClassContentBean("", projectTemplateConfig.getModulePath());
        functionCodeAppWriteService.write(writeContentBean.getTemplateName(), moduleCode, classContentBean);
        //写config文件
        if (writeContentBean.getTemplateName().equals(GlobalConstant.CONFIG)) {
            writeConfig(classContentBean);
        }

        //写自定义工具类
        if (writeContentBean.getTemplateName().equals(GlobalConstant.SINGLE_CLASS_COMMON)) {
            writeRoute(classContentBean);
        }

    }
}
