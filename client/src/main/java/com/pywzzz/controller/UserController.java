package com.pywzzz.controller;

import com.pywzzz.annotation.SystemLog;
import com.pywzzz.domain.ResponseResult;
import com.pywzzz.domain.entity.User;
import com.pywzzz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userInfo")
    public ResponseResult userInfo() {
        return userService.userInfo();
    }

    @PutMapping("/userInfo")
    @SystemLog(businessName = "更新用户信息")
    public ResponseResult updateUserInfo(@RequestBody User user) {
        return userService.updateUserInfo(user);
    }

    @PostMapping("/register")
    public ResponseResult register(@RequestBody User user) {
        return userService.register(user);
    }
}
