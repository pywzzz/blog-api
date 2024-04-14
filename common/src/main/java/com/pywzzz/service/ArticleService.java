package com.pywzzz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pywzzz.domain.ResponseResult;
import com.pywzzz.domain.entity.Article;

public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();
}
