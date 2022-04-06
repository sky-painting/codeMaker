package com.tianhua.codemaker.app.springboot;

import com.tianhua.codemaker.app.IWriteFileService;
import com.tianhua.codemaker.app.WriteService;
import com.tianhua.codemaker.bean.ClassContentBean;
import com.tianhua.codemaker.bean.WriteContentBean;
import com.tianhua.codemaker.config.ProjectTemplateSpringbootConfig;
import com.tianhua.codemaker.enums.TemplateFileEnum;
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
@Component(value = "springBootApiDocWriteService")
public class SpringBootApiDocWriteServiceImpl extends WriteService implements IWriteFileService {

    @Autowired
    private ProjectTemplateSpringbootConfig projectTemplateSpringbootConfig;

    @Override
    public void writeContent(WriteContentBean writeContentBean) {
        writeApiDoc(writeContentBean.getContent(),writeContentBean.getHumpClassName());
        //写sql文件
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.SQL.getTempFileName())){
            ClassContentBean classContentBean = writeContentBean.buildClassContentBean(projectTemplateSpringbootConfig.getModulePath());
            writeSql(classContentBean);
        }
    }

}
