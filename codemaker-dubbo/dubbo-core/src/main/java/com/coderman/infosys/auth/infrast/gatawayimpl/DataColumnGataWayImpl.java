package com.coderman.infosys.auth.infrast.gatawayimpl;

import java.util.List;
import com.coderman.utils.commonbo.PageBO;
import com.coderman.infosys.auth.domain.gataway.DataColumnGataWay;
import com.coderman.infosys.auth.domain.bo.DataColumnBO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @Description:接口实现类
 * @Author：
 * @CreateTime：2021-11-07 08:18:28
 * @version v1.0
 */
@Service
public class DataColumnGataWayImpl  implements DataColumnGataWay{

	private  Logger logger = LoggerFactory.getLogger(this.getClass());



    @Override
	public Long saveDataColumn(DataColumnBO dataColumnBO){

        return null;
    }

    @Override
	public Boolean saveBatchDataColumn(List<DataColumnBO> list){

        return null;
    }

    @Override
	public Long updateDataColumn(DataColumnBO dataColumnBO){

        return null;
    }

    @Override
	public DataColumnBO getById(Long id){

        return null;
    }

    @Override
	public List<DataColumnBO> getByDBCode(String busDataBase){

        return null;
    }

    @Override
	public List<DataColumnBO> getPageList(PageBO pageBO){

        return null;
    }

}