package ${package}.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ${package}.service.${table.humpClassName}Service;
import ${package}.vo.${table.humpClassName}VO;

import ${package}.controller.BaseController;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
* @Description:${table.tableComment}控制层
* @Author:${author}
* @CreateTime:${.now?string('yyyy-MM-dd HH:mm:ss')}
* @version v1.0
*/
@RestController
public class ${table.humpClassName}Controller extends BaseController{
	
	protected Logger logger = LoggerFactory.getLogger(${table.humpClassName}Controller.class);

	@Autowired
	private ${table.humpClassName}Service ${table.humpTableName}Service;


	/**
     * @Description:新增${table.tableComment}
     * @version v1.0
     * @param ${table.humpTableName}VO
     * @return ResultDto
     */
    @RequestMapping(value = "/${table.humpTableName}/add",method = RequestMethod.POST)
    public ResultDto add(@RequestBody ${table.humpClassName}VO ${table.humpTableName}VO){
        //todo impl code
		return new ResultDto();
    }

	/**
	 * @Description:修改${table.tableComment}
	 * @version v1.0
	 * @param ${table.humpTableName}VO
	 * @return ResultDto
	 */
    @RequestMapping(value = "/${table.humpTableName}/update",method = RequestMethod.POST)
	public ResultDto update(@RequestBody ${table.humpClassName}VO ${table.humpTableName}VO){
        //todo impl code
		return new ResultDto();
	}

	/**
	 * @Description:根据id删除${table.tableComment}
	 * @version v1.0
	 * @param id
	 * @return ResultDto
	 */
	@PostMapping("/${table.humpTableName}/delete")
	public ResultDto delete(@RequestParam(name = "id") Long id){
		//todo impl code
		return new ResultDto();
	}

	/**
	 * @Description:根据ID获取${table.tableComment}单条记录
	 * @version v1.0
	 * @param id
	 * @return ResultDataDto
	 */
	@GetMapping("/${table.humpTableName}/get")
	public ResultDataDto getById(@RequestParam(name = "id") Long id){
		//todo impl code
		return new ResultDataDto();
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
