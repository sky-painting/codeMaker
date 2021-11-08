package com.coderman.infosys.auth.domain.bo;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

 /**
 * @Description:操作时间信息类
 * @Author:shenshuai
 * @CreateTime:2021-11-06 23:38:06
 * @version v1.0
 */
@Data
@ToString
public class OperateDateBO {


    /**
     * 修改时
     */
    public Date dateUpdate;

    /**
     * 修改
     */
    public Long updateUserId;


}