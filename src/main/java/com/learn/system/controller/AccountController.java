package com.learn.system.controller;

import com.learn.system.pojo.Account;
import com.learn.system.pojo.Student;
import com.learn.system.service.serviceImpl.AccountServiceImpl;
import com.learn.system.service.serviceImpl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class AccountController {

    @Autowired
    AccountServiceImpl accountService;
    @Autowired
    StudentServiceImpl studentService;

    //登录操作
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String accountLogin(@RequestParam(value="username", required = false)String username,
                               @RequestParam(value="password", required = false)String password,
                               Model model, HttpSession session){
        //防止空指针
        if(username==null){
            System.out.println("usernmae null");
            return "index";
        }
        if(password==null){
            System.out.println("password null");
            return "index";
        }

        String pwd = accountService.checkPassword(username);
        //注意这里的权限码可能得到一个NULL，所以要放到登录成功的时候获取
        //用包装类也行
        if(password.equals(pwd)){   //密码正确，登录成功
            session.setAttribute("stuNo", username);
            model.addAttribute("userName", username);   //用户名
            Integer authority = accountService.getAuthority(username);
            session.setAttribute("userType", authority);
            model.addAttribute("userType", authority);  //权限码
            //这里分开，如果是学生登录跳转到学生控制器，如果是教师登录跳转到教师控制器
            if(authority.equals(1)){        //获取学生信息
                String stuNo = username;
                Student student = new Student();
                student = studentService.selectStu(stuNo);
                double avg = studentService.calStuAverage(stuNo);       //计算平均分
                int sumCredit = studentService.calStuCredit(stuNo);
                model.addAttribute("stu", student);     //学生信息
                model.addAttribute("avg", avg);         //平均分
                model.addAttribute("sumCredit", sumCredit); //总学分
                return "welcome";
            } else if(authority.equals(2)){
                return "welcome";
            } else {
                return "index";
            }
        } else {        //密码不正确，登录失败，返回登录页面
            model.addAttribute("loginAgain", 1);
            return "index";
        }
    }

    //帮助跳转
    @RequestMapping(value="/toRegist", method = RequestMethod.GET)
    public ModelAndView toRegist(ModelAndView mv){
        mv.setViewName("regist");   //不能用forward或者redirect
        return mv;
    }

    //注册方法
    @RequestMapping(value="/regist", method = RequestMethod.POST)
    public String regist(@RequestParam("username")String username,
                         @RequestParam("password")String password,
                         @RequestParam("authority")String authority,
                         Model model){
        Integer isLegal = accountService.checkIsStuLegal(username);
        Account acc = new Account();
        if(authority.equals("2-教师")){       //如果是教师账号注册则直接注册成功就行
            //查看这个账号是否被注册过，如果为0就是没有被注册过
            Integer exist = accountService.checkUsername(username);
            if(exist == 0){         //可以注册
                acc.setUsername(username);
                acc.setPassword(password);
                acc.setAuthority(2);
                accountService.insertAccount(acc);
                return "index";
            } else {                //账号已经存在，不可以注册
                model.addAttribute("msg", "账号已经存在，请换一个账号注册");
                return "regist";
            }
        } else if (authority.equals("1-学生")){   //要检查学生表是否有相应学号
            Integer legal = accountService.checkIsStuLegal(username);
            if(legal == 1){     //合法，直接注册
                acc.setUsername(username);
                acc.setPassword(password);
                acc.setAuthority(1);
                accountService.insertAccount(acc);
                return "index";     //注册成功，跳转到登录页面
            } else {        //不合法，返回错误信息
                model.addAttribute("msg", "请使用已经录入学生表的账号注册");
                return "regist";
            }
        } else {
            System.out.println("这是一种错误的情况，权限信息异常");
            model.addAttribute("errMsg", "AccountController: regist方法，身份信息出现了除了老师和学生外的第三种，匪夷所思...");
            return "error";
        }
    }


}
