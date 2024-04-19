package com.pywzzz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pywzzz.domain.ResponseResult;
import com.pywzzz.domain.entity.Comment;


/**
 * 评论表(Comment)表服务接口
 *
 * @author makejava
 * @since 2024-04-19 11:33:58
 */
public interface CommentService extends IService<Comment> {

    ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize);

    ResponseResult addComment(Comment comment);
}
