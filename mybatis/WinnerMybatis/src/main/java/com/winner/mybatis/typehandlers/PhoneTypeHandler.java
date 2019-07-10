package com.winner.mybatis.typehandlers;

import com.winner.model.PhoneNumber;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by wangning on 2017/7/13.
 * 自定义类型处理器
 */
public class PhoneTypeHandler  extends BaseTypeHandler<PhoneNumber>{
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, PhoneNumber phoneNumber, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i,phoneNumber.getAsString());
    }

    public PhoneNumber getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return new PhoneNumber(resultSet.getString(s));
    }

    public PhoneNumber getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return new PhoneNumber(resultSet.getString(i));
    }

    public PhoneNumber getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return new PhoneNumber(callableStatement.getString(i));
    }
}
