package ${package}.controller;

import lombok.extern.slf4j.Slf4j;

import ${package}.service.${table.humpClassName}Service;
import ${package}.vo.${table.humpClassName}VO;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.coderman.utils.error.CommonErrorEnum;


/**
* @Description:${table.tableComment}控制层
* @Author:${author}
* @CreateTime:${.now?string('yyyy-MM-dd HH:mm:ss')}
* @version v1.0
*/
@RestController
@Slf4j
public class ${table.humpClassName}Controller extends BaseController{

	@Autowired
	private ${table.humpClassName}Service ${table.humpTableName}Service;


	/**
     * @Description:新增${table.tableComment}
     * @version v1.0
     * @param ${table.humpTableName}Vo
     * @return ResultDto
     */
    @RequestMapping(value = "/${table.humpTableName}/add",method = RequestMethod.POST)
    public ResultDto save(@RequestBody ${table.humpClassName}VO ${table.humpTableName}Vo){
		try {
			return ${table.humpTableName}Service.save(${table.humpTableName}Vo);
		} catch (Exception e) {
			log.error("保存失败",e);
			return ResultDto.fail(CommonErrorEnum.DEFAULT_ERROR.getErrorCode(),"保存失败");
		}

    }

	/**
	 * @Description:修改${table.tableComment}
	 * @version v1.0
	 * @param ${table.humpTableName}Vo
	 * @return ResultDto
	 */
    @RequestMapping(value = "/${table.humpTableName}/update",method = RequestMethod.POST)
    public ResultDto update(@RequestBody ${table.humpClassName}VO ${table.humpTableName}Vo){
		try {
			return ${table.humpTableName}Service.update(${table.humpTableName}Vo);
		} catch (Exception e) {
			log.error("修改失败",e);
			return ResultDto.fail(CommonErrorEnum.DEFAULT_ERROR.getErrorCode(),"修改失败");
		}
	}

	/**
	 * @Description:根据id删除${table.tableComment}
	 * @version v1.0
	 * @param id
	 * @return ResultDto
	 */
	@PostMapping("/${table.humpTableName}/delete")
	public ResultDto delete(@RequestParam(name = "id") Long id){
		return ${table.humpTableName}Service.delete(id);
	}

	/**
	 * @Description:根据ID获取${table.tableComment}单条记录
	 * @version v1.0
	 * @param id
	 * @return ResultDataDto
	 */
	@GetMapping("/${table.humpTableName}/get")
	public ResultDataDto getById(@RequestParam(name = "id") Long id){
		try {
			return ${table.humpTableName}Service.getById(id);
		} catch (Exception e) {
			log.error("获取数据失败",e);
			return ResultDataDto.fail(CommonErrorEnum.DEFAULT_ERROR.getErrorCode(), "获取数据失败");
		}
	}

	/**
	 * @Description:分页获取${table.tableComment}记录
	 * @version v1.0
	 * @return ResultDataDto
	 */
	@GetMapping("/${table.humpTableName}/getpage")
	public ResultDataDto getPage(){
		//todo impl code
		return new ResultDataDto();
	}

	/**
	 * @Description:修改${table.tableComment}状态
	 * @version v1.0
	 * @param id
     * @param status
	 * @return ResultDataDto
	 */
	@PostMapping("/${table.humpTableName}/changestatus")
	public ResultDto changeStatus(@RequestParam(name = "id") Long id,@RequestParam(name = "status") int status){
		//todo impl code
		return new ResultDto();
	}

}
