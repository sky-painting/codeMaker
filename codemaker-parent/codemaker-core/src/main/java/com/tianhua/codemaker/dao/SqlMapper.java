package com.tianhua.codemaker.dao;

import com.tianhua.codemaker.bean.ColumnBean;
import com.tianhua.codemaker.bean.TableBean;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author shenshuai
 * @Date 2019/11/22 17
 * @Description:mysql 元数据查询接口
 */
@Repository
public interface SqlMapper {

    Integer getCount(String countSql);


    List<TableBean> getDBTableBeanList(String dbName);


    List<ColumnBean> getColumnBeanList(String dbName);
}
