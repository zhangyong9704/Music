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
 * @ClassName SongListQueryVo.java
 * @Description  歌单-歌曲列表查询
 * @CreateDate 2020-12-22  21:06:18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ListSongsQueryVo对象", description="歌单-歌曲查询对象")
public class ListSongsQueryVo implements Serializable {

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "歌名")
    private String name;

    @ApiModelProperty(value = "简介")
    private String introduction;

    @ApiModelProperty(value = "歌词")
    private String lyric;

}
