package com.learn.system.service.serviceImpl;

import com.learn.system.mapper.StudentMapper;
import com.learn.system.pojo.Course;
import com.learn.system.pojo.Score;
import com.learn.system.pojo.Student;
import com.learn.system.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentMapper studentMapper;

    @Override
    public Student selectStu(String stuNo) {
        return studentMapper.selectStu(stuNo);
    }

    @Override
    public List<Student> selectAllStu() {
        return studentMapper.selectAllStu();
    }

    @Override
    public double calStuAverage(String stuNo) {
        Integer sum = studentMapper.calStuSum(stuNo);       //计算学生获得的总分
        Integer num = studentMapper.getScoreNum(stuNo);         //计算学生通过的考试数
        double avg = sum/num;
        //四舍五入保留两位小数
        BigDecimal b = new BigDecimal(avg);
        avg = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return avg;
    }

    @Override
    public int calStuCredit(String stuNo) {
        return studentMapper.calStuCredit(stuNo);
    }

    @Override
    public List<Course> queryAllCourse(String stuNo) {
        return studentMapper.queryAllCourse(stuNo);
    }

    @Override
    public List<Course> querySomeCourse(List<Course> courseList, int pageNum, int offset) {
        List<Course> list = new ArrayList<Course>();    //pageNum是从0开始的
        int num = (pageNum-1)*offset;   //需要略过的课程数
        int cnt = 0;        //记录添加的数量
        for(Course li : courseList){
            if(num!=0){
                num--;
                continue;
            }
            list.add(li);
            cnt++;
            if(cnt == offset){
                return list;
            }
        }
        return list;    //这里是数量不足offset的时候的返回值
    }

    @Override
    public List<Score> queryAllScore(String stuNo) {
        return studentMapper.queryAllScore(stuNo);
    }

    @Override
    public List<Score> querySomeScore(List<Score> scoreList, int pageNum, int offset) {
        List<Score> list = new ArrayList<Score>();    //pageNum是从0开始的
        int num = (pageNum-1)*offset;   //需要略过的课程数
        int cnt = 0;        //记录添加的数量
        for(Score li : scoreList){
            if(num!=0){
                num--;
                continue;
            }
            list.add(li);
            cnt++;
            if(cnt == offset){
                return list;
            }
        }
        return list;    //这里是数量不足offset的时候的返回值
    }

}
