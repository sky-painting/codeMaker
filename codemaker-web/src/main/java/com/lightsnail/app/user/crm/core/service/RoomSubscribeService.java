package com.lightsnail.app.user.crm.core.service;

import java.util.List;
import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import com.lightsnail.app.user.crm.core.vo.RoomSubscribeVO;

/**
 * @Description:代理人-预约看房记录表Service接口
 * @Author:fanchunshuai
 * @CreateTime:2020-11-17 00:02:53
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
	 * @return ResultDto    返回类型
	 * @throws
	 */
	public ResultDto save(RoomSubscribeVO roomSubscribeVo) throws Exception;

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
	 * @return ResultDataDto<RoomSubscribeVO>
	 * @throws
	 */
	public ResultDataDto<RoomSubscribeVO> getById(Long id) throws Exception;

	/**
	 * 
	 * @Title: getAll 
	 * @Description:查询所有数据 
	 * @author: 
	 * @return ResultDataDto<List<RoomSubscribeVO>>
	 * @throws
	 */
	public ResultDataDto<List<RoomSubscribeVO>> getAll() throws Exception;

	/**
	*
	* @Title: update
	* @Description:修改
	* @author:
	* @param @param roomSubscribeEntity
	* @return int
	* @throws
	*/
	public ResultDto update(RoomSubscribeVO roomSubscribeVo) throws Exception;

}