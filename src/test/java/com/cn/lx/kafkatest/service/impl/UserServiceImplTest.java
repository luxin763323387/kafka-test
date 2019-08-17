package com.cn.lx.kafkatest.service.impl;

import com.alibaba.fastjson.JSON;
import com.cn.lx.kafkatest.entity.User;
import com.cn.lx.kafkatest.util.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    @Resource
    private UserServiceImpl userService;

    @Test
    public void add() {
    }

    @Test
    public void queryById() {
        User user = userService.queryById(1001);
        System.out.println(user);
    }

    @Test
    public void dropUser() {

        User user = new User();
        user.setUserid(1002);
        user.setUsername("asdada");
        user.setPassword("123456");
        user.setPhone("123456");

        Response response = userService.dropUser2(user);
        System.out.println(JSON.toJSONString(response));
    }
}
