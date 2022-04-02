package com.tianhua.codemaker.app.springcloud;

import com.tianhua.codemaker.app.IWriteFileService;
import com.tianhua.codemaker.app.WriteService;
import com.tianhua.codemaker.bean.ClassContentBean;
import com.tianhua.codemaker.bean.WriteContentBean;
import com.tianhua.codemaker.config.ProjectTemplateSpringCloudConfig;
import com.tianhua.codemaker.enums.TemplateFileEnum;
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
@Component(value = "sCFeignApiDocWriteService")
public class SCFeignApiDocWriteServiceImpl extends WriteService implements IWriteFileService {
    @Autowired
    private ProjectTemplateSpringCloudConfig projectTemplateSpringCloudConfig;

    @Override
    public void writeContent(WriteContentBean writeContentBean) {
        writeApiDoc(writeContentBean.getContent(),writeContentBean.getHumpClassName());
        //写pom文件
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.PARENT_POM.getTempFileName())){
            ClassContentBean classContentBean = writeContentBean.buildClassContentBean(projectTemplateSpringCloudConfig.getModuleParentPath());
            writePom(classContentBean);
        }

        //写sql文件
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.SQL.getTempFileName())){
            ClassContentBean classContentBean = writeContentBean.buildClassContentBean(projectTemplateSpringCloudConfig.getModuleParentPath());
            writeSql(classContentBean);
        }
    }
}
