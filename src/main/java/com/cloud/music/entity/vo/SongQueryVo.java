package com.cloud.music.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author zy
 * @version 1.0.0
 * @ClassName SongQueryVo.java
 * @Description
 * @CreateDate 2020-12-19  17:04:49
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SongQueryVo对象", description="歌曲查询对象")
public class SongQueryVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "歌手id")
    private String singerId;

    @ApiModelProperty(value = "歌名")
    private String name;

    @ApiModelProperty(value = "简介")
    private String introduction;

    @ApiModelProperty(value = "歌词")
    private String lyric;

}
