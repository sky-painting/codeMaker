package com.lightsnail.app.user.crm.core.mapper;

import java.util.List;
import com.lightsnail.app.user.crm.core.entity.RoomLandlordEntity;


/**
 * @Description:房东表mapperDAO接口
 * @Author:fanchunshuai
 * @CreateTime:2020-11-17 00:02:53
 * @version v1.0
 */
public interface RoomLandlordMapper{

	/**
	 * 
	 * @Title: save 
	 * @Description:新增或修改 
	 * @author: 
	 * @param @param roomLandlordEntity
	 * @return int
	 * @throws
	 */
	public int insert(RoomLandlordEntity roomLandlordEntity);

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
	 * @return ResultDataDto<RoomLandlordEntity>    返回类型
	 * @throws
	 */
	public RoomLandlordEntity getById(Long id);

	/**
	 * 
	 * @Title: getAll 
	 * @Description:查询所有数据 
	 * @author: 
	 * @return List<RoomLandlordEntity    返回类型
	 * @throws
	 */
	public List<RoomLandlordEntity>  getAll();

	/**
	*
	* @Title: update
	* @Description:新增或修改
	* @author:
	* @param @param roomLandlordEntity
	* @return int
	* @throws
	*/
	public int update(RoomLandlordEntity roomLandlordEntity);
	
}