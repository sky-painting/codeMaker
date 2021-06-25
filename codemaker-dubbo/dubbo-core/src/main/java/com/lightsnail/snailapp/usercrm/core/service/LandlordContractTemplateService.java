package com.lightsnail.schoolmanager.core.service;

import java.util.List;

import com.lightsnail.schoolmanager.core.model.bo.LandlordContractTemplateBO;

/**
 * @Description:房东合同模板表Service接口
 * @Author:fanchunshuai
 * @CreateTime:2021-06-25 23:24:09
 * @version v1.0
 */
public interface LandlordContractTemplateService{

	/**
	 *
	 * @Title: save
	 * @Description:新增
	 * @author:
	 * @param @param dto
	 * @param @param userId
	 * @return LandlordContractTemplateBO    返回类型
	 * @throws
	 */
	public LandlordContractTemplateBO save(LandlordContractTemplateBO landlordContractTemplateBO) throws Exception;

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
	 * @return LandlordContractTemplateBO
	 * @throws
	 */
	public LandlordContractTemplateBO getById(Long id) throws Exception;

	/**
	 * 
	 * @Title: getAll 
	 * @Description:查询所有数据 
	 * @author: 
	 * @return List<LandlordContractTemplateBO>
	 * @throws
	 */
	public List<LandlordContractTemplateBO> getAll() throws Exception;

	/**
	*
	* @Title: update
	* @Description:修改
	* @author:
	* @param @param landlordContractTemplateBO
	* @return LandlordContractTemplateBO
	* @throws
	*/
	public LandlordContractTemplateBO update(LandlordContractTemplateBO landlordContractTemplateBO) throws Exception;

}