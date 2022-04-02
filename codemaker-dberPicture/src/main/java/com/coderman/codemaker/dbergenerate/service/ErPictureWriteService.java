package com.coderman.codemaker.dbergenerate.service;

import com.coderman.codemaker.dbergenerate.bean.ErPictureBean;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Description:
 * date: 2020/10/20
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public class ErPictureWriteService {
    /**
     * 执行写e-r图文件的方法
     * @param path
     * @param erPictureBean
     * @return
     */
    public boolean write(String path, ErPictureBean erPictureBean){
        StringBuilder builder = new StringBuilder();
        builder.append("@startuml\n" +
                "' hide the spot\n" +
                "hide circle\n" +
                "\n" +
                "' avoid problems with angled crows feet\n" +
                "skinparam linetype ortho");
        builder.append("\n\n");
        for (Map.Entry<String,String> entry : erPictureBean.getMainEntityMap().entrySet()){
            builder.append(entry.getValue()+"\n");
        }
        builder.append("\n\n");
        builder.append(erPictureBean.getRelationStr()+"\n\n");
        builder.append("@enduml");
        try {
            FileUtils.writeStringToFile(new File(path),builder.toString(),"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
