package com.tianhua.codemaker.app.dubbo;

import com.tianhua.codemaker.app.IWriteFileService;
import com.tianhua.codemaker.app.WriteService;
import com.tianhua.codemaker.bean.ClassContentBean;
import com.tianhua.codemaker.bean.WriteContentBean;
import com.tianhua.codemaker.config.ProjectTemplateDubboConfig;
import com.tianhua.codemaker.enums.TemplateFileEnum;
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
@Component(value = "dubboApiDocWriteFileService")
public class DubboApiDocWriteServiceImpl extends WriteService implements IWriteFileService {
    @Autowired
    private ProjectTemplateDubboConfig projectTemplateDubboConfig;

    @Override
    public void writeContent(WriteContentBean writeContentBean) {
        writeApiDoc(writeContentBean.getContent(),writeContentBean.getHumpClassName());
        //写pom文件
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.PARENT_POM.getTempFileName())){
            ClassContentBean classContentBean = writeContentBean.buildClassContentBean(projectTemplateDubboConfig.getModuleParentPath());
            writePom(classContentBean);
        }

        //写sql文件
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.SQL.getTempFileName())){
            ClassContentBean classContentBean = writeContentBean.buildClassContentBean(projectTemplateDubboConfig.getModuleParentPath());
            writeSql(classContentBean);
        }
    }
}
