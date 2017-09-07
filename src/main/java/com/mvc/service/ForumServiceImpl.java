package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.dao.ForumDao;
import com.mvc.dto.ForumDto;

@Service
public class ForumServiceImpl implements ForumService{
    @Autowired
    ForumDao forumDao = null;

    @Override
    public List<ForumDto> selectForum() {
        return forumDao.selectAllForums();
    }

}
