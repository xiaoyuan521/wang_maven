package com.mvc.dao;

import java.util.List;

import com.mvc.dto.UserDto;
import com.mvc.model.LoginModel;
import com.mvc.model.StudentModel;


public interface UserDao {


    public List<UserDto> selectByCondition(LoginModel loginModel);

    public void insert(StudentModel studentModel);
}
