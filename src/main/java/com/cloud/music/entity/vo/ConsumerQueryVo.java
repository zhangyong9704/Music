package com.cloud.music.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author zy
 * @since 2020-12-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ConsumerQueryVo对象", description="用户查询对象")
public class ConsumerQueryVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "账号")
    private String username;

    @ApiModelProperty(value = "性别（1男0女）")
    private Boolean sex;

    @ApiModelProperty(value = "电话")
    private String phoneNum;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "生日")
    private LocalDateTime birth;

    @ApiModelProperty(value = "地区")
    private String location;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

}
