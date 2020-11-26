package com.lightsnail.app.user.crm.core.mapper;

import java.util.List;
import com.lightsnail.app.user.crm.core.entity.LandlordRoomRentRecordEntity;


/**
 * @Description:房源出租记录表mapperDAO接口
 * @Author:fanchunshuai
 * @CreateTime:2020-11-17 00:02:54
 * @version v1.0
 */
public interface LandlordRoomRentRecordMapper{

	/**
	 * 
	 * @Title: save 
	 * @Description:新增或修改 
	 * @author: 
	 * @param @param landlordRoomRentRecordEntity
	 * @return int
	 * @throws
	 */
	public int insert(LandlordRoomRentRecordEntity landlordRoomRentRecordEntity);

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
	 * @return ResultDataDto<LandlordRoomRentRecordEntity>    返回类型
	 * @throws
	 */
	public LandlordRoomRentRecordEntity getById(Long id);

	/**
	 * 
	 * @Title: getAll 
	 * @Description:查询所有数据 
	 * @author: 
	 * @return List<LandlordRoomRentRecordEntity    返回类型
	 * @throws
	 */
	public List<LandlordRoomRentRecordEntity>  getAll();

	/**
	*
	* @Title: update
	* @Description:新增或修改
	* @author:
	* @param @param landlordRoomRentRecordEntity
	* @return int
	* @throws
	*/
	public int update(LandlordRoomRentRecordEntity landlordRoomRentRecordEntity);
	
}