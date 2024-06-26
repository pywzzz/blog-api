package com.pywzzz.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 友链(Link)表实体类
 *
 * @author makejava
 * @since 2024-04-15 15:38:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("link")
public class Link {

    private Long id;

    private String name;

    private String logo;

    private String description;
    // 网站地址
    private String address;
    // 审核状态 (0代表审核通过，1代表审核未通过，2代表未审核)
    private String status;

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;
    // 删除标志（0代表未删除，1代表已删除）
    private Integer delFlag;
}

