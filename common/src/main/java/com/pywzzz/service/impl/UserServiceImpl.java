package com.pywzzz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pywzzz.domain.entity.User;
import com.pywzzz.mapper.UserMapper;
import com.pywzzz.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2024-04-19 15:44:51
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
