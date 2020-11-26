package com.coderman.codemaker.dbergenerate.service;

import com.coderman.codemaker.dbergenerate.bean.ErPictureBean;
import com.coderman.codemaker.dbergenerate.bean.TableBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * date: 2020/10/20
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public class ErPictureServiceImpl implements ErPictureService{
    private ErPictureWriteService erPictureWriteService = new ErPictureWriteService();

    private ErPictureServiceHelper erPictureServiceHelper = new ErPictureServiceHelper();

    @Override
    public boolean generateER(String path,List<TableBean> tableBeanList) {
        Map<String,String> mainEntityMap = new HashMap<>();
        for (TableBean tableBean : tableBeanList){
            String plantUML = erPictureServiceHelper.getEntityPlantUML(tableBean);
            mainEntityMap.put(tableBean.getTableName(),plantUML);
        }
        String relationStr = erPictureServiceHelper.getTableRelation(tableBeanList);
        ErPictureBean erPictureBean = new ErPictureBean();
        erPictureBean.setMainEntityMap(mainEntityMap);
        erPictureBean.setRelationStr(relationStr);
        return erPictureWriteService.write(path,erPictureBean);
    }





}
