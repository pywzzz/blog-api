package com.pywzzz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pywzzz.domain.entity.Comment;
import com.pywzzz.mapper.CommentMapper;
import com.pywzzz.service.CommentService;
import org.springframework.stereotype.Service;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author makejava
 * @since 2024-04-19 11:33:58
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
