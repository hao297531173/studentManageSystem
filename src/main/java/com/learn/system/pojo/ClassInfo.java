package com.learn.system.pojo;

public class ClassInfo {
    String classNo;
    String className;
    String institute;
    int grade;

    public ClassInfo(String classNo, String className, String institute, int grade) {
        this.classNo = classNo;
        this.className = className;
        this.institute = institute;
        this.grade = grade;
    }

    public ClassInfo() {
    }

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "ClassInfo{" +
                "classNo='" + classNo + '\'' +
                ", className='" + className + '\'' +
                ", institute='" + institute + '\'' +
                ", grade=" + grade +
                '}';
    }
}
