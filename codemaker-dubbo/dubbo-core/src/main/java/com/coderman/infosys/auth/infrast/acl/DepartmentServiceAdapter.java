package com.coderman.infosys.auth.infrast.acl;

import java.util.List;
import com.coderman.infosys.auth.infrast.acl.req.dto.DepartmentQueryDTO;
import com.coderman.infosys.auth.infrast.acl.res.dto.DepartmentResponseDTO;


/**
 * @Description:部门查询服务适配器接口
 * @Author:shenshuai
 * @CreateTime:2021-11-07 08:18:28
 * @version v1.0
 */
public interface DepartmentServiceAdapter{
    /**
     *
     * @Description:查询所有有效的部门
     * @return List<DepartmentResponseDTO>
     */
     List<DepartmentResponseDTO> queryAllDepartment(DepartmentQueryDTO queryDto);
    /**
     *
     * @Description:查询子部门
     * @return List<DepartmentResponseDTO>
     */
     List<DepartmentResponseDTO> queryDepartmentsList(Long pId);
}