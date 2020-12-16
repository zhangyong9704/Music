package com.cloud.music.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * 歌单
 * </p>
 *
 * @author zy
 * @since 2020-12-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SongList对象", description="歌单")
public class SongList implements Serializable {

    private static final long serialVersionUID = 1L;

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
    private LocalDate createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDate updateTime;

    @ApiModelProperty(value = "0未删除1已删除")
    @TableLogic  //标注逻辑删除字段
    private String isDelete;


}
