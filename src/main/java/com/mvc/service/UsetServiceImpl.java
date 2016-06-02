package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.dao.UserDao;
import com.mvc.dto.UserDto;
import com.mvc.model.LoginModel;
import com.mvc.model.StudentModel;

@Service
public class UsetServiceImpl implements UsetService {

    @Autowired
    UserDao userDao = null;

    public List<UserDto> getUserList(LoginModel loginModel) {

        if (loginModel == null) {
            loginModel = new LoginModel();
        }

        return userDao.selectByCondition(loginModel);
    }

    @Override
    public void addStudent(StudentModel studentModel) {

        userDao.insert(studentModel);



    }

}
