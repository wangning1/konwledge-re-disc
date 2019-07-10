package com.example.mapper.springdb;

import com.example.model.springdb.Users;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by wangning on 2017/10/24.
 */
public interface UsersMapper {

    @Select("select * from users")
//    @Results({
//            @Result(property = "username", column = "username"),
//            @Result(property = "password", column = "password"),
//            @Result(property = "enabled", column = "enabled")
//    })
    List<Users> getAll();

    Users getByUsername(String username);

}
