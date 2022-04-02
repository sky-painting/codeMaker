package com.lightsnail.app.user.crm.core.mapper;

import java.util.List;
import com.lightsnail.app.user.crm.core.entity.RoomPublishEntity;


/**
 * @Description:代理人-房源发布表mapperDAO接口
 * @Author:shenshuai
 * @CreateTime:2020-11-17 00:02:53
 * @version v1.0
 */
public interface RoomPublishMapper{

	/**
	 * 
	 * @Title: save 
	 * @Description:新增或修改 
	 * @author: 
	 * @param @param roomPublishEntity
	 * @return int
	 * @throws
	 */
	public int insert(RoomPublishEntity roomPublishEntity);

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
	 * @return ResultDataDto<RoomPublishEntity>    返回类型
	 * @throws
	 */
	public RoomPublishEntity getById(Long id);

	/**
	 * 
	 * @Title: getAll 
	 * @Description:查询所有数据 
	 * @author: 
	 * @return List<RoomPublishEntity    返回类型
	 * @throws
	 */
	public List<RoomPublishEntity>  getAll();

	/**
	*
	* @Title: update
	* @Description:新增或修改
	* @author:
	* @param @param roomPublishEntity
	* @return int
	* @throws
	*/
	public int update(RoomPublishEntity roomPublishEntity);
	
}