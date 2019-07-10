package com.example.service;

import com.example.mapper.springdb.UsersMapper;
import com.example.mapper.winnerdb.StudentMapper;
import com.example.model.springdb.Users;
import com.example.model.winnerdb.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by wangning on 2017/10/23.
 */
@Service
public class UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private StudentMapper studentMapper;

    public List<Users> getUsers(){
        String sql = "select username,password,enabled from users";
        return jdbcTemplate.query(sql, new RowMapper<Users>() {
            @Override
            public Users mapRow(ResultSet resultSet, int i) throws SQLException {
                Users users = new Users();
                users.setUsername(resultSet.getString("username"));
                users.setPassword(resultSet.getString("password"));
                users.setEnabled(resultSet.getInt("enabled"));
                return users;
            }
        });
    }

    public List<Users> getUsersByMapper(){
        return usersMapper.getAll();
    }

    public Users getUsersByUsername(String username){
        return usersMapper.getByUsername(username);
    }

    public List<Student> getAllStudents(){
        return studentMapper.getAll();
    }




}
