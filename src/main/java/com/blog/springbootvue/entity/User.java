package com.blog.springbootvue.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author motong
 * @since 2024-01-27 03:52:52
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_user")
@ApiModel(value = "User对象", description = "用户表")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("姓名")
    private String name;

    @NotBlank(message = "昵称不能为空")
    @ApiModelProperty("用户名")
    private String username;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("性别")
    private Byte gender;

    @ApiModelProperty("是否启用（0-未启用；1-启用中）")
    private Byte enabled;

    @ApiModelProperty("上一次登录时间")
    private LocalDateTime lastLoginTime;
}
