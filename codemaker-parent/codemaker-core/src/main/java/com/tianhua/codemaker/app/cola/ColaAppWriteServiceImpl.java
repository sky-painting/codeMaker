package com.tianhua.codemaker.app.cola;

import com.tianhua.codemaker.app.FunctionCodeAppWriteService;
import com.tianhua.codemaker.app.WriteService;
import com.tianhua.codemaker.bean.ClassContentBean;
import com.tianhua.codemaker.bean.GlobalConstant;
import com.tianhua.codemaker.bean.WriteContentBean;
import com.tianhua.codemaker.config.ProjectTemplateColaConfig;
import com.tianhua.codemaker.enums.TemplateFileEnum;
import com.tianhua.codemaker.app.IWriteFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Description:
 * date: 2021/7/6
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component(value = "colaAppWriteService")
public class ColaAppWriteServiceImpl extends WriteService implements IWriteFileService {

    @Autowired
    private ProjectTemplateColaConfig projectTemplateColaConfig;

    @Autowired
    private FunctionCodeAppWriteService functionCodeAppWriteService;

    private static final String moduleCode = "app";

    @Override
    public void writeContent(WriteContentBean writeContentBean) {

        ClassContentBean classContentBean = writeContentBean.buildClassContentBean(moduleCode, projectTemplateColaConfig.getModuleAppPath());

        functionCodeAppWriteService.write(writeContentBean.getTemplateName(),moduleCode,classContentBean);
        //写自定义工具类
        if(writeContentBean.getTemplateName().equals(GlobalConstant.SINGLE_CLASS_COMMON)){
            writeRoute(classContentBean);
        }

        //写config文件
        if(writeContentBean.getTemplateName().equals(GlobalConstant.CONFIG)){
            writeConfig(classContentBean);
        }
    }
}
