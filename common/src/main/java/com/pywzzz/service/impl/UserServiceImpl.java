package com.pywzzz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pywzzz.domain.ResponseResult;
import com.pywzzz.domain.entity.User;
import com.pywzzz.domain.vo.UserInfoVo;
import com.pywzzz.mapper.UserMapper;
import com.pywzzz.service.UserService;
import com.pywzzz.utils.BeanCopyUtils;
import com.pywzzz.utils.SecurityUtils;
import org.springframework.stereotype.Service;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2024-04-19 15:44:51
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public ResponseResult userInfo() {
        // 获取当前用户id
        Long userId = SecurityUtils.getUserId();
        // 根据用户id查询用户信息
        User user = getById(userId);
        // 封装成UserInfoVo
        UserInfoVo vo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        
        return ResponseResult.okResult(vo);
    }
}
