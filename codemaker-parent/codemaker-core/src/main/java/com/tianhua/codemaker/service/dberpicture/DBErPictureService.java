package com.tianhua.codemaker.service.dberpicture;

import com.coderman.codemaker.dbergenerate.bean.TableBean;

import java.util.List;

/**
 * description: DBErPicture <br>
 * date: 2020/10/21 23:24 <br>
 * author: coderman <br>
 * version: 1.0 <br>
 */
public interface DBErPictureService {
    /**
     * 生成数据库e-r图文件
     * @param path
     * @param tableBeanList
     * @return
     */
    boolean getErPicture(String path, List<TableBean> tableBeanList);
}
