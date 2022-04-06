package com.tianhua.codemaker.app.cola;

import com.tianhua.codemaker.app.FunctionCodeAppWriteService;
import com.tianhua.codemaker.app.IWriteFileService;
import com.tianhua.codemaker.app.WriteService;
import com.tianhua.codemaker.bean.ClassContentBean;
import com.tianhua.codemaker.bean.WriteContentBean;
import com.tianhua.codemaker.config.AppServiceConfig;
import com.tianhua.codemaker.config.ProjectTemplateColaConfig;
import com.tianhua.codemaker.enums.TemplateFileEnum;
import com.tianhua.codemaker.utils.Constant;
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

@Component(value = "colaFeignApiWriteService")
public class ColaFeignApiWriteServiceImpl extends WriteService implements IWriteFileService {
    @Autowired
    private ProjectTemplateColaConfig projectTemplateColaConfig;

    @Autowired
    private AppServiceConfig appServiceConfig;

    @Autowired
    private FunctionCodeAppWriteService functionCodeAppWriteService;

    private static final String moduleCode = "feignapi";

    @Override
    public void writeContent(WriteContentBean writeContentBean) {
        ClassContentBean classContentBean = writeContentBean.buildClassContentBean(moduleCode, projectTemplateColaConfig.getModuleFeignApiPath());
        functionCodeAppWriteService.write(writeContentBean.getTemplateName(),moduleCode,classContentBean);

    }
}
