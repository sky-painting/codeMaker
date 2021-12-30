package com.coderman.codemaker.app.springboot;

import com.coderman.codemaker.app.CommonWriteService;
import com.coderman.codemaker.app.IWriteFileService;
import com.coderman.codemaker.app.WriteService;
import com.coderman.codemaker.bean.WriteContentBean;
import com.coderman.codemaker.config.AppServiceConfig;
import com.coderman.codemaker.config.ProjectTemplateSpringbootConfig;
import com.coderman.codemaker.service.dberpicture.DBErPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Description:
 * date: 2021/6/22
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component(value = "springBootApiDocWriteService")
public class SpringBootApiDocWriteServiceImpl extends WriteService implements IWriteFileService {

    @Override
    public void writeContent(WriteContentBean writeContentBean) {
        writeApiDoc(writeContentBean.getContent(),writeContentBean.getHumpClassName());
    }

}
