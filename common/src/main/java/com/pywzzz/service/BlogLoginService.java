package com.pywzzz.service;

import com.pywzzz.domain.ResponseResult;
import com.pywzzz.domain.entity.User;

public interface BlogLoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
