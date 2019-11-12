package com.mvtlabs.api.core.http.mail;

import io.swagger.annotations.ApiModelProperty;

public class MailVerificationRequest {
    @ApiModelProperty(value = "目标邮箱地址",required = true)
    public String mailAddress;
    @Override
    public String toString(){
        return "MailVerificationRequest{" +
                "mailAddress='" + mailAddress + '\'' +
                "}";
    }
}
