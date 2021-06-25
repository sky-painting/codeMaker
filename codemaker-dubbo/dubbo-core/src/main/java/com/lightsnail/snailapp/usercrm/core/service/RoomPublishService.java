package com.lightsnail.schoolmanager.core.service;

import java.util.List;

import com.lightsnail.schoolmanager.core.model.bo.RoomPublishBO;

/**
 * @Description:代理人-房源发布表Service接口
 * @Author:fanchunshuai
 * @CreateTime:2021-06-25 23:24:09
 * @version v1.0
 */
public interface RoomPublishService{

	/**
	 *
	 * @Title: save
	 * @Description:新增
	 * @author:
	 * @param @param dto
	 * @param @param userId
	 * @return RoomPublishBO    返回类型
	 * @throws
	 */
	public RoomPublishBO save(RoomPublishBO roomPublishBO) throws Exception;

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
	 * @return RoomPublishBO
	 * @throws
	 */
	public RoomPublishBO getById(Long id) throws Exception;

	/**
	 * 
	 * @Title: getAll 
	 * @Description:查询所有数据 
	 * @author: 
	 * @return List<RoomPublishBO>
	 * @throws
	 */
	public List<RoomPublishBO> getAll() throws Exception;

	/**
	*
	* @Title: update
	* @Description:修改
	* @author:
	* @param @param roomPublishBO
	* @return RoomPublishBO
	* @throws
	*/
	public RoomPublishBO update(RoomPublishBO roomPublishBO) throws Exception;

}