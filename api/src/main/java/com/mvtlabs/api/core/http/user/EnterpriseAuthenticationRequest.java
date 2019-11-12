package com.mvtlabs.api.core.http.user;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class EnterpriseAuthenticationRequest {

    @ApiModelProperty(value = "企业名称")
    public String enterpriseName;

    @ApiModelProperty(value = "所属行业")
    public String industryE;

    @ApiModelProperty(value = "所在地区")
    public String regionE;

    @ApiModelProperty(value = "公司注册时间")
    public Date registerTime;

    @ApiModelProperty(value = "企业规模")
    public String size;

    @ApiModelProperty(value = "社会信用代码")
    public String socialCreditCode;

    //以下为企业代表人信息
    @ApiModelProperty(value = "代表人姓名")
    public String representativeName;

    @ApiModelProperty(value = "代表人职位")
    public String representativePosition;

    public String toString(){
        return "EnterpriseAuthenticationRequest{" +
                "realName" + enterpriseName + '\'' +
                ", identityCard='" + industryE + '\'' +
                " industry=" + regionE + '\'' +
                ", region='" + registerTime + '\'' +
                ", size" + size + '\'' +
                ", socialCreditCode" + socialCreditCode + '\'' +
                ", representativeName" + representativeName + '\'' +
                ", representativePosition" + representativePosition + '\'' +
                '}';
    }
}
