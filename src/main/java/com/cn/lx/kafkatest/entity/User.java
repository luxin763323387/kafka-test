package com.cn.lx.kafkatest.entity;

import com.cn.lx.kafkatest.dao.UserMapper;
import com.cn.lx.kafkatest.util.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer userid;

    private String username;

    private String password;

    private String phone;

    public User(String username,String password, String phone){
        this.username = username;
        this.password = password;
        this.phone = phone;
    }

    public ErrorCode validate(UserMapper userMapper){

        if(null == userMapper.selectByPrimaryKey(userid)){
            return ErrorCode.USER_NOT_EXIST;
        }
        return ErrorCode.SUCCESS;
    }
}
