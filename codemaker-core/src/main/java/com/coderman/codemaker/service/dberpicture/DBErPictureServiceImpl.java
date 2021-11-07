package com.coderman.codemaker.service.dberpicture;

import com.coderman.codemaker.dbergenerate.bean.TableBean;
import com.coderman.codemaker.dbergenerate.service.ErPictureService;
import com.coderman.codemaker.dbergenerate.service.ErPictureServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description: DBErPictureServiceImpl <br>
 * date: 2020/10/21 23:25 <br>
 * author: coderman <br>
 * version: 1.0 <br>
 */
@Service
public class DBErPictureServiceImpl implements DBErPictureService {
    private ErPictureService erPictureService = new ErPictureServiceImpl();
    @Override
    public boolean getErPicture(String path, List<TableBean> tableBeanList) {
        return erPictureService.generateER(path,tableBeanList);
    }
}
