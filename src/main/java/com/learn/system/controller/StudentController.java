package com.learn.system.controller;

import com.learn.system.pojo.Course;
import com.learn.system.pojo.Score;
import com.learn.system.pojo.Student;
import com.learn.system.service.serviceImpl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentController {
    @Autowired
    StudentServiceImpl studentService;


    //查询学生个人信息
    @RequestMapping(value = "/getStuInfo")
    public ModelAndView getStuInfo(ModelAndView mv, HttpSession session){
        String stuNo = (String)session.getAttribute("stuNo");
        mv.addObject("userName", stuNo);
        mv.addObject("userType", session.getAttribute("userType"));
        Student student = new Student();
        student = studentService.selectStu(stuNo);
        double avg = studentService.calStuAverage(stuNo);       //计算平均分
        int sumCredit = studentService.calStuCredit(stuNo);
        mv.addObject("stu", student);     //学生信息
        mv.addObject("avg", avg);         //平均分
        mv.addObject("sumCredit", sumCredit); //总学分
        mv.setViewName("welcome");
        return mv;
    }


    //查询学生培养计划
    @RequestMapping(value="/checkPlan/{pageNum}", method = RequestMethod.GET)
    public ModelAndView checkPlan(@PathVariable("pageNum")int pageNum,
                                  ModelAndView mv, HttpSession session){
        int offset = 5;
        mv.addObject("userType", session.getAttribute("userType"));
        mv.addObject("userName",session.getAttribute("stuNo") );
        String stuNo = (String)session.getAttribute("stuNo");
        List<Course> courseList = new ArrayList<Course>();
        int cnt = 0;        //记录总的结果条数
        courseList = studentService.querySomeCourse(studentService.queryAllCourse(stuNo),
                pageNum, offset);
        cnt = studentService.queryAllCourse(stuNo).size();
        int totalPage = cnt / offset;
        if(cnt % offset != 0){
            totalPage++;
        }
        if(totalPage == 0){
            totalPage=1;
        }
        mv.addObject("totalPage", totalPage);
        //将当前页面号返回给前端
        mv.addObject("pageNum", pageNum);
        mv.addObject("courstList", courseList);
        mv.setViewName("checkCourse");
//        System.out.println(courseList);
        return mv;
    }

    @RequestMapping(value = "/getScore/{pageNum}", method = RequestMethod.GET)
    public ModelAndView getScore(@PathVariable("pageNum")int pageNum,
                                 ModelAndView mv, HttpSession session){
        mv.addObject("userType", session.getAttribute("userType"));
        mv.addObject("userName",session.getAttribute("stuNo") );
        String stuNo = (String)session.getAttribute("stuNo");
        int offset = 5;
        int cnt = studentService.queryAllCourse(stuNo).size();  //记录总条数
        int totalPage = cnt/offset;
        if(cnt%offset != 0){
            totalPage++;
        }
        if(totalPage == 0){
            totalPage=1;
        }
        mv.addObject("totalPage", totalPage);   //总页数
        mv.addObject("pageNum", pageNum);       //当前页
        List<Score> scoreList = new ArrayList<Score>();
        scoreList = studentService.querySomeScore(studentService.queryAllScore(stuNo),
                pageNum, offset);
        mv.addObject("scoreList", scoreList);
        System.out.println(scoreList);
        mv.setViewName("checkScore");
        return mv;
    }
}
