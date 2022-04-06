package com.lightsnail.app.user.crm.core.service;

import java.util.List;
import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import com.lightsnail.app.user.crm.core.vo.LandlordRoomSourceVO;

/**
 * @Description:房东-房源表Service接口
 * @Author:shenshuai
 * @CreateTime:2020-11-17 00:02:53
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
	 * @return ResultDto    返回类型
	 * @throws
	 */
	public ResultDto save(LandlordRoomSourceVO landlordRoomSourceVo) throws Exception;

	/**
	 * 
	 * @Title: delete 
	 * @Description: 通过id删除数据
	 * @author: 
	 * @param @param id
	 * @return ResultDto    返回类型 
	 * @throws
	 */
	public ResultDto delete(Long id);

	/**
	 * 
	 * @Title: getById 
	 * @Description: 通过id查询
	 * @author: 
	 * @param @param id
	 * @return ResultDataDto<LandlordRoomSourceVO>
	 * @throws
	 */
	public ResultDataDto<LandlordRoomSourceVO> getById(Long id) throws Exception;

	/**
	 * 
	 * @Title: getAll 
	 * @Description:查询所有数据 
	 * @author: 
	 * @return ResultDataDto<List<LandlordRoomSourceVO>>
	 * @throws
	 */
	public ResultDataDto<List<LandlordRoomSourceVO>> getAll() throws Exception;

	/**
	*
	* @Title: update
	* @Description:修改
	* @author:
	* @param @param landlordRoomSourceEntity
	* @return int
	* @throws
	*/
	public ResultDto update(LandlordRoomSourceVO landlordRoomSourceVo) throws Exception;

}