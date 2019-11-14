package com.mvtlabs.api.Controller.user;

import com.mvtlabs.api.Service.http.HttpService;
import com.mvtlabs.api.Service.mail.MailService;
import com.mvtlabs.api.Service.user.UserService;
import com.mvtlabs.api.core.http.mail.MailVerificationRequest;
import com.mvtlabs.api.core.http.Response;
import com.mvtlabs.api.core.http.user.*;
import com.mvtlabs.api.core.model.ProjectModel;
import com.mvtlabs.api.core.model.UserHttpModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.mvtlabs.api.core.http.RequestHeaderConstant.USER_TOKEN;

@Api(tags = {"用户API"},description = "用户")
@RestController
@RequestMapping(path = "/user", produces = {"application/json;charset=UTF-8"})
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    MailService mailService;

    @Autowired
    HttpService httpService;

    @ApiOperation(value = "用户的注册")
    @PostMapping(value = "/register",produces = {"application/json;charset=UTF-8"})
    public Response<UserHttpModel> registerUser(@RequestBody UserRegisterRequest request, HttpServletRequest servletRequest){
        return userService.registerUser(request);
    }//@RequestBody的作用是将传入参数转化为json

    @ApiOperation(value = "发送邮件验证码")
    @PostMapping(value = "/verification",produces = {"application/json;charset=UTF-8"})
    public Response<String> sendVerification(@RequestBody MailVerificationRequest request){
        return mailService.sendVerificationCode(request);
    }

    @ApiOperation("用户登录")
    @PostMapping(value = "/login",produces = {"application/json;charset=UTF-8"})
    public Response<UserHttpModel> login(@RequestBody UserLoginRequest request){
        return userService.userLogin(request);
    }

    //修改用户密码
    @ApiOperation(value = "修改用户密码")
    @ApiImplicitParam(name = USER_TOKEN,value = "User Token",dataType = "String" ,paramType = "header")
    @PostMapping(value = "/modify/password",produces = {"application/json;charset=UTF-8"})
    public Response modifyPassword(@RequestBody UserModifyPasswordRequest request, HttpServletRequest servletRequest){
        String userToken = servletRequest.getHeader(USER_TOKEN);
        if(userToken == null){
            return Response.Builder.buildFail("请先登录");
        }
        return userService.modifyPassword(request,userToken);
    }
    //申请个人实名认证(国内版-身份证)
    @ApiOperation(value = "申请个人实名认证(国内版)")
    @ApiImplicitParam(name = USER_TOKEN,value = "User Token",dataType = "String", paramType = "header")
    @PostMapping(value = "/authentication/domestic",produces = {"application/json;charset=UTF-8"})
    public Response<String> applyIndividualRealNameAuthentication(@RequestBody IndividualRealNameAuthenticationRequest request, HttpServletRequest servletRequest){
        String userToken = servletRequest.getHeader(USER_TOKEN);
        if(userToken == null){
            return Response.Builder.buildFail("请先登录");
        }
        return userService.applyIndividualDomestic(request,userToken);
    }

    //申请企业认证
    @ApiOperation(value = "申请企业实名认证")
    @ApiImplicitParam(name = USER_TOKEN,value = "User Token", dataType = "String",paramType = "header")
    @PostMapping(value = "/authentication/enterprise",produces = {"application/json;charset=UTF-8"})
    public Response<String> applyEnterpriseAuthentication(@RequestBody EnterpriseAuthenticationRequest request, HttpServletRequest servletRequest){
        String userToken = servletRequest.getHeader(USER_TOKEN);
        if(userToken == null){
            return Response.Builder.buildFail("请先登录");
        }
        return userService.applyEnterprise(request,userToken);
    }

//    @ApiOperation(value = "测试用")
//    @ApiImplicitParam(name = "X-Appid",value = "X-Appid", dataType = "String",paramType = "header")
//    @GetMapping(value = "/test",produces = {"application/json;charset=UTF-8"})
//    public String test1(HttpServletRequest request){
//        System.out.println(request.getHeader("ok"));
//        return "ok";
//    }

    @ApiOperation(value = "申请一个项目")
    @ApiImplicitParam(name = USER_TOKEN,value = "User Token", dataType = "String",paramType = "header")
    @PostMapping(value = "/apply/project",produces = {"application/json;charset=UTF-8"})
    public Response<ProjectModel> applyForProject(@RequestBody ApplyProjectRequest request,HttpServletRequest httpServletRequest){
        String userToken = httpServletRequest.getHeader(USER_TOKEN);
        if(userToken == null){
            return Response.Builder.buildFail("请先登录");
        }
        return userService.applyProject(request,userToken);
    }


    @ApiOperation(value = "测试Http请求的发送")
    @PostMapping(value = "/test/test",produces = {"application/json;charset=UTF-8"})
    public String test(HttpServletRequest request){
    //    return httpService.parseRequest(request);
    //    return httpService.getPost(request);
    //    return httpService.getPost2(request);
        return httpService.parseRequest(request);
    }


}
