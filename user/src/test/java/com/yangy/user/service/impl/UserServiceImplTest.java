//package com.yangy.user.service.impl;
//
//import com.alibaba.fastjson.JSON;
//import User;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class UserServiceImplTest {
//
//    @org.junit.Test
//    public void save() {
//        User user = new User();
//        user.setSex(100);
//        user.setUsername("yangy2");
//        user.setNickname("yangy2");
//        long birth = System.currentTimeMillis();
//        user.setBirth(birth);
//        user.setCreateTime(birth);
//        user.setUpdateTime(birth);
//
//        user.setPhone("phone");
//        boolean insert = user.insertOrUpdate();
//
//        System.out.println(user.toString());
//        System.out.println(JSON.toJSONString(user));
//
//    }
//
//    @org.junit.Test
//    public void update() {
//    }
//
//    @org.junit.Test
//    public void del() {
//    }
//}