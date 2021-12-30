package com.coderman.codemaker.app.cola;

import com.coderman.codemaker.app.IWriteFileService;
import com.coderman.codemaker.app.WriteService;
import com.coderman.codemaker.bean.ClassContentBean;
import com.coderman.codemaker.bean.GlobalConstant;
import com.coderman.codemaker.bean.WriteContentBean;
import com.coderman.codemaker.config.AppServiceConfig;
import com.coderman.codemaker.config.ProjectTemplateColaConfig;
import com.coderman.codemaker.utils.Constant;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * Description:
 * date: 2021/7/6
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component(value = "colaStartWriteService")
public class ColaStartWriteServiceImpl extends WriteService implements IWriteFileService {
    @Autowired
    private ProjectTemplateColaConfig projectTemplateColaConfig;

    @Autowired
    private AppServiceConfig appServiceConfig;

    @Override
    public void writeContent(WriteContentBean writeContentBean) {
        ClassContentBean classContentBean = writeContentBean.buildClassContentBean(projectTemplateColaConfig.getModuleStartPath());
        //写自定义工具类
        if(writeContentBean.getTemplateName().equals(GlobalConstant.SINGLE_CLASS_COMMON)){
            writeRoute(classContentBean);
        }
    }

}
