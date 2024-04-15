package com.pywzzz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pywzzz.domain.ResponseResult;
import com.pywzzz.domain.entity.Link;


/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2024-04-15 15:38:37
 */
public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();
}
