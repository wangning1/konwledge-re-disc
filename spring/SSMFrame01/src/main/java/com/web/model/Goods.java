package com.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by wangning on 2017/3/10.
 */

public class Goods extends BaseObject {


    private static final long serialVersionUID = -2967003767664413699L;

    @NotNull(message = "id cannot be null!")
    private Integer id;

    @NotBlank(message = "name cannot be null!")
    @Length(min = 2,message = "cannot less 2 words")
    private String name;

    //这里展示了jackson封装好的以及自定义的对时间格式的转换方式,提高可读性
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
