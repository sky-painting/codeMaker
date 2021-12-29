package com.coderman.codemaker.app.cola;

import com.coderman.codemaker.app.IWriteFileService;
import com.coderman.codemaker.app.WriteService;
import com.coderman.codemaker.bean.WriteContentBean;
import com.coderman.codemaker.config.AppServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Description:
 * date: 2021/7/6
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component(value = "colaApiDocWriteService")
public class ColaApiDocWriteServiceImpl extends WriteService implements IWriteFileService {

    @Autowired
    private AppServiceConfig appServiceConfig;

    @Override
    public void writeContent(WriteContentBean writeContentBean) {
        writeApiDoc(writeContentBean.getContent(),writeContentBean.getHumpClassName());
    }


}
