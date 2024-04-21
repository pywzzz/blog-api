package com.pywzzz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pywzzz.constants.SystemConstants;
import com.pywzzz.domain.ResponseResult;
import com.pywzzz.domain.entity.Article;
import com.pywzzz.domain.entity.Category;
import com.pywzzz.domain.vo.ArticleDetailVo;
import com.pywzzz.domain.vo.ArticleListVo;
import com.pywzzz.domain.vo.HotArticleVo;
import com.pywzzz.domain.vo.PageVo;
import com.pywzzz.mapper.ArticleMapper;
import com.pywzzz.service.ArticleService;
import com.pywzzz.service.CategoryService;
import com.pywzzz.utils.BeanCopyUtils;
import com.pywzzz.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult hotArticleList() {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        // 必须是正式文章
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        // 按照浏览量进行将序排列
        queryWrapper.orderByDesc(Article::getViewCount);
        // 查询第1页，每页最多10条
        Page<Article> page = new Page(1, 10);
        page(page, queryWrapper);
        // 返回结果
        List<Article> articles = page.getRecords();

        List<HotArticleVo> hotArticleVos = BeanCopyUtils.copyBeanList(articles, HotArticleVo.class);
        return ResponseResult.okResult(hotArticleVos);
    }

    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 如果有categoryId，则查询时要和传入的相同
        lambdaQueryWrapper.eq(Objects.nonNull(categoryId) && categoryId > 0, Article::getCategoryId, categoryId);
        // 文章应该是正式发布的
        lambdaQueryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        // 按isTop大小进行降序（由于1为顶置0不为，所以降序排列能达到顶置的文章在前的效果）
        lambdaQueryWrapper.orderByDesc(Article::getIsTop);
        // 分页查询
        Page<Article> page = new Page<>(pageNum, pageSize);
        page(page, lambdaQueryWrapper);
        // 根据articleId去查询categoryName并将查询到的name进行赋值
        page.getRecords().stream().map(article -> article.setCategoryName(categoryService.getById(article.getCategoryId()).getName())).collect(Collectors.toList());
        // 封装vo
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleListVo.class);
        PageVo pageVo = new PageVo(articleListVos, page.getTotal());

        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult getArticleDetail(Long id) {
        // 根据id查询文章
        Article article = getById(id);
        // vo封装
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        // 根据分类id查询分类名
        Long categoryId = articleDetailVo.getCategoryId();
        Category category = categoryService.getById(categoryId);
        if (category != null) {
            articleDetailVo.setCategoryName(category.getName());
        }

        return ResponseResult.okResult(articleDetailVo);
    }

    @Override
    public ResponseResult updateViewCount(Long id) {
        // 更新redis中对应 id的浏览量
        redisCache.incrementCacheMapValue("article:viewCount", id.toString(), 1);
        return ResponseResult.okResult();
    }
}
