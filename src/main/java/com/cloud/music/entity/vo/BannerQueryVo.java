package com.cloud.music.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 首页banner表
 * </p>
 *
 * @author zy
 * @since 2020-08-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Banner对象", description="首页banner表")
public class BannerQueryVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "查询开始时间", example = "2019-01-01 10:10:10")
    private Date begin;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换


    @ApiModelProperty(value = "查询结束时间", example = "2019-12-01 10:10:10")
    private Date end;


}
