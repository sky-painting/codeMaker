package com.coderman.codemaker.service;

import com.coderman.codemaker.bean.ColumnBean;
import com.coderman.codemaker.bean.TableBean;
import com.coderman.codemaker.config.ProjectTemplateConfig;
import com.coderman.codemaker.enums.TemplateFileEnum;
import com.coderman.codemaker.utils.Constant;
import com.coderman.codemaker.utils.FreemarkerUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * description: WriteFileService <br>
 * date: 2020/7/8 11:43 <br>
 * author: coderman <br>
 * version: 1.0 <br>
 */
@Service
public class WriteFileService {

    @Autowired
    private ProjectTemplateConfig projectTemplateConfig;

    @Autowired
    private DBErPictureService erPictureService;


    /**
     * 写mapper xml文件
     *
     * @param content
     * @param humpClassName
     */
    public void writeMapperXml(String content, String humpClassName) {
        String fileName = humpClassName + "Mapper.xml";
        String filePath = projectTemplateConfig.getOutPath() + Constant.MAPPER + "/" + fileName;
        try {
            FileUtils.write(new File(filePath), content, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写entity文件
     *
     * @param content
     * @param humpClassName
     */
    public void writeEntity(String content, String humpClassName) {
        String packageName = projectTemplateConfig.getPackageName();
        String packagePath = packageName.replace(".", "\\");
        String filePath = Constant.JAVA + "/" + packagePath + "/entity";
        String fileName = humpClassName + "Entity.java";
        filePath = projectTemplateConfig.getOutPath() + "\\" + filePath + "\\" + fileName;
        try {
            FileUtils.write(new File(filePath), content, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 写entity文件
     *
     * @param content
     * @param humpClassName
     */
    public void writeVO(String content, String humpClassName) {
        String packageName = projectTemplateConfig.getPackageName();
        String packagePath = packageName.replace(".", "\\");
        String filePath = Constant.JAVA + "/" + packagePath + "/vo";
        String fileName = humpClassName + "VO.java";
        filePath = projectTemplateConfig.getOutPath() + "\\" + filePath + "\\" + fileName;
        try {
            FileUtils.write(new File(filePath), content, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 写mapper class文件
     *
     * @param content
     * @param humpClassName
     */
    public void writeMapper(String content, String humpClassName) {
        String packageName = projectTemplateConfig.getPackageName();
        String packagePath = packageName.replace(".", "\\");
        String filePath = Constant.JAVA + "/" + packagePath + "/mapper";
        String fileName = humpClassName + "Mapper.java";
        filePath = projectTemplateConfig.getOutPath() + "\\" + filePath + "\\" + fileName;
        try {
            FileUtils.write(new File(filePath), content, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写service文件
     *
     * @param content
     * @param humpClassName
     */
    public void writeService(String content, String humpClassName) {
        String packageName = projectTemplateConfig.getPackageName();
        String packagePath = packageName.replace(".", "\\");
        String filePath = Constant.JAVA + "/" + packagePath + "/service";
        String fileName = humpClassName + "Service.java";
        filePath = projectTemplateConfig.getOutPath() + "\\" + filePath + "\\" + fileName;
        try {
            FileUtils.write(new File(filePath), content, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写serviceImpl文件
     *
     * @param content
     * @param humpClassName
     */
    public void writeServiceImpl(String content, String humpClassName) {
        String packageName = projectTemplateConfig.getPackageName();
        String packagePath = packageName.replace(".", "\\");
        String filePath = Constant.JAVA + "/" + packagePath + "/service/impl";
        String fileName = humpClassName + "ServiceImpl.java";
        filePath = projectTemplateConfig.getOutPath() + "\\" + filePath + "\\" + fileName;
        try {
            FileUtils.write(new File(filePath), content, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 写serviceImpl文件
     *
     * @param content
     */
    public void writeBaseController(String content) {
        String packageName = projectTemplateConfig.getPackageName();
        String packagePath = packageName.replace(".", "\\");
        String filePath = Constant.JAVA + "/" + packagePath + "/controller";
        String fileName = "BaseController.java";
        filePath = projectTemplateConfig.getOutPath() + "\\" + filePath + "\\" + fileName;
        try {
            FileUtils.write(new File(filePath), content, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写工具类文件
     *
     * @param content
     */
    public void writeSpringApplicationContext(String content) {
        String packageName = projectTemplateConfig.getPackageName();
        String packagePath = packageName.replace(".", "\\");
        String filePath = Constant.JAVA + "/" + packagePath + "/utils";
        String fileName = "SpringApplicationContext.java";
        filePath = projectTemplateConfig.getOutPath() + "\\" + filePath + "\\" + fileName;
        try {
            FileUtils.write(new File(filePath), content, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 写测试文件
     *
     * @param content
     */
    public void writeTest(String content, String humpClassName) {
        String packageName = projectTemplateConfig.getPackageName();
        String packagePath = packageName.replace(".", "\\");
        String filePath = Constant.TEST_JAVA + "/" + packagePath + "/service/test";
        String fileName = humpClassName + "ServiceTest.java";
        filePath = projectTemplateConfig.getOutPath() + "\\" + filePath + "\\" + fileName;
        try {
            FileUtils.write(new File(filePath), content, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写应用启动类
     *
     * @param content
     */
    public void writeApplication(String content) {
        String packageName = projectTemplateConfig.getPackageName();
        String packagePath = packageName.replace(".", "\\");
        String filePath = Constant.JAVA + "/" + packagePath + "";
        String fileName = "Application.java";
        filePath = projectTemplateConfig.getOutPath() + "\\" + filePath + "\\" + fileName;
        try {
            FileUtils.write(new File(filePath), content, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 写controller文件
     *
     * @param content
     * @param humpClassName
     */
    public void writeController(String content, String humpClassName) {
        String packageName = projectTemplateConfig.getPackageName();
        String packagePath = packageName.replace(".", "\\");
        String filePath = Constant.JAVA + "/" + packagePath + "/controller";
        String fileName = humpClassName + "Controller.java";
        filePath = projectTemplateConfig.getOutPath() + "\\" + filePath + "\\" + fileName;
        try {
            FileUtils.write(new File(filePath), content, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 一次性生成单表需要的所有模块代码
     *
     * @param humpClassName
     * @param varMap
     */
    public void writeAll(String humpClassName, Map<String, Object> varMap,String fast) {
        String entityContent = FreemarkerUtils.parseTpl(fast+TemplateFileEnum.ENTITY.getTempFileName(), varMap);
        this.writeEntity(entityContent, humpClassName);

        String serviceContent = FreemarkerUtils.parseTpl(fast+TemplateFileEnum.SERVICE.getTempFileName(), varMap);
        this.writeService(serviceContent, humpClassName);

        String serviceImplContent = FreemarkerUtils.parseTpl(fast+TemplateFileEnum.SERVICE_IMPL.getTempFileName(), varMap);
        this.writeServiceImpl(serviceImplContent, humpClassName);

        String mapperXmlContent = FreemarkerUtils.parseTpl(fast+TemplateFileEnum.MAPPER_XML.getTempFileName(), varMap);
        this.writeMapperXml(mapperXmlContent, humpClassName);

        String mapperContent = FreemarkerUtils.parseTpl(fast+TemplateFileEnum.MAPPER.getTempFileName(), varMap);
        this.writeMapper(mapperContent, humpClassName);

        String controllerContent = FreemarkerUtils.parseTpl(fast+TemplateFileEnum.CONTROLLER.getTempFileName(), varMap);
        this.writeController(controllerContent, humpClassName);

        String voContent = FreemarkerUtils.parseTpl(fast+TemplateFileEnum.VO.getTempFileName(), varMap);
        this.writeVO(voContent, humpClassName);

        String testContent = FreemarkerUtils.parseTpl(fast+TemplateFileEnum.TEST.getTempFileName(), varMap);
        this.writeTest(testContent, humpClassName);
    }




    /**
     * 写公共基础服务类
     *
     * @param varMap
     */
    public void writeCommon(Map<String, Object> varMap,String fast) {
        String baseControllerContent = FreemarkerUtils.parseTpl(fast+TemplateFileEnum.BASE_CONTROLLER.getTempFileName(), varMap);
        this.writeBaseController(baseControllerContent);

        String SpringApplicationContextContent = FreemarkerUtils.parseTpl(fast+TemplateFileEnum.SPRING_APPLICATION_CONTEXT.getTempFileName(), varMap);
        this.writeSpringApplicationContext(SpringApplicationContextContent);

        String application = FreemarkerUtils.parseTpl(fast+TemplateFileEnum.APPLICATION.getTempFileName(), varMap);
        this.writeApplication(application);

    }

    /**
     * 整合e-r图生成工具
     *
     * @param tableBeanMap
     * @param columnBeanListMap
     */
    public void writeERPicture(Map<String, TableBean> tableBeanMap, Map<String, List<ColumnBean>> columnBeanListMap) {
        String filePath = projectTemplateConfig.getOutPath() + Constant.ER_PICTURE + "/" + projectTemplateConfig.getDbName() + ".puml";
        List<com.coderman.codemaker.dbergenerate.bean.TableBean> tableBeanList = new ArrayList<>();
        tableBeanMap.forEach((k, v) -> {
            com.coderman.codemaker.dbergenerate.bean.TableBean tableBean = new com.coderman.codemaker.dbergenerate.bean.TableBean();
            tableBean.setTableComment(v.getTableComment());
            tableBean.setTableName(v.getTableName());
            List<ColumnBean> columnBeanList = columnBeanListMap.get(k);
            List<com.coderman.codemaker.dbergenerate.bean.ColumnBean> columnBeanList1 = new ArrayList<>();
            columnBeanList.forEach(columnBean -> {
                com.coderman.codemaker.dbergenerate.bean.ColumnBean columnBean1 = new com.coderman.codemaker.dbergenerate.bean.ColumnBean();
                columnBean1.setColumnComment(columnBean.getColumnComment());
                columnBean1.setColumnKey(columnBean.getColumnKey());
                columnBean1.setColumnName(columnBean.getColumnName());
                columnBean1.setTableName(columnBean.getTableName());
                columnBean1.setColumnType(columnBean.getColumnType());
                columnBean1.setDataType(columnBean.getDataType());
                columnBeanList1.add(columnBean1);
            });
            tableBean.setColumnBeanList(columnBeanList1);
            tableBeanList.add(tableBean);
        });
        erPictureService.getErPicture(filePath, tableBeanList);
    }

}
