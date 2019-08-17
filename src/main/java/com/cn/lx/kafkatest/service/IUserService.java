package com.cn.lx.kafkatest.service;

import com.cn.lx.kafkatest.entity.User;
import com.cn.lx.kafkatest.util.Response;

public interface IUserService {

   public int add(User user);

   User queryById(Integer id);

   Response dropUser(User user);

   Response dropUser2(User user);
}

