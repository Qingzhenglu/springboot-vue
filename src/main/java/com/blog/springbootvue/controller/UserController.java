package com.blog.springbootvue.controller;

import com.blog.springbootvue.common.lang.Result;
import com.blog.springbootvue.entity.User;
import com.blog.springbootvue.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author motong
 * @since 2024-01-27 03:52:52
 */
@RestController
@RequestMapping("/springbootvue/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequiresAuthentication
    @GetMapping("/index")
    public Result index() {
        User user = userService.getById(1L);
        return Result.succ(user);
    }
    @PostMapping("/save")
public Object testUser(@Validated @RequestBody User user) {
    return user.toString();
}
}
