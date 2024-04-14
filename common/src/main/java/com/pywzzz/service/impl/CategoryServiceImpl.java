package com.pywzzz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pywzzz.domain.entity.Category;
import com.pywzzz.mapper.CategoryMapper;
import com.pywzzz.service.CategoryService;
import org.springframework.stereotype.Service;

/**
 * 分类表(Category)表服务实现类
 *
 * @author makejava
 * @since 2024-04-14 12:40:44
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
