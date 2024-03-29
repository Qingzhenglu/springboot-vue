package com.blog.springbootvue.service.impl;

import com.blog.springbootvue.entity.User;
import com.blog.springbootvue.mapper.UserMapper;
import com.blog.springbootvue.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author motong
 * @since 2024-01-27 03:52:52
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
