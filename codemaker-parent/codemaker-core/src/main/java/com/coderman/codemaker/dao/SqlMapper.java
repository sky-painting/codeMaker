package com.coderman.codemaker.dao;

import com.coderman.codemaker.bean.ColumnBean;
import com.coderman.codemaker.bean.TableBean;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author fanchunshuai
 * @Date 2019/11/22 17
 * @Description:mysql 元数据查询接口
 */
@Repository
public interface SqlMapper {

    Integer getCount(String countSql);


    List<TableBean> getDBTableBeanList(String dbName);


    List<ColumnBean> getColumnBeanList(String dbName);
}
