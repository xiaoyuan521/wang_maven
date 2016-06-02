package com.mvc.service;

import java.util.List;

import com.mvc.dto.UserDto;
import com.mvc.model.LoginModel;
import com.mvc.model.StudentModel;

public interface UsetService {

    public List<UserDto> getUserList(LoginModel loginModel);

    public void addStudent(StudentModel studentModel);


}
