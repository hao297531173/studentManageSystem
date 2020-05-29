package com.learn.system.mapper;

import com.learn.system.pojo.Course;
import com.learn.system.pojo.Score;
import com.learn.system.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper
@Component
public interface StudentMapper {


    //通过学号查询某个学生信息
    Student selectStu(String stuNo);

    //查询所有学生信息
    List<Student> selectAllStu();

    //返回一个学生所有课程成绩的总和
    int calStuSum(String stuNo);

    //返回一个学生有成绩的课程数
    int getScoreNum(String stuNo);

    //计算某个学生已修学分
    int calStuCredit(String stuNo);

    //查找学生有成绩的所有课程
    List<Course> queryAllCourse(String stuNo);

    //查找学生所有成绩
    List<Score> queryAllScore(String stuNo);
}
