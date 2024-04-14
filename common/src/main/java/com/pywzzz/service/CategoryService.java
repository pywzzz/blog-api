package com.pywzzz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pywzzz.domain.ResponseResult;
import com.pywzzz.domain.entity.Category;


/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2024-04-14 12:40:43
 */
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();
}
