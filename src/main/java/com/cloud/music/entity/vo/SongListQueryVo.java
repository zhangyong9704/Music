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
 * @Description  歌单列表查询
 * @CreateDate 2020-12-22  21:06:18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SongListQueryVo对象", description="歌单查询对象")
public class SongListQueryVo implements Serializable {

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "简介")
    private String introduction;

    @ApiModelProperty(value = "风格")
    private String style;

}
