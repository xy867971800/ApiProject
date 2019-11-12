package com.mvtlabs.api.Controller.administrator;

import com.mvtlabs.api.Service.administrator.AdministratorService;
import com.mvtlabs.api.core.http.Response;
import com.mvtlabs.api.core.http.adminitrator.AdministratorRegisterRequest;
import com.mvtlabs.api.core.http.adminitrator.AuditingRequest;
import com.mvtlabs.api.core.http.user.UserRegisterRequest;
import com.mvtlabs.api.core.model.AdministratorHttpModel;
import com.mvtlabs.api.entity.ApplicationFormEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.mvtlabs.api.core.http.RequestHeaderConstant.USER_TOKEN;

@Api(tags = {"管理员API"},description = "管理员")
@RestController
@RequestMapping(path = "/administrator", produces = {"application/json;charset=UTF-8"})
public class AdministratorController {

    @Autowired
    AdministratorService administratorService;

    //管理员注册
    @ApiOperation(value = "管理员的注册")
    @PostMapping(value = "/register",produces = {"application/json;charset=UTF-8"})
    public Response<AdministratorHttpModel> registerAdministrator(@RequestBody AdministratorRegisterRequest request, HttpServletRequest servletRequest){
        return administratorService.administratorRegister(request);
    }

    //管理员登录
    @ApiOperation(value = "管理员的登录")
    @PostMapping(value = "/login",produces = {"application/json;charset=UTF-8"})
    public Response<AdministratorHttpModel> registerLogin(@RequestBody AdministratorRegisterRequest request,HttpServletRequest servletRequest){
        return administratorService.administratorLogin(request);
    }

    //获取待审核信息
    @ApiOperation(value = "获取待审核信息")
    @ApiImplicitParam(name = USER_TOKEN,value = "User Token",dataType = "String" ,paramType = "header")
    @GetMapping(value = "/get/audit",produces = {"application/json;charset=UTF-8"})
    public Response<List<ApplicationFormEntity>> getAuditList(HttpServletRequest servletRequest){
        String userToken = servletRequest.getHeader(USER_TOKEN);
        if(userToken == null){
            return Response.Builder.buildFail("请先登录管理员");
        }
        return administratorService.getAudit();
    }

    @ApiOperation(value = "审核")
    @ApiImplicitParam(name = USER_TOKEN,value = "User Token",dataType = "String",paramType = "header")
    @PostMapping(value = "/auditing",produces = {"application/json;charset=UTF-8"})
    public Response<String> doAudit(@RequestBody AuditingRequest request, HttpServletRequest servletRequest){
        String userToken = servletRequest.getHeader(USER_TOKEN);
        if(userToken == null){
            return Response.Builder.buildFail("请先登录管理员！！！");
        }
        return administratorService.doAudit(request);
    }
}
