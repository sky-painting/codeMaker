package com.coderman.infosys.auth.infrast.acl;

import java.util.List;
import com.coderman.infosys.auth.infrast.acl.res.dto.CityResponseDTO;
import com.coderman.infosys.auth.infrast.acl.res.dto.ProvinceResponseDTO;


/**
 * @Description:省份城市查询服务适配器接口
 * @Author:shenshuai
 * @CreateTime:2021-11-07 08:18:28
 * @version v1.0
 */
public interface AreaServiceAdapter{
    /**
     *
     * @Description:查询所有的省份
     * @return List<ProvinceResponseDTO>
     */
     List<ProvinceResponseDTO> queryAllProvince();
    /**
     *
     * @Description:查询省份下所有城市
     * @return List<CityResponseDTO>
     */
     List<CityResponseDTO> queryCityByProvinceid(Long provinceId);
}