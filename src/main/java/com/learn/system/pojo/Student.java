package com.learn.system.pojo;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Student {

        String stuNo;
        String stuName;
        Integer sex;
        Date birthday;
        String nat;
        String classNo;

        public Student() {
        }

        public Student(String stuNo, String stuName, Integer sex, Date birthday, String nat, String classNo) {
            this.stuNo = stuNo;
            this.stuName = stuName;
            this.sex = sex;
            this.birthday = birthday;
            this.nat = nat;
            this.classNo = classNo;
        }

        public String getStuNo() {
            return stuNo;
        }

        public void setStuNo(String stuNo) {
            this.stuNo = stuNo;
        }

        public String getStuName() {
            return stuName;
        }

        public void setStuName(String stuName) {
            this.stuName = stuName;
        }

        public Integer getSex() {
            return sex;
        }

        public void setSex(Integer sex) {
            this.sex = sex;
        }

        //这里我改了返回值
        public String getBirthday() {
            SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
            String date = simple.format(birthday);
            return date;
        }

        public void setBirthday(Date birthday) {
            this.birthday = birthday;
        }

        public String getNat() {
            return nat;
        }

        public void setNat(String nat) {
            this.nat = nat;
        }

        public String getClassNo() {
            return classNo;
        }

        public void setClassNo(String classNo) {
            this.classNo = classNo;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "stuNo='" + stuNo + '\'' +
                    ", stuName='" + stuName + '\'' +
                    ", sex=" + sex +
                    ", birthday=" + birthday +
                    ", nat='" + nat + '\'' +
                    ", classNo='" + classNo + '\'' +
                    '}';
        }

}
