package com.coderman.infosys.auth.adapter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coderman.infosys.auth.adapter.vo.DataColumnVO;
import java.util.List;
import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.PageVO;
import com.coderman.infosys.auth.api.dto.UpdateMenuRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
* @Description:控制层
* @Author:
* @CreateTime:2021-11-07 08:23:49
* @version v1.0
*/
@RestController
public class DataColumnController {
	
	protected Logger logger = LoggerFactory.getLogger(DataColumnController.class);



	/**
	 *
	 * @Description 获取数据字段信息
	 * @param busDataBase
	 * @return ResultDataDto<List<DataColumnVO>>
	 */
	@RequestMapping(value = "/datacolumn/getbycode")
	public ResultDataDto<List<DataColumnVO>> getListByBusDataBase(@RequestParam(value = "busDataBase", required = true) String busDataBase){

		return null;
	}

	/**
	 *
	 * @Description 分页获取数据字段信息
	 * @param pageVO
	 * @return ResultDataDto<PageVO<DataColumnVO>>
	 */
	@RequestMapping(value = "/datacolumn/pagelist")
	public ResultDataDto<PageVO<DataColumnVO>> getPageList(@RequestParam(value = "pageVO", required = true) PageVO pageVO){

		return null;
	}

	/**
	 *
	 * @Description 导入数据字段列表
	 * @param updateMenuRequestDTO
	 * @return ResultDataDto<Boolean>
	 */
	@RequestMapping(value = "/datacolumn/import")
	public ResultDataDto<Boolean> importDataColumnList(@RequestBody List<DataColumnVO> updateMenuRequestDTO){

		return null;
	}
}
