package com.mvtlabs.api.core.http.user;

import io.swagger.annotations.ApiModelProperty;

public class UserModifyPasswordRequest {
    @ApiModelProperty(value = "原密码")
    public String passwordOriginal;
    @ApiModelProperty(value = "新密码")
    public String passwordNew;

    public String toString(){
        return "UserModifyPasswordRequest{" +
                "passwordOriginal=" + passwordOriginal + '\'' +
                ", passwordNew='" + passwordNew + '\'' +
                '}';
    }
}
