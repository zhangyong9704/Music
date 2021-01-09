package com.cloud.music.entity.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zy
 * @version 1.0.0
 * @ClassName IndexSongListQueryVo.java
 * @Description  前台歌单查、评分查询对象
 * @CreateDate 2020-12-22  21:06:18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="IndexSongListQueryVo对象", description="前台歌单查、评分查询对象")
public class IndexSongListQueryVo implements Serializable {

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "歌单图片")
    private String pic;

    @ApiModelProperty(value = "简介")
    private String introduction;

    @ApiModelProperty(value = "风格")
    private String style;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "评分")
    private Integer grade;

}
