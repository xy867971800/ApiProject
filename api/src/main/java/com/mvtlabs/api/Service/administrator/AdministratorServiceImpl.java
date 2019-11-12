package com.mvtlabs.api.Service.administrator;

import com.mvtlabs.api.core.http.Response;
import com.mvtlabs.api.core.http.adminitrator.AdministratorRegisterRequest;
import com.mvtlabs.api.core.http.adminitrator.AuditingRequest;
import com.mvtlabs.api.core.model.AdministratorHttpModel;
import com.mvtlabs.api.core.util.AdministratorUtil;
import com.mvtlabs.api.core.util.UUIDUtil;
import com.mvtlabs.api.dao.AdministratorDao;
import com.mvtlabs.api.dao.ApplicationFormDao;
import com.mvtlabs.api.dao.UserDao;
import com.mvtlabs.api.entity.AdministratorEntity;
import com.mvtlabs.api.entity.ApplicationFormEntity;
import com.mvtlabs.api.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
public class AdministratorServiceImpl implements AdministratorService{

    private Logger logger = LoggerFactory.getLogger(AdministratorServiceImpl.class);

    @Resource
    AdministratorDao administratorDao;
    @Resource
    ApplicationFormDao applicationFormDao;
    @Resource
    UserDao userDao;

    //管理员注册服务
    public Response<AdministratorHttpModel> administratorRegister(AdministratorRegisterRequest request){
        AdministratorEntity administratorEntity = administratorDao.findByUseId(request.useId);
        if(administratorEntity != null){
            return Response.Builder.buildFail("该管理员已存在");
        }

        administratorEntity = new AdministratorEntity();
        administratorEntity.setUseId(request.useId);
        administratorEntity.setPassword(request.password);
        administratorEntity.setUid(UUIDUtil.generateUUID());
        administratorDao.save(administratorEntity);

        AdministratorHttpModel administratorHttpModel = AdministratorUtil.toAdministratorHttpModel(administratorEntity);
        return Response.Builder.buildOk(administratorHttpModel);
    }

    public Response<AdministratorHttpModel> administratorLogin(AdministratorRegisterRequest request){
        AdministratorEntity administratorEntity = administratorDao.findByUseId(request.useId);
        if(administratorEntity == null){
            return Response.Builder.buildFail("没有该管理员用户");
        }

        if(!administratorEntity.getPassword().equals(request.password)){
            return Response.Builder.buildFail("密码错误");
        }
        AdministratorHttpModel administratorHttpModel = AdministratorUtil.toAdministratorHttpModel(administratorEntity);

        return Response.Builder.buildOk(administratorHttpModel);
    }

    //获取全部待审核信息
    public Response<List<ApplicationFormEntity>> getAudit(){
        List<ApplicationFormEntity> list = applicationFormDao.findAllNotAudit();
        return Response.Builder.buildOk(list);
    }

    public Response<String> doAudit(AuditingRequest request){
        UserEntity userEntity = userDao.findByUseId(request.useId);
        if(userEntity == null) {
            return Response.Builder.buildFail("用户不存在");
        }
        ApplicationFormEntity applicationFormEntity = applicationFormDao.findByUseId(request.useId);
        if(applicationFormEntity == null){
            return Response.Builder.buildFail("该审核请求不存在");
        }
        //如果同意则设置在user表中加上身份认证
        if(request.passIf){
            userEntity.setAuthentication(applicationFormEntity.getApplicationType());
            userDao.save(userEntity);
        }
        applicationFormEntity.setStatus(true);//true代表已审核
        applicationFormDao.save(applicationFormEntity);
        return Response.Builder.buildOk("完成审核");
    }
}
