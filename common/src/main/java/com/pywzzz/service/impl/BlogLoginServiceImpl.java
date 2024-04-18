package com.pywzzz.service.impl;

import com.pywzzz.domain.ResponseResult;
import com.pywzzz.domain.entity.LoginUser;
import com.pywzzz.domain.entity.User;
import com.pywzzz.domain.vo.BlogUserLoginVo;
import com.pywzzz.domain.vo.UserInfoVo;
import com.pywzzz.service.BlogLoginService;
import com.pywzzz.utils.BeanCopyUtils;
import com.pywzzz.utils.JwtUtil;
import com.pywzzz.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BlogLoginServiceImpl implements BlogLoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        // 判断是否认证通过
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或密码错误");
        }
        // 获取userid 生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        // 把用户信息存入redis
        redisCache.setCacheObject("bloglogin:" + userId, loginUser);
        // 把User转换成UserInfoVo
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        // 把token和userinfo封装
        BlogUserLoginVo vo = new BlogUserLoginVo(jwt, userInfoVo);
        return ResponseResult.okResult(vo);
    }
}