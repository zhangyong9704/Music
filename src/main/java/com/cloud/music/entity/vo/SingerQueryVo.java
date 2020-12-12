package com.cloud.music.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
@ApiModel(value="SingerQueryVo对象", description="歌手查询对象")
public class SingerQueryVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "性别（0女1男2组合3不明）")
    private Integer sex;

//    @ApiModelProperty(value = "生日")
//    private LocalDateTime birth;

    @ApiModelProperty(value = "地区")
    private String location;



}
