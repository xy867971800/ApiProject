package com.mvtlabs.api.Service.user;

import com.mvtlabs.api.core.http.Response;
import com.mvtlabs.api.core.http.user.*;
import com.mvtlabs.api.core.model.ProjectModel;
import com.mvtlabs.api.core.model.UserHttpModel;

public interface UserService {
    //注册用户
    Response<UserHttpModel> registerUser(UserRegisterRequest userRegisterRequest);

    Response<UserHttpModel> userLogin(UserLoginRequest request);

    Response modifyPassword(UserModifyPasswordRequest request,String userToken);

    //申请个人认证(国内版)
    Response<String> applyIndividualDomestic(IndividualRealNameAuthenticationRequest request,String userToken);

    //申请企业认证
    Response<String> applyEnterprise(EnterpriseAuthenticationRequest request,String userToken);

    //申请一个项目
    Response<ProjectModel> applyProject(ApplyProjectRequest applyProjectRequest,String userToken);
}
