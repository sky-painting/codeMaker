package com.coderman.infosys.auth.infrast.acl.impl;

import java.util.List;
import com.coderman.infosys.auth.infrast.acl.req.dto.DepartmentQueryDTO;
import com.coderman.infosys.auth.infrast.acl.DepartmentServiceAdapter;
import com.coderman.infosys.auth.infrast.acl.res.dto.DepartmentResponseDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @Description:接口实现类
 * @Author：
 * @CreateTime：2021-11-07 08:23:49
 * @version v1.0
 */
@Service
public class DepartmentServiceAdapterImpl  implements DepartmentServiceAdapter{

	private  Logger logger = LoggerFactory.getLogger(this.getClass());



    @Override
	public List<DepartmentResponseDTO> queryAllDepartment(DepartmentQueryDTO queryDto){

        return null;
    }

    @Override
	public List<DepartmentResponseDTO> queryDepartmentsList(Long pId){

        return null;
    }

}