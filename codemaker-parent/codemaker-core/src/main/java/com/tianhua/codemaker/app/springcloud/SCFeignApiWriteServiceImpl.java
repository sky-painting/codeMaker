package com.tianhua.codemaker.app.springcloud;

import com.tianhua.codemaker.app.FunctionCodeAppWriteService;
import com.tianhua.codemaker.app.IWriteFileService;
import com.tianhua.codemaker.app.WriteService;
import com.tianhua.codemaker.bean.ClassContentBean;
import com.tianhua.codemaker.bean.GlobalConstant;
import com.tianhua.codemaker.bean.WriteContentBean;
import com.tianhua.codemaker.config.ProjectTemplateSpringCloudConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Description:
 * date: 2021/11/29
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */

@Component(value = "sCFeignApiWriteService")
public class SCFeignApiWriteServiceImpl  extends WriteService implements IWriteFileService {
    @Autowired
    private ProjectTemplateSpringCloudConfig projectTemplateSpringCloudConfig;

    @Autowired
    private FunctionCodeAppWriteService functionCodeAppWriteService;

    private static final String moduleCode = "api";

    @Override
    public void writeContent(WriteContentBean writeContentBean) {
        ClassContentBean classContentBean = writeContentBean.buildClassContentBean(moduleCode, projectTemplateSpringCloudConfig.getModuleFeginApiPath());
        functionCodeAppWriteService.write(writeContentBean.getTemplateName(),moduleCode,classContentBean);
        //写自定义工具类
        if(writeContentBean.getTemplateName().equals(GlobalConstant.SINGLE_CLASS_COMMON)){
            writeRoute(classContentBean);
        }
    }
}
