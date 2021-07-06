package com.lightsnail.snailapp.usercrm.infrast.acl;

import com.lightsnail.snailapp.usercrm.infrast.acl.res.dto.DepartmentResponseDTO;
import java.util.List;
import com.lightsnail.snailapp.usercrm.infrast.acl.req.dto.DepartmentQueryDTO;


/**
 * @Description:部门查询服务适配器接口
 * @Author:fanchunshuai
 * @CreateTime:2021-07-06 17:11:46
 * @version v1.0
 */
public interface DepartmentServiceAdapter{
    /**
     *
     * @Description:查询所有有效的部门
     * @return List<DepartmentResponseDTO>
     */
     List<DepartmentResponseDTO>  queryAllDepartment(DepartmentQueryDTO queryDto);
    /**
     *
     * @Description:查询子部门
     * @return List<DepartmentResponseDTO>
     */
     List<DepartmentResponseDTO>  queryDepartmentsList(Long pId);
}