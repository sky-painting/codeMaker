package com.lightsnail.snailapp.usercrm.infrast.acl.impl;

import com.lightsnail.snailapp.usercrm.infrast.acl.res.dto.DepartmentResponseDTO;
import java.util.List;
import com.lightsnail.snailapp.usercrm.infrast.acl.req.dto.DepartmentQueryDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.lightsnail.snailapp.usercrm.infrast.acl.DepartmentServiceAdapter;

/**
 * @Description:部门查询服务适配器接口实现类
 * @Author：fanchunshuai
 * @CreateTime：2021-07-07 00:05:11
 * @version v1.0
 */
@Service
public class DepartmentServiceAdapterImpl implements DepartmentServiceAdapter{

	private  Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
	public List<DepartmentResponseDTO>  queryAllDepartment(DepartmentQueryDTO queryDto){
        return null;
    }

    @Override
	public List<DepartmentResponseDTO>  queryDepartmentsList(Long pId){
        return null;
    }

}