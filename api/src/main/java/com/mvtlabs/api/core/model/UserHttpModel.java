package com.mvtlabs.api.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserHttpModel {
    @ApiModelProperty(value = "ID")
    public long id;
    @ApiModelProperty(value = "用户身份ID")
    public String useId;
    @ApiModelProperty("姓名")
    public String name;
    @ApiModelProperty(value = "Token")
    public String token;



}
