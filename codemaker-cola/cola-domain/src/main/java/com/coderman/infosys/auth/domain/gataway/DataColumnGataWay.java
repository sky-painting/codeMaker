package com.coderman.infosys.auth.domain.gataway;

import java.util.List;
import com.coderman.utils.commonbo.PageBO;
import com.coderman.infosys.auth.domain.bo.DataColumnBO;


/**
 * @Description:数据字段管理网关接口
 * @Author:shenshuai
 * @CreateTime:2021-11-07 08:23:49
 * @version v1.0
 */
public interface DataColumnGataWay{

	/**
	 *
	 * @Description 保存数据字段
	 * @param dataColumnBO
	 * @return Long
	 */
     Long saveDataColumn(DataColumnBO dataColumnBO);

	/**
	 *
	 * @Description 批量保存保存数据字段
	 * @param list
	 * @return Boolean
	 */
     Boolean saveBatchDataColumn(List<DataColumnBO> list);

	/**
	 *
	 * @Description 更新数据字段
	 * @param dataColumnBO
	 * @return Long
	 */
     Long updateDataColumn(DataColumnBO dataColumnBO);

	/**
	 *
	 * @Description 根据ID查询数据字段详情
	 * @param id
	 * @return DataColumnBO
	 */
     DataColumnBO getById(Long id);

	/**
	 *
	 * @Description 根据数据库名称获取字段元数据内容
	 * @param busDataBase
	 * @return List<DataColumnBO>
	 */
     List<DataColumnBO> getByDBCode(String busDataBase);

	/**
	 *
	 * @Description 分页查询数据字段内容
	 * @param pageBO
	 * @return List<DataColumnBO>
	 */
     List<DataColumnBO> getPageList(PageBO pageBO);
}