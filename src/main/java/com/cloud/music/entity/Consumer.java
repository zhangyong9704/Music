package com.cloud.music.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 前端用户
 * </p>
 *
 * @author zy
 * @since 2021-01-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Consumer对象", description="前端用户")
public class Consumer implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "账号")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "性别（1男0女）")
    private Integer sex;

    @ApiModelProperty(value = "电话")
    private String phoneNum;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "生日")
    private Date birth;

    @ApiModelProperty(value = "盐值")
    private int salt;

    @ApiModelProperty(value = "签名")
    private String introduction;

    @ApiModelProperty(value = "地区")
    private String location;

    @ApiModelProperty(value = "头像")
    private String avator;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableLogic  //标注逻辑删除字段
    private Integer isDelete;


}
