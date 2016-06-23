package com.mvc.service;

import java.util.List;

import com.mvc.dto.UserDto;
import com.mvc.model.LoginModel;

public interface UsetService {

    public List<UserDto> getUserList(LoginModel loginModel);

}
