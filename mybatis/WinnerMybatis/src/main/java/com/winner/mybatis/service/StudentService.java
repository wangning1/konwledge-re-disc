package com.winner.mybatis.service;

import com.winner.model.Student;
import com.winner.mybatis.mapper.StudentMapper;
import com.winner.mybatis.utils.MyBatisSqlSessionFactory;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangning on 2017/7/13.
 */
public class StudentService {

    private final static Logger logger = LoggerFactory.getLogger(StudentService.class);

    /**
     * 方式一 ： 通过mapper的方式进行操作
     *
     * @return
     */
    public List<Student> getAllStudent() {
        SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = studentMapper.findAllStudents();
        return students;
    }


    /**
     * 方式二 ： 直接通过SQLsession的方式进行操作
     * 使用原始的方式
     */
    public List<Student> getAllStudentByAnotherWay() {
        SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSession();
        try {
            List<Student> students = sqlSession.selectList("com.winner.mybatis.mapper.StudentMapper.findAllStudents");
            return students;
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 使用ResultSetHandler自定义结果集ResultSet处理
     * @return
     */
    public Map<Integer, String> getStudentIdAndName() {
        SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSession();
        final Map<Integer, String> result = new HashMap<Integer, String>();
        sqlSession.select("com.winner.mybatis.mapper.StudentMapper.findAllStudents",
                new ResultHandler() {
                    public void handleResult(ResultContext resultContext) {
                        Student student = (Student) resultContext.getResultObject();
                        logger.info("" + student.getStudentId() + " - " + student.getName());
                        result.put(student.getStudentId(),student.getName());
                    }
                });
        return result;
    }

    public Student getStudentById(Integer id) {
        SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSession();
        try {
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            Student student = studentMapper.findStudentById(id);
            return student;
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 注意需要提交 commit
     *
     * @param student
     */
    public void addStudent(Student student) {
        SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSession();
        try {
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            studentMapper.insertStudent(student);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }

    }

    /**
     * 基于注解SQL映射
     */

    public Student getStudnet(Integer id){
        SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSession();
        try {
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            Student student = studentMapper.getStudentById(id);
            return  student;
        }finally {
            sqlSession.close();
        }
    }

}
