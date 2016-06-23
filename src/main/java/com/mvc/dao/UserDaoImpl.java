package com.mvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mvc.dto.UserDto;
import com.mvc.model.LoginModel;
import com.mysql.jdbc.StringUtils;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate JdbcTemelate = null;

    public List<UserDto> selectByCondition(LoginModel loginModel) {

        final StringBuilder sql = new StringBuilder();
        sql.append(" SELECT");
        sql.append("  user,");
        sql.append("  password");
        sql.append(" FROM");
        sql.append("  user");
        sql.append(" WHERE");
        sql.append(" 1=1 ");
        if (!StringUtils.isNullOrEmpty(loginModel.getUsername())) {
            sql.append(" and ");
            sql.append(" user=? ");
        }
        if (!StringUtils.isNullOrEmpty(loginModel.getPassword())) {
            sql.append(" and ");
            sql.append(" password=? ");

        }

        Object[] paramer = new Object[]{
                loginModel.getUsername(), loginModel.getPassword()
        };

        List<UserDto> userList = JdbcTemelate.query(sql.toString(), paramer, new UserRowMapper());

        return userList;
    }

    protected class UserRowMapper implements RowMapper<UserDto> {

        @Override
        public UserDto mapRow(ResultSet rs, int paramInt) throws SQLException {

            UserDto UserDto = new UserDto();
            UserDto.setUsername(rs.getString("user"));
            UserDto.setPassword(rs.getString("password"));

            return UserDto;
        }

    }

}
