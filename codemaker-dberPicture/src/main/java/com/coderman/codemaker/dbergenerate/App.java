package com.coderman.codemaker.dbergenerate;


import com.coderman.codemaker.dbergenerate.bean.ColumnBean;
import com.coderman.codemaker.dbergenerate.bean.TableBean;
import com.coderman.codemaker.dbergenerate.service.ErPictureService;
import com.coderman.codemaker.dbergenerate.service.ErPictureServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    private static TableBean getStudentBean(){
        TableBean tableBean = new TableBean();
        tableBean.setTableComment("学生表");
        tableBean.setTableName("student");

        List<ColumnBean> columnBeanList = new ArrayList<>();
        ColumnBean columnBean = new ColumnBean();
        columnBean.setColumnComment("主键");
        columnBean.setColumnName("id");
        columnBean.setTableName("student");
        columnBean.setColumnType("bigint");
        ColumnBean columnBean2 = new ColumnBean();
        columnBean2.setColumnComment("姓名");
        columnBean2.setColumnName("chinese_name");
        columnBean2.setTableName("student");
        columnBean2.setColumnType("varchar");

        ColumnBean columnBean3 = new ColumnBean();
        columnBean3.setColumnComment("年龄");
        columnBean3.setColumnName("age");
        columnBean3.setTableName("student");
        columnBean3.setColumnType("int");

        ColumnBean columnBean4 = new ColumnBean();
        columnBean4.setColumnComment("所属班级");
        columnBean4.setColumnName("class_id");
        columnBean4.setTableName("student");
        columnBean4.setColumnType("bigint");

        columnBeanList.add(columnBean);
        columnBeanList.add(columnBean2);
        columnBeanList.add(columnBean3);
        columnBeanList.add(columnBean4);

        tableBean.setColumnBeanList(columnBeanList);
        return tableBean;
    }

    private static TableBean getCourseBean(){
        TableBean tableBean = new TableBean();
        tableBean.setTableComment("课程表");
        tableBean.setTableName("course");

        List<ColumnBean> columnBeanList = new ArrayList<>();
        ColumnBean columnBean = new ColumnBean();
        columnBean.setColumnComment("主键");
        columnBean.setColumnName("id");
        columnBean.setTableName("course");
        columnBean.setColumnType("bigint");
        ColumnBean columnBean2 = new ColumnBean();
        columnBean2.setColumnComment("课程名称");
        columnBean2.setColumnName("course_name");
        columnBean2.setTableName("course");
        columnBean2.setColumnType("varchar");

        ColumnBean columnBean3 = new ColumnBean();
        columnBean3.setColumnComment("上课老师");
        columnBean3.setColumnName("teacher_id");
        columnBean3.setTableName("course");
        columnBean3.setColumnType("bigint");

        ColumnBean columnBean4 = new ColumnBean();
        columnBean4.setColumnComment("课时数");
        columnBean4.setColumnName("course_num");
        columnBean4.setTableName("course");
        columnBean4.setColumnType("int");

        columnBeanList.add(columnBean);
        columnBeanList.add(columnBean2);
        columnBeanList.add(columnBean3);
        columnBeanList.add(columnBean4);

        tableBean.setColumnBeanList(columnBeanList);
        return tableBean;
    }

    private static TableBean getScoreBean(){
        TableBean tableBean = new TableBean();
        tableBean.setTableComment("分数表");
        tableBean.setTableName("score");

        List<ColumnBean> columnBeanList = new ArrayList<>();
        ColumnBean columnBean = new ColumnBean();
        columnBean.setColumnComment("主键");
        columnBean.setColumnName("id");
        columnBean.setTableName("score");
        columnBean.setColumnType("bigint");
        ColumnBean columnBean2 = new ColumnBean();
        columnBean2.setColumnComment("课程Id");
        columnBean2.setColumnName("course_id");
        columnBean2.setTableName("score");
        columnBean2.setColumnType("bigint");

        ColumnBean columnBean3 = new ColumnBean();
        columnBean3.setColumnComment("学生ID");
        columnBean3.setColumnName("student_id");
        columnBean3.setTableName("score");
        columnBean3.setColumnType("bigint");

        ColumnBean columnBean4 = new ColumnBean();
        columnBean4.setColumnComment("分数");
        columnBean4.setColumnName("score_num");
        columnBean4.setTableName("score");
        columnBean4.setColumnType("int");

        columnBeanList.add(columnBean);
        columnBeanList.add(columnBean2);
        columnBeanList.add(columnBean3);
        columnBeanList.add(columnBean4);

        tableBean.setColumnBeanList(columnBeanList);
        return tableBean;
    }

    private static TableBean getClassBean(){
        TableBean tableBean = new TableBean();
        tableBean.setTableComment("班级表");
        tableBean.setTableName("class");

        List<ColumnBean> columnBeanList = new ArrayList<>();
        ColumnBean columnBean = new ColumnBean();
        columnBean.setColumnComment("主键");
        columnBean.setColumnName("id");
        columnBean.setTableName("class");
        columnBean.setColumnType("bigint");
        ColumnBean columnBean2 = new ColumnBean();
        columnBean2.setColumnComment("班级名称");
        columnBean2.setColumnName("class_name");
        columnBean2.setTableName("class");
        columnBean2.setColumnType("varchar");

        ColumnBean columnBean3 = new ColumnBean();
        columnBean3.setColumnComment("班级序号");
        columnBean3.setColumnName("class_num");
        columnBean3.setTableName("class");
        columnBean3.setColumnType("int");

        ColumnBean columnBean4 = new ColumnBean();
        columnBean4.setColumnComment("学生数");
        columnBean4.setColumnName("student_num");
        columnBean4.setTableName("class");
        columnBean4.setColumnType("int");

        columnBeanList.add(columnBean);
        columnBeanList.add(columnBean2);
        columnBeanList.add(columnBean3);
        columnBeanList.add(columnBean4);

        tableBean.setColumnBeanList(columnBeanList);
        return tableBean;
    }

    private static TableBean getTeacherClassBean(){
        TableBean tableBean = new TableBean();
        tableBean.setTableComment("老师表");
        tableBean.setTableName("teacher");

        List<ColumnBean> columnBeanList = new ArrayList<>();
        ColumnBean columnBean = new ColumnBean();
        columnBean.setColumnComment("主键");
        columnBean.setColumnName("id");
        columnBean.setTableName("teacher");
        columnBean.setColumnType("bigint");
        ColumnBean columnBean2 = new ColumnBean();
        columnBean2.setColumnComment("教师姓名");
        columnBean2.setColumnName("teacher_name");
        columnBean2.setTableName("teacher");
        columnBean2.setColumnType("varchar");

        ColumnBean columnBean3 = new ColumnBean();
        columnBean3.setColumnComment("教师年龄");
        columnBean3.setColumnName("age");
        columnBean3.setTableName("teacher");
        columnBean3.setColumnType("int");

        ColumnBean columnBean4 = new ColumnBean();
        columnBean4.setColumnComment("教师职称");
        columnBean4.setColumnName("post_name");
        columnBean4.setTableName("teacher");
        columnBean4.setColumnType("varchar");

        columnBeanList.add(columnBean);
        columnBeanList.add(columnBean2);
        columnBeanList.add(columnBean3);
        columnBeanList.add(columnBean4);

        tableBean.setColumnBeanList(columnBeanList);
        return tableBean;
    }


    public static void main( String[] args )
    {
        List<TableBean> tableBeanList = new ArrayList<>();
        tableBeanList.add(getClassBean());
        tableBeanList.add(getCourseBean());
        tableBeanList.add(getScoreBean());
        tableBeanList.add(getStudentBean());
        tableBeanList.add(getTeacherClassBean());
        ErPictureService erPictureService = new ErPictureServiceImpl();
        String path = "/Users/dasouche/scworkspace/myspace/tianhua/tianhua-dberGenerator/src/main/resources/teachSystem.puml";
        path = "E:\\workspace\\newWorkspace\\codeMaker\\codemaker-dberPicture\\src\\main\\resources\\teachSystem.puml";
        erPictureService.generateER(path,tableBeanList);
    }

}
