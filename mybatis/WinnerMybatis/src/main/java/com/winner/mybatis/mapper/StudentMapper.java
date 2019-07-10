package com.winner.mybatis.mapper;

import com.winner.model.Student;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by wangning on 2017/7/13.
 */
public interface StudentMapper {

    public List<Student> findAllStudents();

    public Student findStudentById(Integer id);

    public void insertStudent(Student student);

    @Select("select * from student where student_id = #{id}")
    public Student getStudentById(Integer id);

}
