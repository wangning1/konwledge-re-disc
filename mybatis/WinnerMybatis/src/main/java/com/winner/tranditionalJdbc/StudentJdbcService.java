package com.winner.tranditionalJdbc;


import com.winner.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by wangning on 2017/7/13.
 */
public class StudentJdbcService {

    private final static Logger logger = LoggerFactory.getLogger(StudentJdbcService.class);

    public static void main(String[] args) throws Exception {
        StudentJdbcService jdbcService = new StudentJdbcService();
        System.out.println(jdbcService.getDatebaseConnection());
        Student student = jdbcService.findStudentByid(1);
        System.out.println(student.toString());
    }

    public Student findStudentByid(Integer studentId) {
        Student student = null;
        Connection connection = null;
        try {
            //获取数据库连接
            connection = getDatebaseConnection();
            String sql = "Select * from student where student_id = ?";
            //创建
            PreparedStatement statement = connection.prepareStatement(sql);
            //设置输入参数
            statement.setInt(1, studentId);
            ResultSet resultSet = statement.executeQuery();
            logger.info(" " + resultSet);
            if (resultSet.next()) {
                student = new Student();
                student.setStudentId(resultSet.getInt("student_id"));
                student.setName(resultSet.getString("name"));
                student.setEmail(resultSet.getString("email"));
                student.setDob(resultSet.getDate("dob"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return student;
    }

    public Connection getDatebaseConnection() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/winner_mybatis", "root", "pwrd123456");
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
