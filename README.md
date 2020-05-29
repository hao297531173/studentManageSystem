## 学生成绩管理系统

### 数据库设计

使用数据库为scoreDB

#### 班级信息表class

| 字段名    | 描述     | 数据类型    | 约束        |
| :-------- | :------- | ----------- | ----------- |
| classNo   | 班级编号 | char(10)    | primary key |
| className | 班级名称 | varchar(20) | not null    |
| institute | 所属学院 | varchar(20) | not null    |
| grade     | 年级     | int         | not null    |
| classNum  | 班级人数 | int         | not null    |

#### 学生信息表student

| 字段名   | 描述     | 数据类型    | 约束        |
| -------- | -------- | ----------- | ----------- |
| stuNo    | 学号     | char(10)    | primary key |
| stuName  | 姓名     | varchar(20) | not null    |
| sex      | 性别     | int         | not null    |
| birthday | 出生日期 | date        | not null    |
| nat      | 民族     | varchar(20) | not null    |
| classNo  | 所属班级 | varchar(20) | not null    |

#### 课程信息表course

| 字段名      | 属性     | 数据类型    | 约束        |
| ----------- | -------- | ----------- | ----------- |
| courseNo    | 课程号   | char(10)    | primary key |
| courseName  | 课程名   | varchar(20) | not null    |
| credit      | 学分     | int         | not null    |
| courseHour  | 课时数   | int         | not null    |
| priorCourse | 先修课程 | varchar(20) | not null    |

#### 成绩表score

| 字段名   | 属性     | 数据类型 | 约束        |
| -------- | -------- | -------- | ----------- |
| stuNo    | 学号     | char(10) | primary key |
| courseNo | 课程号   | char(10) | primary key |
| term     | 开课学期 | char(10) | not null    |
| score    | 成绩     | int      | not null    |

#### 账号表account

| 字段名    | 描述     | 数据类型    | 约束                         |
| --------- | -------- | ----------- | ---------------------------- |
| id        | 用户id   | int         | primary key ,auto_increament |
| username  | 用户名   | varchar(20) | not null                     |
| password  | 密码     | varchar(20) | not null                     |
| authority | 用户权限 | int         | not null                     |

#### 详细建表sql

