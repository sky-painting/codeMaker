package com.lightsnail.schoolmanager.core.service;

import java.util.List;

import com.lightsnail.schoolmanager.core.model.bo.AttractInvestmentBO;

/**
 * @Description:招商服务注册表Service接口
 * @Author:fanchunshuai
 * @CreateTime:2021-06-25 23:24:09
 * @version v1.0
 */
public interface AttractInvestmentService{

	/**
	 *
	 * @Title: save
	 * @Description:新增
	 * @author:
	 * @param @param dto
	 * @param @param userId
	 * @return AttractInvestmentBO    返回类型
	 * @throws
	 */
	public AttractInvestmentBO save(AttractInvestmentBO attractInvestmentBO) throws Exception;

	/**
	 * 
	 * @Title: delete 
	 * @Description: 通过id删除数据
	 * @author: 
	 * @param @param id
	 * @return ResultDto    返回类型 
	 * @throws
	 */
	public Boolean delete(Long id);

	/**
	 * 
	 * @Title: getById 
	 * @Description: 通过id查询
	 * @author: 
	 * @param @param id
	 * @return AttractInvestmentBO
	 * @throws
	 */
	public AttractInvestmentBO getById(Long id) throws Exception;

	/**
	 * 
	 * @Title: getAll 
	 * @Description:查询所有数据 
	 * @author: 
	 * @return List<AttractInvestmentBO>
	 * @throws
	 */
	public List<AttractInvestmentBO> getAll() throws Exception;

	/**
	*
	* @Title: update
	* @Description:修改
	* @author:
	* @param @param attractInvestmentBO
	* @return AttractInvestmentBO
	* @throws
	*/
	public AttractInvestmentBO update(AttractInvestmentBO attractInvestmentBO) throws Exception;

}