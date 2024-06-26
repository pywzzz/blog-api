package com.pywzzz.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 文章表(Article)表实体类
 *
 * @author makejava
 * @since 2024-04-13 17:11:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("article")
// 如果不加这个注解，那么set方法的返回值默认是void，而加了后，返回类型就会变为这个实体类（可 alt+7 自己看）
@Accessors(chain = true)
public class Article {

    @TableId
    private Long id;
    // 标题
    private String title;
    // 文章内容
    private String content;
    // 文章摘要
    private String summary;
    // 所属分类id
    private Long categoryId;
    // 数据库表不存在，但api中要出现的一个字段
    @TableField(exist = false)
    private String categoryName;
    // 缩略图
    private String thumbnail;
    // 是否置顶（0否，1是）
    private String isTop;
    // 状态（0已发布，1草稿）
    private String status;
    // 访问量
    private Long viewCount;
    // 是否允许评论 1是，0否
    private String isComment;

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;
    // 删除标志（0代表未删除，1代表已删除）
    private Integer delFlag;

    public Article(Long id, long viewCount) {
        this.id = id;
        this.viewCount = viewCount;
    }
}

