package com.lightsnail.schoolmanager.core.service;

import java.util.List;

import com.lightsnail.schoolmanager.core.model.bo.RoomSubscribeBO;

/**
 * @Description:代理人-预约看房记录表Service接口
 * @Author:fanchunshuai
 * @CreateTime:2021-06-25 23:24:09
 * @version v1.0
 */
public interface RoomSubscribeService{

	/**
	 *
	 * @Title: save
	 * @Description:新增
	 * @author:
	 * @param @param dto
	 * @param @param userId
	 * @return RoomSubscribeBO    返回类型
	 * @throws
	 */
	public RoomSubscribeBO save(RoomSubscribeBO roomSubscribeBO) throws Exception;

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
	 * @return RoomSubscribeBO
	 * @throws
	 */
	public RoomSubscribeBO getById(Long id) throws Exception;

	/**
	 * 
	 * @Title: getAll 
	 * @Description:查询所有数据 
	 * @author: 
	 * @return List<RoomSubscribeBO>
	 * @throws
	 */
	public List<RoomSubscribeBO> getAll() throws Exception;

	/**
	*
	* @Title: update
	* @Description:修改
	* @author:
	* @param @param roomSubscribeBO
	* @return RoomSubscribeBO
	* @throws
	*/
	public RoomSubscribeBO update(RoomSubscribeBO roomSubscribeBO) throws Exception;

}