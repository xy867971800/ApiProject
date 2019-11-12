package com.mvtlabs.api.core.http.user;

import io.swagger.annotations.ApiModelProperty;

public class ApplyProjectRequest {

    @ApiModelProperty(value = "项目名称")
    public String projectName;

    @ApiModelProperty(value = "项目密码")
    public String apiSecret;
}
