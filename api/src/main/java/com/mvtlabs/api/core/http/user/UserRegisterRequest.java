package com.mvtlabs.api.core.http.user;

import io.swagger.annotations.ApiModelProperty;

public class UserRegisterRequest {
    @ApiModelProperty(value = "用户名",required = true)
    public String useId;

    @ApiModelProperty(value = "姓名",required = true)
    public String name;

    @ApiModelProperty(value = "密码",required = true)
    public String password;

    @ApiModelProperty(value = "邮箱",required = true)
    public String mail;

    @ApiModelProperty(value = "验证码",required = true)
    public String verificationCode;

    @Override
    public String toString() {
        return "UserRegisterRequest{" +
                "useId='" + useId +'\'' +
                ", name=" + name + '\'' +
                ", password=" + password + '\'' +
                ", mail=" + mail +'\'' +
                '}';
    }
}
