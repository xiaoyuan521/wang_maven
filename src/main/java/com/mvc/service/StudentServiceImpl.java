package com.mvc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.dao.StudentDao;
import com.mvc.dto.StudentDto;
import com.mvc.model.StudentModel;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentDao studentDao = null;

    @Override
    public List<StudentDto> selectStudent() {
        return studentDao.selectByCondition();
    }

    /**
     * 删除
     */
    @Override
    public void deleteStudent(int student) {
        studentDao.delete(student);
    }

    /**
     * 编辑
     */
    @Override
    public List<StudentDto> getDisplayById(int studentId) {
        return studentDao.selectById(studentId);
    }

    /**
     * 使用Optional返回数据
     */
    @Override
    public Optional<StudentDto> getDisplayById1(int studentId) {
        StudentDto studentDto = null;

        List<StudentDto> studentEditList = studentDao.selectById(studentId);
        if (studentEditList.size() == 0) {
            studentDto = null;
        } else {
            studentDto = studentEditList.get(0);
        }
        return Optional.ofNullable(studentDto);
    }

    /**
     * 新增student
     */
    @Override
    public void addStudent(StudentModel studentModel) {

        studentDao.insert(studentModel);

    }

    /**
     * 更新student
     */
    @Override
    public void updateStudent(StudentModel studentModel) {
        studentDao.update(studentModel);
    }

}
