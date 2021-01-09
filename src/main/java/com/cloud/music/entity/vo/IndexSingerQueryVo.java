package com.cloud.music.entity.vo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 歌手
 * </p>
 *
 * @author zy
 * @since 2020-12-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="IndexSingerQueryVo对象", description="前端主页歌手,评论数查询对象")
public class IndexSingerQueryVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "性别（0女1男2组合3不明）")
    private Integer sex;

    @ApiModelProperty(value = "头像")
    private String pic;

    @ApiModelProperty(value = "生日")
    private Date birth;

    @ApiModelProperty(value = "地区")
    private String location;

    @ApiModelProperty(value = "简介")
    private String introduction;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "评论数量")
    private Integer amount;

}
