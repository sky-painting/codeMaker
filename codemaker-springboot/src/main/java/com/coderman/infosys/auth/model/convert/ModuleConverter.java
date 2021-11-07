package com.coderman.infosys.auth.model.convert;

import java.util.List;
import com.coderman.infosys.auth.adapter.vo.CreateModuleRequestVO;
import com.coderman.infosys.auth.adapter.vo.ModuleVO;
import com.coderman.infosys.auth.domain.bo.ModuleBO;
import com.coderman.infosys.auth.adapter.vo.UpdateModuleRequestVO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
* @Description:ModuleConvertervobo接口
* @Author:
* @CreateTime:2021-11-06 23:38:07
* @version v1.0
*/
@Mapper
public interface ModuleConverter{
	ModuleConverter INSTANCE = Mappers.getMapper(ModuleConverter.class);

	/**
	 *
	 * @Description:
	 * @return List<ModuleBO>
	 */
	 List<ModuleBO> VOs2BOs(List<ModuleVO> vOList);
	/**
	 *
	 * @Description:
	 * @return List<ModuleBO>
	 */
	 List<ModuleBO> vos2bos(List<UpdateModuleRequestVO> updateModuleRequestVOList);
	/**
	 *
	 * @Description:
	 * @return ModuleBO
	 */
	 ModuleBO vo2bo(ModuleVO moduleVO);
	/**
	 *
	 * @Description:
	 * @return ModuleVO
	 */
	 ModuleVO bo2VO(ModuleBO moduleBO);
	/**
	 *
	 * @Description:
	 * @return List<ModuleVO>
	 */
	 List<ModuleVO> BOs2VOs(List<ModuleBO> moduleBOList);
	/**
	 *
	 * @Description:
	 * @return ModuleBO
	 */
	 ModuleBO vo2bo(UpdateModuleRequestVO updateModuleRequestVO);
	/**
	 *
	 * @Description:
	 * @return ModuleBO
	 */
	 ModuleBO vo2bo(CreateModuleRequestVO createModuleRequestVO);
	/**
	 *
	 * @Description:
	 * @return List<ModuleBO>
	 */
	 List<ModuleBO> VOs2BOs(List<CreateModuleRequestVO> createModuleRequestVOList);
}