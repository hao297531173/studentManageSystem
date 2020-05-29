package com.learn.system.service.serviceImpl;

import com.learn.system.mapper.ManageMapper;
import com.learn.system.pojo.ClassInfo;
import com.learn.system.pojo.Student;
import com.learn.system.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ManageServiceImpl implements ManageService {

    @Autowired
    ManageMapper manageMapper;

    @Override
    public int isExistClassNo(String classNo) {
        return manageMapper.isExistClassNo(classNo);
    }

    @Override
    public void insertClassInfo(ClassInfo cla) {
        manageMapper.insertClassInfo(cla);
    }

    @Override
    public int isExistStuNo(String stuNo) {
        return manageMapper.isExistStuNo(stuNo);
    }

    @Override
    public void insertStudentInfo(Student student) {    //这个要改
        manageMapper.updateClassNum(student.getClassNo());
        manageMapper.insertStudentInfo(student);
    }

    @Override
    public List<String> queryAllClassNo() {
        return manageMapper.queryAllClassNo();
    }

    @Override
    public void updateClassNum(String classNo) {
        manageMapper.updateClassNum(classNo);
    }

    @Override
    public void deleteStuByNo(String stuNo) {
        //删除成绩表
        deleteScoreByNo(stuNo);
        //班级人数减一
        updateClassNumM(manageMapper.queryClassNoByStuNo(stuNo));
        //最后删除学生信息
        manageMapper.deleteStuByNo(stuNo);
    }

    @Override
    public void updateClassNumM(String classNo) {
        manageMapper.updateClassNumM(classNo);
    }

    @Override
    public void deleteScoreByNo(String stuNo) {
        manageMapper.deleteScoreByNo(stuNo);
    }

    @Override
    public List<Student> queryAllStudent() {
        return manageMapper.queryAllStudent();
    }

    @Override
    public List<Student> querySomeStudent(List<Student> studentList, int pageNum, int offset) {
        List<Student> list = new ArrayList<Student>();
        int cnt = (pageNum-1)*offset;
        int num=0;
        for(Student li : studentList){
            if(cnt!=0){
                cnt--;
                continue;
            }
            list.add(li);
            num++;
            if(num == offset){
                break;
            }
        }
        return list;
    }


}
