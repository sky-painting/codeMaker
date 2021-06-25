package com.lightsnail.schoolmanager.core.service;

import java.util.List;

import com.lightsnail.schoolmanager.core.model.bo.RealRoomSourceBO;

/**
 * @Description:房东-房源表Service接口
 * @Author:fanchunshuai
 * @CreateTime:2021-06-25 23:24:09
 * @version v1.0
 */
public interface RealRoomSourceService{

	/**
	 *
	 * @Title: save
	 * @Description:新增
	 * @author:
	 * @param @param dto
	 * @param @param userId
	 * @return RealRoomSourceBO    返回类型
	 * @throws
	 */
	public RealRoomSourceBO save(RealRoomSourceBO realRoomSourceBO) throws Exception;

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
	 * @return RealRoomSourceBO
	 * @throws
	 */
	public RealRoomSourceBO getById(Long id) throws Exception;

	/**
	 * 
	 * @Title: getAll 
	 * @Description:查询所有数据 
	 * @author: 
	 * @return List<RealRoomSourceBO>
	 * @throws
	 */
	public List<RealRoomSourceBO> getAll() throws Exception;

	/**
	*
	* @Title: update
	* @Description:修改
	* @author:
	* @param @param realRoomSourceBO
	* @return RealRoomSourceBO
	* @throws
	*/
	public RealRoomSourceBO update(RealRoomSourceBO realRoomSourceBO) throws Exception;

}