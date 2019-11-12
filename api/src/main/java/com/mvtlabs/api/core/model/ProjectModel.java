package com.mvtlabs.api.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectModel {
    @ApiModelProperty(value = "项目用户身份ID")
    public String useId;
    @ApiModelProperty(value = "项目名")
    public String projectName;
    @ApiModelProperty("调用量")
    public int usageQuality;
    @ApiModelProperty("项目的ID")
    public String appId;
    @ApiModelProperty("用户所有的项目数量")
    public int userProjectNumber;
}
