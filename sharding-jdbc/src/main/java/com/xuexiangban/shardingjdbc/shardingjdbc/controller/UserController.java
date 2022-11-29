package com.xuexiangban.shardingjdbc.shardingjdbc.controller;


import com.xuexiangban.shardingjdbc.shardingjdbc.entity.User;
import com.xuexiangban.shardingjdbc.shardingjdbc.mapper.UserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author: 学相伴-飞哥
 * @description: UserController
 * @Date : 2021/3/10
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserMapper userMapper;

    @GetMapping("/save")
    public String insert() throws ParseException {
        User user = new User();
        user.setNickname("zhangsan" + new Random().nextInt());
        user.setPassword("1234567");
        user.setSex(1);
        user.setBirthday(new SimpleDateFormat("YYYY-MM-dd").parse("1990-10-04"));
        user.setAge(new Random().nextInt());
        userMapper.addUser(user);
        return "success";
    }

    @GetMapping("/listuser")
    public List<User> listuser() {
        return userMapper.findUsers();
    }
}