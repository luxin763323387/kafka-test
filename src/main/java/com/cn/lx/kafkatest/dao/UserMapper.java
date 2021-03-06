package com.cn.lx.kafkatest.dao;

import com.cn.lx.kafkatest.entity.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    public int insertUsers(List<User> users);

}
