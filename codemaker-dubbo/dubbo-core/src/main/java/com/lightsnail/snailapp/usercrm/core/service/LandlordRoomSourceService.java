package com.lightsnail.schoolmanager.core.service;

import java.util.List;

import com.lightsnail.schoolmanager.core.model.bo.LandlordRoomSourceBO;

/**
 * @Description:房东-房源表Service接口
 * @Author:fanchunshuai
 * @CreateTime:2021-06-25 23:24:09
 * @version v1.0
 */
public interface LandlordRoomSourceService{

	/**
	 *
	 * @Title: save
	 * @Description:新增
	 * @author:
	 * @param @param dto
	 * @param @param userId
	 * @return LandlordRoomSourceBO    返回类型
	 * @throws
	 */
	public LandlordRoomSourceBO save(LandlordRoomSourceBO landlordRoomSourceBO) throws Exception;

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
	 * @return LandlordRoomSourceBO
	 * @throws
	 */
	public LandlordRoomSourceBO getById(Long id) throws Exception;

	/**
	 * 
	 * @Title: getAll 
	 * @Description:查询所有数据 
	 * @author: 
	 * @return List<LandlordRoomSourceBO>
	 * @throws
	 */
	public List<LandlordRoomSourceBO> getAll() throws Exception;

	/**
	*
	* @Title: update
	* @Description:修改
	* @author:
	* @param @param landlordRoomSourceBO
	* @return LandlordRoomSourceBO
	* @throws
	*/
	public LandlordRoomSourceBO update(LandlordRoomSourceBO landlordRoomSourceBO) throws Exception;

}