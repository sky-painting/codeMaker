package com.coderman.codemaker.app.springcloud;

import com.coderman.codemaker.app.IWriteFileService;
import com.coderman.codemaker.app.WriteService;
import com.coderman.codemaker.bean.WriteContentBean;
import org.springframework.stereotype.Component;

/**
 * Description:
 * date: 2021/11/29
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component(value = "sCFeignApiDocWriteService")
public class SCFeignApiDocWriteServiceImpl extends WriteService implements IWriteFileService {

    @Override
    public void writeContent(WriteContentBean writeContentBean) {
        writeApiDoc(writeContentBean.getContent(),writeContentBean.getHumpClassName());
    }
}
