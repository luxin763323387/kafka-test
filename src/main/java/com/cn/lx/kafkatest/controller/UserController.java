package com.cn.lx.kafkatest.controller;

import com.cn.lx.kafkatest.entity.User;
import com.cn.lx.kafkatest.service.IUserService;
import com.cn.lx.kafkatest.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/test/")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public int add(User user) {
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
}
