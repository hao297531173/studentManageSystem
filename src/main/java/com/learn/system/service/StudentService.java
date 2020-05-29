package com.learn.system.service;

import com.learn.system.pojo.Course;
import com.learn.system.pojo.Score;
import com.learn.system.pojo.Student;

import java.util.List;

public interface StudentService {

    //通过学号查询某个学生信息
    Student selectStu(String stuNo);

    //查询所有学生信息
    List<Student> selectAllStu();

    //计算某个学生的平均分
    double calStuAverage(String stuNo);

    //计算某个学生已修学分
    int calStuCredit(String stuNo);

    //查找学生有成绩的所有课程
    List<Course> queryAllCourse(String stuNo);

    //分页查找学生课程
    List<Course> querySomeCourse(List<Course> courseList, int pageNum, int offset);

    //查找学生的所有成绩
    List<Score> queryAllScore(String stuNo);

    //分页查找学生成绩
    List<Score> querySomeScore(List<Score> scoreList, int pageNum, int offset);

}
