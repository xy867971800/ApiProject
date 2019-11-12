package com.mvtlabs.api.core.http.user;

import io.swagger.annotations.ApiModelProperty;

public class UserLoginRequest {
    @ApiModelProperty(value = "用户名或者邮箱")
    public String useIdOrMail;
    @ApiModelProperty(value = "密码")
    public String password;

    public String toString(){
        return "UserLoginRequest{" +
                "useIdOrMail" + useIdOrMail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
