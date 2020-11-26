package com.coderman.codemaker.dbergenerate.service;


import com.coderman.codemaker.dbergenerate.bean.TableBean;

import java.util.List;

/**
 * Description: 生成e-r图的服务接口
 * date: 2020/10/20
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public interface ErPictureService {
    /**
     * 生成执行入口
     * @param tableBeanList
     * @return
     */
    public boolean generateER(String path, List<TableBean> tableBeanList);
}
