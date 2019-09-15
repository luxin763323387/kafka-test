package com.cn.lx.kafkatest.test;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author StevenLu
 * @date 2019-08-28 20:38
 */
public class Test {

    public static void main(String[] args) {

        List<User> users = new ArrayList<>();
        users.add(new User("1","lx",18));
        users.add(new User("2","ls",19));
        users.add(new User("123","ll",20));

        List<String> list = users.stream()
                .map(User::getId)
                .collect(Collectors.toList());
        List<Integer> ages = users.stream()
                .map(User::getAge)
                .collect(Collectors.toList());

        System.out.println(list);
        System.out.println(JSON.toJSON(list));
        System.out.println(ages);
        System.out.println(JSON.toJSON(ages));
    }



    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class User{

        private String id;
        private String name;
        private int age;
    }
}
