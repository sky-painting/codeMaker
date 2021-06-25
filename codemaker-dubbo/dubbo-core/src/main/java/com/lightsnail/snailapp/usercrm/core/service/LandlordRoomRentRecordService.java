package com.lightsnail.schoolmanager.core.service;

import java.util.List;

import com.lightsnail.schoolmanager.core.model.bo.LandlordRoomRentRecordBO;

/**
 * @Description:房源出租记录表Service接口
 * @Author:fanchunshuai
 * @CreateTime:2021-06-25 23:24:09
 * @version v1.0
 */
public interface LandlordRoomRentRecordService{

	/**
	 *
	 * @Title: save
	 * @Description:新增
	 * @author:
	 * @param @param dto
	 * @param @param userId
	 * @return LandlordRoomRentRecordBO    返回类型
	 * @throws
	 */
	public LandlordRoomRentRecordBO save(LandlordRoomRentRecordBO landlordRoomRentRecordBO) throws Exception;

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
	 * @return LandlordRoomRentRecordBO
	 * @throws
	 */
	public LandlordRoomRentRecordBO getById(Long id) throws Exception;

	/**
	 * 
	 * @Title: getAll 
	 * @Description:查询所有数据 
	 * @author: 
	 * @return List<LandlordRoomRentRecordBO>
	 * @throws
	 */
	public List<LandlordRoomRentRecordBO> getAll() throws Exception;

	/**
	*
	* @Title: update
	* @Description:修改
	* @author:
	* @param @param landlordRoomRentRecordBO
	* @return LandlordRoomRentRecordBO
	* @throws
	*/
	public LandlordRoomRentRecordBO update(LandlordRoomRentRecordBO landlordRoomRentRecordBO) throws Exception;

}