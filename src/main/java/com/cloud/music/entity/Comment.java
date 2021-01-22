package com.cloud.music.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 评论
 * </p>
 *
 * @author zy
 * @since 2021-01-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Comment对象", description="评论")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "评论类型（0歌曲1歌单）")
    private Integer type;

    @ApiModelProperty(value = "歌曲id")
    private String songId;

    @ApiModelProperty(value = "歌单id")
    private String songListId;

    @ApiModelProperty(value = "评论内容")
    private String content;

    @ApiModelProperty(value = "收藏时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "评论点赞数")
    private Integer up;


}
