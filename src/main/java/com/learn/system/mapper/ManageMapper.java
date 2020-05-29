package com.learn.system.mapper;

import com.learn.system.pojo.ClassInfo;
import com.learn.system.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ManageMapper {

    //查询某个班级号是否存在，如果存在就不能插入
    int isExistClassNo(String classNo);

    //插入班级信息
    void insertClassInfo(ClassInfo cla);

    //查询某个学号是否存在，如果存在就不能插入
    int isExistStuNo(String stuNo);

    //插入学生信息
    void insertStudentInfo(Student student);

    //查询所有的班级号，以便学生信息插入
    List<String> queryAllClassNo();

    //班级人数加一
    void updateClassNum(String classNo);

    //按照学号删除学生信息
    void deleteStuByNo(String stuNo);

    //班级人数减一
    void updateClassNumM(String classNo);

    //根据学号删除成绩表
    void deleteScoreByNo(String stuNo);

    //查询所有学生信息
    List<Student> queryAllStudent();

    //通过学号找班级号
    String queryClassNoByStuNo(String stuNo);

}
