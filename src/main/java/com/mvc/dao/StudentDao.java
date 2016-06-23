package com.mvc.dao;

import java.util.List;

import com.mvc.dto.StudentDto;
import com.mvc.model.StudentModel;

public interface StudentDao {

    public List<StudentDto> selectByCondition();

    public void delete(int studentId);

    public List<StudentDto> selectById(int studentId);

    public void insert(StudentModel studentModel);

    public void update(StudentModel studentModel);

}
