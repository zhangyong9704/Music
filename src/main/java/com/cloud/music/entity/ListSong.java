package com.cloud.music.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 歌单包含歌曲列表
 * </p>
 *
 * @author zy
 * @since 2020-12-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ListSong对象", description="歌单包含歌曲列表")
public class ListSong implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
      private String id;

    @ApiModelProperty(value = "歌曲id")
    private String songId;

    @ApiModelProperty(value = "歌单id")
    private String songListId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;


}
