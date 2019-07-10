package com.example.mapper.winnerdb;

import com.example.model.winnerdb.Student;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by wangning on 2017/11/3.
 */
public interface StudentMapper {

    @Select("select * from student")
    public List<Student> getAll();
}
