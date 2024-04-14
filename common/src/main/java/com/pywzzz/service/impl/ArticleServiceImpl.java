package com.pywzzz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pywzzz.domain.ResponseResult;
import com.pywzzz.domain.entity.Article;
import com.pywzzz.domain.vo.HotArticleVo;
import com.pywzzz.mapper.ArticleMapper;
import com.pywzzz.service.ArticleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Override
    public ResponseResult hotArticleList() {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        // 必须是正式文章
        queryWrapper.eq(Article::getStatus, 0);
        // 按照浏览量进行将序排列
        queryWrapper.orderByDesc(Article::getViewCount);
        // 查询第1页，每页最多10条
        Page<Article> page = new Page(1, 10);
        page(page, queryWrapper);
        // 返回结果
        List<Article> articles = page.getRecords();

        List<HotArticleVo> articleVos = new ArrayList<>();
        for (Article article : articles) {
            HotArticleVo hotArticleVo = new HotArticleVo();
            // bean拷贝（拷贝时字段啥的要一致）
            BeanUtils.copyProperties(article, hotArticleVo);
            articleVos.add(hotArticleVo);
        }

        return ResponseResult.okResult(articleVos);
    }
}
