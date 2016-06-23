package com.mvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mvc.dto.StudentDto;
import com.mvc.model.StudentModel;

@Repository
public class StudentDaoImpl implements StudentDao {

    @Autowired
    private JdbcTemplate JdbcTemelate = null;

    /**
     * 检索
     */
    @Override
    public List<StudentDto> selectByCondition() {

        final StringBuilder sql = new StringBuilder();
        sql.append(" SELECT");
        sql.append(" student_id, ");
        sql.append(" username, ");
        sql.append(" age, ");
        sql.append(" score, ");
        sql.append(" gender ");
        sql.append(" FROM");
        sql.append("  student");
        sql.append(" WHERE");
        sql.append(" 1=1 ");

        Object[] paramer = new Object[]{};

        List<StudentDto> studentList = JdbcTemelate.query(sql.toString(), paramer, new StudentRowMapper());

        return studentList;
    }

    /**
     * 编辑
     *
     * @param studentId
     * @return
     */
    @Override
    public List<StudentDto> selectById(int studentId) {

        final StringBuilder sql = new StringBuilder();
        sql.append(" SELECT");
        sql.append(" student_id, ");
        sql.append(" username, ");
        sql.append(" age, ");
        sql.append(" score, ");
        sql.append(" gender ");
        sql.append(" FROM");
        sql.append("  student");
        sql.append(" WHERE");
        sql.append(" 1=1 ");
        sql.append(" AND ");
        sql.append(" student_id=? ");

        Object[] paramer = new Object[]{studentId};

        List<StudentDto> studentList = JdbcTemelate.query(sql.toString(), paramer, new StudentRowMapper());

        return studentList;
    }

    protected class StudentRowMapper implements RowMapper<StudentDto> {

        @Override
        public StudentDto mapRow(ResultSet rs, int paramInt) throws SQLException {

            StudentDto studentDto = new StudentDto();
            studentDto.setStudentId(rs.getInt("student_id"));
            studentDto.setUsername(rs.getString("username"));
            studentDto.setAge(rs.getString("age"));
            studentDto.setScore(rs.getString("score"));
            studentDto.setGender(rs.getString("gender"));

            return studentDto;
        }
    }

    /**
     * 删除
     */
    @Override
    public void delete(int studentId) {
        final StringBuilder sql = new StringBuilder();
        sql.append(" DELETE");
        sql.append(" FROM");
        sql.append("  student");
        sql.append(" WHERE");
        sql.append(" 1=1 ");
        sql.append(" AND ");
        sql.append(" student_id=? ");

        Object[] paramer = new Object[]{studentId};
        JdbcTemelate.update(sql.toString(), paramer);
    }

    /**
     * 插入
     */
    @Override
    public void insert(StudentModel studentModel) {

        final StringBuilder sql = new StringBuilder();

        sql.append(" INSERT INTO ");
        sql.append(" student ");
        sql.append(" ( ");
        sql.append(" username, ");
        sql.append(" age, ");
        sql.append(" score, ");
        sql.append(" gender ");
        sql.append(" ) ");
        sql.append(" values ");
        sql.append(" ( ");
        sql.append(" ?, ");
        sql.append(" ?, ");
        sql.append(" ?, ");
        sql.append(" ? ");
        sql.append(" ) ");

        Object[] paramer = new Object[]{studentModel.getUsername(), studentModel.getAge(), studentModel.getScore(), studentModel.getGender()};
        JdbcTemelate.update(sql.toString(), paramer);

    }

    /**
     * 更新
     */
    @Override
    public void update(StudentModel studentModel) {
        final StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE student");
        sql.append(" SET");
        sql.append(" username= ?, ");
        sql.append(" age= ?,");
        sql.append(" score= ?, ");
        sql.append(" gender= ? ");
        sql.append(" WHERE");
        sql.append(" student_id= ? ");
        System.out.println(sql.toString());
        Object[] paramer = new Object[]{studentModel.getUsername(), studentModel.getAge(), studentModel.getScore(), studentModel.getGender(), studentModel.getStudentId()};

        JdbcTemelate.update(sql.toString(), paramer);
    }
}
