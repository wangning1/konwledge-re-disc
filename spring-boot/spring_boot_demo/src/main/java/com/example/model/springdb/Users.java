package com.example.model.springdb;

import org.apache.ibatis.type.Alias;

/**
 * Created by wangning on 2017/10/23.
 */
@Alias("users")
public class Users {

    private String username;
    private String password;
    private Integer enabled;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }
}
