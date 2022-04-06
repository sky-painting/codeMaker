package com.lightsnail.app.user.crm.core.mapper;

import java.util.List;
import com.lightsnail.app.user.crm.core.entity.RoomSubscribeEntity;


/**
 * @Description:代理人-预约看房记录表mapperDAO接口
 * @Author:shenshuai
 * @CreateTime:2020-11-17 00:02:53
 * @version v1.0
 */
public interface RoomSubscribeMapper{

	/**
	 * 
	 * @Title: save 
	 * @Description:新增或修改 
	 * @author: 
	 * @param @param roomSubscribeEntity
	 * @return int
	 * @throws
	 */
	public int insert(RoomSubscribeEntity roomSubscribeEntity);

	/**
	 * 
	 * @Title: delete 
	 * @Description: 通过id删除数据
	 * @author: 
	 * @param @param id
	 * @return int
	 * @throws
	 */
	public int deleteById(Long id);

	/**
	 * 
	 * @Title: getById 
	 * @Description: 通过id查询
	 * @author: 
	 * @param @param id
	 * @return ResultDataDto<RoomSubscribeEntity>    返回类型
	 * @throws
	 */
	public RoomSubscribeEntity getById(Long id);

	/**
	 * 
	 * @Title: getAll 
	 * @Description:查询所有数据 
	 * @author: 
	 * @return List<RoomSubscribeEntity    返回类型
	 * @throws
	 */
	public List<RoomSubscribeEntity>  getAll();

	/**
	*
	* @Title: update
	* @Description:新增或修改
	* @author:
	* @param @param roomSubscribeEntity
	* @return int
	* @throws
	*/
	public int update(RoomSubscribeEntity roomSubscribeEntity);
	
}