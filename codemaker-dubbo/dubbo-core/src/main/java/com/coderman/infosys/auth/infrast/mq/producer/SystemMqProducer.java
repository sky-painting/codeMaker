package com.coderman.infosys.auth.infrast.mq.producer;

import com.coderman.infosys.auth.domain.event.MenuChangeEvent;

import org.springframework.stereotype.Service;

 /**
 * @Description:SystemMqProducer类
 * @Author:shenshuai
 * @CreateTime:2021-11-07 08:18:28
 * @version v1.0
 */
@Service
public class SystemMqProducer{


    /**
     *
     * @Description:事务提交后权限变更监
     * @return void
     */
    public void systemChange(MenuChangeEvent event){
        
    }

}