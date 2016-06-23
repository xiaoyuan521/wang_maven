package com.mvc.dao;

import java.util.List;

import com.mvc.dto.UserDto;
import com.mvc.model.LoginModel;

public interface UserDao {

    public List<UserDto> selectByCondition(LoginModel loginModel);

}
