package com.mvtlabs.api.core.http.adminitrator;

import io.swagger.annotations.ApiModelProperty;

public class AdministratorRegisterRequest {
    @ApiModelProperty(value = "用户名",required = true)
    public String useId;
    @ApiModelProperty(value = "密码", required = true)
    public String password;

    public String toString(){
        return "AdministartorRegister{" +
                "useId='" + useId +'\'' +
                ", password=" + password + '\'' +
                '}';
    }
}
