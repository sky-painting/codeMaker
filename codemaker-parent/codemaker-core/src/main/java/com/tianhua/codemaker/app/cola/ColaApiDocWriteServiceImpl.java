package com.tianhua.codemaker.app.cola;

import com.tianhua.codemaker.app.IWriteFileService;
import com.tianhua.codemaker.app.WriteService;
import com.tianhua.codemaker.bean.ClassContentBean;
import com.tianhua.codemaker.bean.WriteContentBean;
import com.tianhua.codemaker.config.ProjectTemplateColaConfig;
import com.tianhua.codemaker.enums.TemplateFileEnum;
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
@Component(value = "colaApiDocWriteService")
public class ColaApiDocWriteServiceImpl extends WriteService implements IWriteFileService {
    @Autowired
    private ProjectTemplateColaConfig projectTemplateColaConfig;


    @Override
    public void writeContent(WriteContentBean writeContentBean) {

        writeApiDoc(writeContentBean.getContent(),writeContentBean.getHumpClassName());

        //写pom文件
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.PARENT_POM.getTempFileName())){
            ClassContentBean classContentBean = writeContentBean.buildClassContentBean(projectTemplateColaConfig.getModuleParentPath());
            writePom(classContentBean);
        }

        //写sql文件
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.SQL.getTempFileName())){
            ClassContentBean classContentBean = writeContentBean.buildClassContentBean(projectTemplateColaConfig.getModuleParentPath());
            writeSql(classContentBean);
        }
    }


}
