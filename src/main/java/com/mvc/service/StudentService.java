package com.mvc.service;

import java.util.List;
import java.util.Optional;

import com.mvc.dto.StudentDto;
import com.mvc.model.StudentModel;

public interface StudentService {

    public List<StudentDto> selectStudent(StudentModel studentModel);

    public void deleteStudent(int studentId);

    public List<StudentDto> getDisplayById(int studentId);

    public Optional<StudentDto> getDisplayById1(int studentId);

    public void addStudent(StudentModel studentModel);

    public void updateStudent(StudentModel studentModel);

}
