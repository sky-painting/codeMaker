package com.tianhua.codemaker.app.dubbo;

import com.tianhua.codemaker.app.FunctionCodeAppWriteService;
import com.tianhua.codemaker.app.WriteService;
import com.tianhua.codemaker.bean.ClassContentBean;
import com.tianhua.codemaker.bean.GlobalConstant;
import com.tianhua.codemaker.bean.WriteContentBean;
import com.tianhua.codemaker.config.ProjectTemplateDubboConfig;
import com.tianhua.codemaker.app.IWriteFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Description:
 * date: 2021/6/18
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 *
 * 写api模块服务
 */
@Component(value = "dubboApiWriteFileService")
public class DubboApiWriteServiceImpl extends WriteService implements IWriteFileService {

    @Autowired
    private ProjectTemplateDubboConfig projectTemplateDubboConfig;

    @Autowired
    private FunctionCodeAppWriteService functionCodeAppWriteService;

    private static final String moduleCode = "api";

    @Override
    public void writeContent(WriteContentBean writeContentBean) {
        ClassContentBean classContentBean = writeContentBean.buildClassContentBean(moduleCode, projectTemplateDubboConfig.getModuleApiPath());
        functionCodeAppWriteService.write(writeContentBean.getTemplateName(),moduleCode,classContentBean);

        //写自定义工具类
        if(writeContentBean.getTemplateName().equals(GlobalConstant.SINGLE_CLASS_COMMON)){
            writeRoute(classContentBean);
        }
    }
}
