package com.mvtlabs.api.core.http.adminitrator;

import io.swagger.annotations.ApiModelProperty;

public class AuditingRequest {
    @ApiModelProperty(value = "用户名",required = true)
    public String useId;
    @ApiModelProperty(value = "审核结果",required = true)
    public boolean passIf;//1为通过，0为不通过

    public String toString(){
        return "AuditingRequest{" +
                "useId='" + useId +'\'' +
                ", passIf=" + passIf + '\'' +
                '}';
    }
}
