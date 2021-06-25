package com.lightsnail.schoolmanager.core.service;

import java.util.List;

import com.lightsnail.schoolmanager.core.model.bo.RoomLandlordBO;

/**
 * @Description:房东表Service接口
 * @Author:fanchunshuai
 * @CreateTime:2021-06-25 23:24:09
 * @version v1.0
 */
public interface RoomLandlordService{

	/**
	 *
	 * @Title: save
	 * @Description:新增
	 * @author:
	 * @param @param dto
	 * @param @param userId
	 * @return RoomLandlordBO    返回类型
	 * @throws
	 */
	public RoomLandlordBO save(RoomLandlordBO roomLandlordBO) throws Exception;

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
	 * @return RoomLandlordBO
	 * @throws
	 */
	public RoomLandlordBO getById(Long id) throws Exception;

	/**
	 * 
	 * @Title: getAll 
	 * @Description:查询所有数据 
	 * @author: 
	 * @return List<RoomLandlordBO>
	 * @throws
	 */
	public List<RoomLandlordBO> getAll() throws Exception;

	/**
	*
	* @Title: update
	* @Description:修改
	* @author:
	* @param @param roomLandlordBO
	* @return RoomLandlordBO
	* @throws
	*/
	public RoomLandlordBO update(RoomLandlordBO roomLandlordBO) throws Exception;

}