package com.coderman.infosys.auth.app.facadeimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.coderman.utils.response.PageDTO;
import java.util.List;
import com.coderman.infosys.auth.api.facade.SystemFacade;
import com.coderman.utils.response.ResultDataDto;
import com.coderman.infosys.auth.api.dto.UpdateSystemRequestDTO;
import com.coderman.infosys.auth.api.dto.SystemDTO;
import com.coderman.infosys.auth.domain.gataway.SystemModuleRepository;
import com.coderman.infosys.auth.domain.bo.SystemBO;
import com.coderman.infosys.auth.domain.gataway.SystemModuleQueryGataWay;
import com.coderman.infosys.auth.app.convert.SystemConvert;
/**
 * @Description:接口实现类
 * @Author：
 * @CreateTime：2021-11-07 08:23:49
 * @version v1.0
 */
@Service
public class SystemFacadeImpl   implements SystemFacade{

	private  Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SystemModuleQueryGataWay systemModuleQueryGataWay;

    @Autowired
    private SystemModuleRepository systemModuleRepository;



    @Override
    public ResultDataDto<SystemDTO> registSystem(SystemDTO systemDTO){
        SystemBO systemBO = SystemConvert.INSTANCE.dto2bo(systemDTO);
        List<SystemBO> systemBOList = systemModuleQueryGataWay.getSystemByName(systemBO.getSystemName());

        return null;
    }

    @Override
    public ResultDataDto<PageDTO<SystemDTO>> getPageList(PageDTO pageDto){
        List<SystemBO> systemBOList = systemModuleQueryGataWay.querySystemPage(null);
        List<SystemDTO> systemDTOList = SystemConvert.INSTANCE.systemBOs2dtoList(systemBOList);

        return null;
    }

    @Override
    public ResultDataDto<SystemDTO> getBySystemCode(String systemCode){
        SystemBO systemBO = systemModuleQueryGataWay.queryBySystemCode(systemCode);
        SystemDTO systemDTO = SystemConvert.INSTANCE.bo2dto(systemBO);

        return ResultDataDto.success(systemDTO);
    }

    @Override
    public ResultDataDto<Boolean> updateSystem(UpdateSystemRequestDTO dto){
        SystemBO systemBO = SystemConvert.INSTANCE.dto2bo(dto);
        Integer intVar = systemModuleRepository.updateSystem(systemBO);

        return null;
    }

    @Override
    public ResultDataDto<List<SystemDTO>> getSearchList(String search){
        List<SystemBO> systemBOList = systemModuleQueryGataWay.queryByCondition(search);
        List<SystemDTO> systemDTOList = SystemConvert.INSTANCE.systemBOs2dtoList(systemBOList);

        return ResultDataDto.success(systemDTOList);
    }

}