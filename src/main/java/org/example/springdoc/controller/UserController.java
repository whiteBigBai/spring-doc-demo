package org.example.springdoc.controller;

import com.alibaba.fastjson.JSONObject;
import org.example.springdoc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping("/login")
    public JSONObject login(String userName, String password) {
        JSONObject result = new JSONObject();
        if (userService.queryUser(userName, password)) {
            result.put("statusCode", "200");
            result.put("statusMsg", "登录成功!");
        } else {
            result.put("statusCode", "500");
            result.put("statusMsg", "登录失败!");
        }
        return result;
    }

}
