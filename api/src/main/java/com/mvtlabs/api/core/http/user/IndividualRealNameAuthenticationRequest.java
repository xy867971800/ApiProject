package com.mvtlabs.api.core.http.user;

import io.swagger.annotations.ApiModelProperty;

public class IndividualRealNameAuthenticationRequest {

    @ApiModelProperty(value = "真实姓名")
    public String realName;

    @ApiModelProperty(value = "身份证")
    public String identityCard;

    @ApiModelProperty(value = "所属行业")
    public String industry;

    @ApiModelProperty(value = "所在地区")
    public String region;

    @ApiModelProperty(value = "职业")
    public String occupation;

    //日后可增加手机号码
    //public String phone;
    public String toString(){
        return "IndividualRealNameAuthenticationRequest{" +
                "realName" + realName + '\'' +
                ", identityCard='" + identityCard + '\'' +
                "industry=" + industry + '\'' +
                ", region='" + region + '\'' +
                "occupation" + occupation + '\'' +
                '}';
    }
}
