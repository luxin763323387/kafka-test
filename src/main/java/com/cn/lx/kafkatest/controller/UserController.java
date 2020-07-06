package com.cn.lx.kafkatest.controller;

import com.alibaba.fastjson.JSON;
import com.cn.lx.kafkatest.entity.User;
import com.cn.lx.kafkatest.service.IUserService;
import com.cn.lx.kafkatest.util.Response;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping(value = "/test/")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public int add(@RequestBody User user) {
        return userService.add(user);
    }

    @RequestMapping(value = "queryUserInfo", method = RequestMethod.GET)
    public User queryUserInfo(@RequestParam(name = "id") Integer id) {
        return userService.queryById(id);
    }

    @PostMapping("post")
    public Response postUser(@RequestBody User user){
        return userService.dropUser(user);
    }

    @GetMapping("redisGet")
    public void redisGet(){
        stringRedisTemplate.opsForList().leftPush("a","a");

        String b = "b";
        String a = "a";
        String index = stringRedisTemplate.opsForList().index(a, 0);
        System.out.println(index);
        System.out.println(index.equals(b));
    }
}