```sql
use scoreDB;

/*  删除数据库中所有的表 */
SELECT concat('DROP TABLE IF EXISTS' , table_name, ';')
FROM information_schema.tables
WHERE table_schema = 'scoreDB';


/* 班级信息表class */
drop table IF EXISTS class;
 CREATE TABLE class(
    classNo CHAR(10) PRIMARY KEY ,
    className VARCHAR (20) NOT NULL,
    institute VARCHAR (20) NOT NULL,
    grade INT NOT NULL,
    classNum INT NOT NULL
 );

/*  插入班级信息 */
INSERT INTO class (classNo, className, institute, grade, classNum) VALUES
                          ('20011', '通信201','通信学院',1, 1);

 /* 学生信息表student */
 /* sex：0-未知 1-男 2-女 */
drop table IF EXISTS student;
CREATE TABLE student(
    stuNo CHAR(10) PRIMARY KEY,
    stuName VARCHAR (20) NOT NULL,
    sex INT NOT NULL,
    birthday DATE NOT NULL,
    nat VARCHAR(20) NOT NULL,
    classNo VARCHAR (20) NOT NULL
);

/*  插入学生信息 */
INSERT INTO student (stuNo, stuName, sex, birthday, nat, classNo) VALUES
                          ('20066','王兰花',2,'2000-01-01','汉族','20011');
INSERT INTO student (stuNo, stuName, sex, birthday, nat, classNo) VALUES
                          ('20068','李子豪',1,'2000-06-06','汉族','20011');
INSERT INTO student (stuNo, stuName, sex, birthday, nat, classNo) VALUES
                          ('20088','苏梅',2,'2000-02-12','汉族','20011');

/*  重新计算班级人数 */
UPDATE class SET classNum = (select count(*) from  student where classNo='20011')
                            where classNo='20011';



/*  课程信息表course */
drop TABLE  IF EXISTS course;
CREATE TABLE course(
    courseNo CHAR(10) PRIMARY KEY,
    courseName VARCHAR (20) NOT NULL,
    credit INT NOT NULL,
    courseHour INT NOT NULL,
    priorCourse VARCHAR (20) NOT NULL
);

/*  插入课程信息 */
INSERT INTO course (courseNo, courseName, credit, courseHour, priorCourse) VALUES
                    ('ke-001','高等数学',4, 60, 'null');
INSERT INTO course (courseNo, courseName, credit, courseHour, priorCourse) VALUES
                    ('ke-002','线性代数',4, 60, 'null');
INSERT INTO course (courseNo, courseName, credit, courseHour, priorCourse) VALUES
                    ('ke-003','通信原理',4, 60, '高等数学');
INSERT INTO course (courseNo, courseName, credit, courseHour, priorCourse) VALUES
                    ('ke-004','电子技术基础',4, 60, '高等数学');
INSERT INTO course (courseNo, courseName, credit, courseHour, priorCourse) VALUES
                    ('ke-005','电工学',4, 60, '高等数学');
INSERT INTO course (courseNo, courseName, credit, courseHour, priorCourse) VALUES
                    ('ke-006','自动控制原理',4, 60, '高等数学');

/*  成绩表score */
drop TABLE  IF EXISTS score;
CREATE TABLE score(
    stuNo CHAR(10) ,
    courseNo CHAR(10) ,
    term VARCHAR (10) NOT NULL,
    score INT NOT NULL,
    PRIMARY KEY (stuNo, courseNo)
);

/*  插入成绩信息 */
INSERT INTO score (stuNo, courseNo, term, score) VALUES
                  ('20066', 'ke-001', '2020年春季学期', 80);
INSERT INTO score (stuNo, courseNo, term, score) VALUES
                  ('20066', 'ke-002', '2020年春季学期', 88);
INSERT INTO score (stuNo, courseNo, term, score) VALUES
                  ('20066', 'ke-003', '2020年春季学期', 88);
INSERT INTO score (stuNo, courseNo, term, score) VALUES
                  ('20066', 'ke-004', '2020年春季学期', 88);
INSERT INTO score (stuNo, courseNo, term, score) VALUES
                  ('20066', 'ke-005', '2020年春季学期', 88);
                  INSERT INTO score (stuNo, courseNo, term, score) VALUES
                  ('20066', 'ke-006', '2020年春季学期', 88);


INSERT INTO score (stuNo, courseNo, term, score) VALUES
                  ('20068', 'ke-001', '2020年春季学期', 60);
INSERT INTO score (stuNo, courseNo, term, score) VALUES
                  ('20068', 'ke-002', '2020年春季学期', 68);
INSERT INTO score (stuNo, courseNo, term, score) VALUES
                  ('20068', 'ke-003', '2020年春季学期', 68);
INSERT INTO score (stuNo, courseNo, term, score) VALUES
                  ('20068', 'ke-004', '2020年春季学期', 68);
INSERT INTO score (stuNo, courseNo, term, score) VALUES
                  ('20068', 'ke-005', '2020年春季学期', 68);
INSERT INTO score (stuNo, courseNo, term, score) VALUES
                  ('20068', 'ke-006', '2020年春季学期', 68);

INSERT INTO score (stuNo, courseNo, term, score) VALUES
                  ('20088', 'ke-001', '2020年春季学期', 90);
INSERT INTO score (stuNo, courseNo, term, score) VALUES
                  ('20088', 'ke-002', '2020年春季学期', 98);
INSERT INTO score (stuNo, courseNo, term, score) VALUES
                  ('20088', 'ke-003', '2020年春季学期', 98);
INSERT INTO score (stuNo, courseNo, term, score) VALUES
                  ('20088', 'ke-004', '2020年春季学期', 98);
INSERT INTO score (stuNo, courseNo, term, score) VALUES
                  ('20088', 'ke-005', '2020年春季学期', 98);
INSERT INTO score (stuNo, courseNo, term, score) VALUES
                  ('20088', 'ke-006', '2020年春季学期', 98);

/*  用户表user */
/*  authority是权限字段，共设两个值 1-学生 2-教师 */
drop TABLE IF EXISTS account;
CREATE TABLE account(
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(20) NOT NULL,
    password VARCHAR(20) NOT NULL,
    authority INT NOT NULL
);
/*  用户表插入值 */
INSERT INTO account (username, password, authority) VALUES ('20066', '666666', 1);
INSERT INTO account (username, password, authority) VALUES ('20068', '666666', 1);
INSERT INTO account (username, password, authority) VALUES ('20088', '666666', 1);
INSERT INTO account (username, password, authority) VALUES ('gong-001', '666666', 2);
```



### 模块设计

#### 账号模块

登录模块用到的是account表，里面记录了用户名，密码，还有用户想权限信息，目前使用两种权限，1-学生，2-教师。不同的权限可以使用不同的功能。

##### 注册账号

学生注册账号的时候使用的账号必须是student学生表中已经有的学号，此后，这个账号只能查询相对应学生信息；老师注册账号的时候账号不能重复

##### 主要代码（accountMapper.xml）

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.learn.system.mapper.AccountMapper">

    <select id="checkUsername" parameterType="String" resultType="int">
        select count(*) from account where username = #{username}
    </select>

    <insert id="insertAccount" parameterType="account">
        insert into account
        (username, password, authority) values
        (#{username}, #{password}, #{authority})
    </insert>

    <select id="checkPassword" parameterType="String" resultType="String">
        select password from account where username = #{username}
    </select>

    <select id="getAuthority" parameterType="String" resultType="int">
        select authority from account where username = #{username}
    </select>

    <select id="checkIsStuLegal" parameterType="String" resultType="int">
        select count(*) from student where stuNo = #{username}
    </select>
</mapper>
```

#### 学生可以使用的功能

- 查看个人信息 
- 查看培养计划（选修的课程）
- 查看成绩单

##### 主要代码(studentMapper.xml)

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.learn.system.mapper.StudentMapper">

    <select id="selectStu" parameterType="String" resultType="Student">
        select * from student where stuNo = #{stuNo}
    </select>

    <select id="selectAllStu" resultType="Student">
        select * from student
    </select>

    <select id="calStuSum" parameterType="String" resultType="int">
        select sum(score) from score where stuNo=#{stuNo}
    </select>

    <select id="getScoreNum" parameterType="String" resultType="int">
        select count(*) from score where stuNo = #{stuNo}
    </select>

    <select id="calStuCredit" parameterType="String" resultType="int">
        select sum(credit) from course
        where courseNo in
        (select DISTINCT courseNo from score where stuNo = #{stuNo})
    </select>

    <select id="queryAllCourse" parameterType="String" resultType="Course">
        select * from course where courseNo IN
        (select courseNo from student where stuNo = #{stuNo})
    </select>

    <select id="queryAllScore" parameterType="String" resultType="Score">
        select score.stuNo as stuNo,
               score.courseNo as courseNo,
               score.term as term,
               score.score as score,
               course.courseName as courseName
        from score left join course on
               score.courseNo = course.courseNo
        where score.stuNo = #{stuNo}
    </select>
</mapper>
```

![查看个人信息](https://github.com/hao297531173/studentManageSystem/blob/master/image/1.PNG)

![查看培养计划](https://github.com/hao297531173/studentManageSystem/blob/master/image/2.PNG)

![查看成绩单](https://github.com/hao297531173/studentManageSystem/blob/master/image/3.PNG)

#### 老师可以使用的功能

- 添加班级表

- 添加学生信息

- 删除学生信息

  

##### 主要代码(manageMapper.xml)

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.learn.system.mapper.ManageMapper">

    <select id="isExistClassNo" parameterType="String" resultType="int">
        select count(*) from class where classNo = #{classNo}
    </select>

    <insert id="insertClassInfo" parameterType="ClassInfo">
        insert into class (classNo, className, institute, grade, classNum) values
                          (#{classNo}, #{className}, #{institute}, #{grade}, 0)
    </insert>

    <select id="isExistStuNo" parameterType="String" resultType="int">
        select count(*) from student where stuNo = #{stuNo}
    </select>

    <insert id="insertStudentInfo" parameterType="Student">
        insert into student (stuNo, stuName, sex, birthday, nat, classNo) values
                            (#{stuNo}, #{stuName}, #{sex}, #{birthday}, #{nat}, #{classNo})
    </insert>

    <select id="queryAllClassNo" resultType="String">
        select classNo from class
    </select>

    <update id="updateClassNum" parameterType="String">
        update class set classNum = classNum+1 where classNo = #{classNo}
    </update>

    <delete id="deleteStuByNo" parameterType="String">
        delete from student where stuNo = #{stuNo}
    </delete>

    <update id="updateClassNumM" parameterType="String">
        update class set classNum = classNum-1 where classNo = #{classNo}
    </update>

    <delete id="deleteScoreByNo" parameterType="String">
        delete from score where stuNo = #{stuNo}
    </delete>

    <select id="queryClassNoByStuNo" parameterType="String" resultType="String">
        select classNo from student where stuNo = #{stuNo}
    </select>

    <select id="queryAllStudent" resultType="Student">
        select * from student
    </select>

</mapper>
```

![写入班级信息](https://github.com/hao297531173/studentManageSystem/blob/master/image/4.PNG)

![写入学生信息](https://github.com/hao297531173/studentManageSystem/blob/master/image/5.png)

![删除学生信息](https://github.com/hao297531173/studentManageSystem/blob/master/image/6.png)

#### 分页写法

在这个项目中，分页是在service层进行的，在mapper层查出所有的数据，然后再service层对List集合进行裁剪（这种方法很粗糙，会产生性能问题，并且大数据下可能会发生数据溢出）

写法摘录如下

##### StudentServiceImpl.java

```java
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
```

##### StudentController.java

```java
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
```

##### checkCourse.html

```html
<table class="table table-bordered">
                <tr >
                    <td>课程号</td>
                    <td>课程名</td>
                    <td>学分</td>
                    <td>课时数</td>
                    <td>先修课程</td>
                </tr>
                <tr th:each="list : ${courstList}">
                    <td th:text="${list.courseNo}"></td>
                    <td th:text="${list.courseName}"></td>
                    <td th:text="${list.credit}"></td>
                    <td th:text="${list.courseHour}"></td>
                    <td th:text="${list.priorCourse}"></td>
                </tr>
                <!--下面是进行翻页的控件-->
                <tr>
                    <td>
                        <a th:href="@{/checkPlan/{pageNum}(pageNum=1)}">首页</a>
                    </td>
                    <td th:if="${pageNum}!=1">
                        <a th:href="@{/checkPlan/{pageNum}(pageNum=${pageNum}-1)}">上一页</a>
                    </td>
                    <td th:if="${pageNum}!=${totalPage}">
                        <a th:href="@{/checkPlan/{pageNum}(pageNum=${pageNum}+1)}">下一页</a>
                    </td>
                    <td>
                        <a th:href="@{/checkPlan/{pageNum}(pageNum=${totalPage})}">尾页</a>
                    </td>
                    <td>
                        第<span th:text="${pageNum}"></span>页/<span th:text="${totalPage}"></span>页
                    </td>
                </tr>
            </table>
```

#### 项目结构

 ├─main
 │  ├─java
 │  │  └─com
 │  │      └─learn
 │  │          └─system
 │  │              │  SystemApplication.java
 │  │              │  
 │  │              ├─controller
 │  │              │      AccountController.java
 │  │              │      ManageController.java
 │  │              │      StudentController.java
 │  │              │      
 │  │              ├─mapper
 │  │              │      AccountMapper.java
 │  │              │      ManageMapper.java
 │  │              │      StudentMapper.java
 │  │              │      
 │  │              ├─pojo
 │  │              │      Account.java
 │  │              │      ClassInfo.java
 │  │              │      Course.java
 │  │              │      Score.java
 │  │              │      Student.java
 │  │              │      
 │  │              └─service
 │  │                  │  AccountService.java
 │  │                  │  ManageService.java
 │  │                  │  StudentService.java
 │  │                  │  
 │  │                  └─serviceImpl
 │  │                          AccountServiceImpl.java
 │  │                          ManageServiceImpl.java
 │  │                          StudentServiceImpl.java
 │  │                          
 │  └─resources
 │      │  application.properties
 │      │  
 │      ├─mapper
 │      │      accountMapper.xml
 │      │      manageMapper.xml
 │      │      studentMapper.xml
 │      │      
 │      ├─static
 │      │  ├─css
 │      │  │      bootstrap-grid.css
 │      │  │      bootstrap-grid.css.map
 │      │  │      bootstrap-grid.min.css
 │      │  │      bootstrap-grid.min.css.map
 │      │  │      bootstrap-reboot.css
 │      │  │      bootstrap-reboot.css.map
 │      │  │      bootstrap-reboot.min.css
 │      │  │      bootstrap-reboot.min.css.map
 │      │  │      bootstrap.css
 │      │  │      bootstrap.css.map
 │      │  │      bootstrap.min.css
 │      │  │      bootstrap.min.css.map
 │      │  │      
 │      │  └─js
 │      │          bootstrap.bundle.js
 │      │          bootstrap.bundle.js.map
 │      │          bootstrap.bundle.min.js
 │      │          bootstrap.bundle.min.js.map
 │      │          bootstrap.js
 │      │          bootstrap.js.map
 │      │          bootstrap.min.js
 │      │          bootstrap.min.js.map
 │      │          jquery-3.2.1.js
 │      │          
 │      └─templates
 │              checkCourse.html
 │              checkScore.html
 │              deleteStu.html
 │              index.html
 │              insertStuInfo.html
 │              regist.html
 │              welcome.html
 │              模板.html
 │              
 └─test
     └─java
         └─com
             └─learn
                 └─system
                         SystemApplicationTests.java

#### 控制器代码

##### AccountController.java

```java
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
```

##### ManageController.java

```java
@Controller
public class ManageController {
    @Autowired
    ManageServiceImpl manageService;

    @RequestMapping(value = "/insertClass", method = RequestMethod.POST)
    public ModelAndView insertClass(@RequestParam("classNo")String classNo,
                                    @RequestParam("className")String className,
                                    @RequestParam("institute")String institute,
                                    @RequestParam("grade")int grade,
                                    ModelAndView mv, HttpSession session){
        //先检查是否存在相同的班级号
        int isExist = manageService.isExistClassNo(classNo);
        mv.addObject("userType", session.getAttribute("userType"));
        mv.addObject("userName", session.getAttribute("stuNo"));
        if(isExist == 1){           //存在，不能插入
            mv.addObject("isExist", 1);
            mv.setViewName("welcome");
        } else {
            ClassInfo cla = new ClassInfo(classNo, className, institute, grade);
            manageService.insertClassInfo(cla);
            mv.addObject("success", 1);
            mv.setViewName("welcome");
        }
        return mv;
    }

    //get方式跳转，不用接受数据
    @RequestMapping(value = "/insertClass", method = RequestMethod.GET)
    public ModelAndView insertClass(ModelAndView mv, HttpSession session){
        mv.addObject("userType", session.getAttribute("userType"));
        mv.addObject("userName", session.getAttribute("stuNo"));
        mv.setViewName("welcome");
        return mv;
    }

    //跳转函数，跳转到学生插入页面都要通过这个函数获取可供选择的班级号
    @RequestMapping("/toInsertStuInfo")
    public String toInsertStuInfo(Model model, HttpSession session){
        model.addAttribute("userType", session.getAttribute("userType"));
        model.addAttribute("userName", session.getAttribute("stuNo"));
        List<String> str = new ArrayList<String>();
        str = manageService.queryAllClassNo();      //获取所有的班级号
        model.addAttribute("classNo", str);
        return "insertStuInfo";
    }

    @RequestMapping(value = "/insertStu", method = RequestMethod.POST)
    public ModelAndView insertStu(@RequestParam("stuNo") String stuNo,
                                  @RequestParam("stuName") String stuName,
                                  @RequestParam("nat") String nat,
                                  @RequestParam("birthday") String birthday,
                                  @RequestParam("sex") int sex,
                                  @RequestParam("classNo")String classNo,
                                  ModelAndView mv, HttpSession session) throws ParseException {
        mv.addObject("userType", session.getAttribute("userType"));
        mv.addObject("userName", session.getAttribute("stuNo"));
        List<String> str = new ArrayList<String>();
        str = manageService.queryAllClassNo();      //获取所有的班级号
        mv.addObject("classNo", str);
        int isExist = manageService.isExistStuNo(stuNo);
        if(isExist != 0){   //存在
            mv.addObject("isExist", 1);
        } else {
            //将生日字符串转换成date类型
            SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new java.sql.Date(simple.parse(birthday).getTime());
            Student stu = new Student(stuNo, stuName, sex,date, nat, classNo);
            manageService.insertStudentInfo(stu);
            mv.addObject("success", 1);
        }
        mv.setViewName("insertStuInfo");
        return mv;
    }

    //deleteStu显示函数
    @RequestMapping(value="/deleteStu/{pageNum}" , method = RequestMethod.GET)
    public ModelAndView deleteStu(@PathVariable(value="pageNum")int pageNum,
                                  ModelAndView mv, HttpSession session){
        mv.addObject("userType", session.getAttribute("userType"));
        mv.addObject("userName", session.getAttribute("stuNo"));
        int offset = 5;     //每页显示的数量
        int total = manageService.queryAllStudent().size(); //学生信息总数
        int totalPage = total/offset;
        if(total%offset !=0){
            totalPage++;
        }
        if(totalPage == 0){
            totalPage=1;
        }
        mv.addObject("totalPage", totalPage);
        mv.addObject("pageNum", pageNum);
        List<Student> studentList = new ArrayList<Student>();
        studentList = manageService.querySomeStudent(manageService.queryAllStudent(),
                pageNum, offset);
        mv.addObject("studentList", studentList);
        mv.setViewName("deleteStu");
        return mv;
    }

    //删除函数
    @RequestMapping(value = "/delete/{stuNo}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable("stuNo")String stuNo,
                               ModelAndView mv, HttpSession session){
        manageService.deleteStuByNo(stuNo);
        mv.setViewName("redirect:/deleteStu/1"); //转发请求
        return mv;
    }


}
```

##### StudentController.java

```java
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
```



#### 实践的经验总结

##### Model和Session

我之前不了解这两者之间的区别，这次实践之后我明白了，Model里面的变量的生命周期是一次request，也就是说你用一个控制器跳转到另一个控制器可能Model里面的数据就找不到了，但是Session的数据是可以在控制器之间共享的，声明周期没有记错的话应该是一次访问（也就是tcp建立开始到结束，或者设定的时间片用完）

##### 一定要好好学sql

后端编程基本就是存取数据，然后将数据按照相应的格式发送给前端，反正就是和数据打交道

##### 项目架构很重要

其实就是软件工程里面说的 **高内聚，低耦合**，我能感觉出来我挺多地方代码写的不好，但是懒得改了，懒~~ヽ(ー_ー)ノ

