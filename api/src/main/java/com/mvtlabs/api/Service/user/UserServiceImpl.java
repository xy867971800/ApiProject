package com.mvtlabs.api.Service.user;

import com.mvtlabs.api.core.http.Response;
import com.mvtlabs.api.core.http.user.*;
import com.mvtlabs.api.core.model.ProjectModel;
import com.mvtlabs.api.core.model.UserHttpModel;
import com.mvtlabs.api.core.util.AppIdUtil;
import com.mvtlabs.api.core.util.UUIDUtil;
import com.mvtlabs.api.core.util.UserUtil;
import com.mvtlabs.api.dao.ApplicationFormDao;
import com.mvtlabs.api.dao.ProjectDao;
import com.mvtlabs.api.dao.UserDao;
import com.mvtlabs.api.entity.ApplicationFormEntity;
import com.mvtlabs.api.entity.ProjectEntity;
import com.mvtlabs.api.entity.UserEntity;
import org.apache.http.util.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Resource
    private UserDao userDao;

    @Resource
    private ApplicationFormDao applicationFormDao;

    @Resource
    private ProjectDao projectDao;

    @Autowired
    private RedisTemplate redisTemplate;

    //用户的注册
    public Response<UserHttpModel> registerUser(UserRegisterRequest userRegisterRequest){
        UserEntity userEntity = userDao.findByUseId(userRegisterRequest.useId);
        if(userEntity != null){
            return Response.Builder.buildFail("用户已存在");
        }
        UserEntity userEntity1 = userDao.findByMail(userRegisterRequest.mail);
        if(userEntity1 != null){
            return Response.Builder.buildFail("该邮箱已被注册");
        }
        //进行redis序列化
        RedisSerializer redisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setValueSerializer(redisSerializer);
        if(redisTemplate.opsForValue().get(userRegisterRequest.mail) == null){
            logger.info("<<<redis内没有邮箱>>>");
            return Response.Builder.buildFail("没有发送验证码或者系统错误，请重新发送");
        }
        if(redisTemplate.opsForValue().get(userRegisterRequest.mail) != null){
            logger.info("redis内验证码为:" + redisTemplate.opsForValue().get(userRegisterRequest.mail) + "  过期时间:" + redisTemplate.getExpire(userRegisterRequest.mail, TimeUnit.SECONDS)+"秒");
        }
        if(!userRegisterRequest.verificationCode.equals(redisTemplate.opsForValue().get(userRegisterRequest.mail))||redisTemplate.opsForValue().get(userRegisterRequest.mail)==null){
            return Response.Builder.buildFail("验证码错误或已经失效");
        }

        userEntity = new UserEntity();
        //logger.info("传入的userId"+userRegisterRequest.useId);
        userEntity.setUseId(userRegisterRequest.useId);
        //logger.info("userEntity中的"+userEntity.getUseId());
        userEntity.setMail(userRegisterRequest.mail);
        if(TextUtils.isEmpty(userRegisterRequest.name)){
            userEntity.setName(userRegisterRequest.useId);
        }else {
            userEntity.setName(userRegisterRequest.name);
        }

        userEntity.setUid(UUIDUtil.generateUUID());

        userEntity.setPassword(userRegisterRequest.password);

        userDao.save(userEntity);

        UserHttpModel userHttpModel = UserUtil.toUserHttpModel(userEntity);

        return Response.Builder.buildOk(userHttpModel);
    }

    //用户的登录
    public Response<UserHttpModel> userLogin(UserLoginRequest request){
        UserEntity userEntity = userDao.findByUseId(request.useIdOrMail);

        if(userEntity == null){
            userEntity = userDao.findByMail(request.useIdOrMail);
            if(userEntity == null){
                return Response.Builder.buildFail("用户名或邮箱不存在");
            }
        }

        if(!request.password.equals(userEntity.getPassword())){
            return Response.Builder.buildFail("密码错误！！！");
        }

        UserHttpModel userHttpModel = UserUtil.toUserHttpModel(userEntity);
        return Response.Builder.buildOk(userHttpModel);
    }

    //用户密码的修改
    public Response modifyPassword(UserModifyPasswordRequest request,String userToken){
        UserEntity userEntity = userDao.findByUid(userToken);
        if(userEntity == null){
            return Response.Builder.buildFail("用户不存在");
        }
        String password = request.passwordNew;
        if(password == null || password.length() < 6){
            return Response.Builder.buildFail("密码不得小于6位");
        }

        if(!request.passwordOriginal.equals(userEntity.getPassword())){
            return Response.Builder.buildFail("密码不匹配");
        }
        userEntity.setPassword(password);
        userDao.save(userEntity);
        return Response.Builder.buildOk();
    }


    public Response<String> applyIndividualDomestic(IndividualRealNameAuthenticationRequest request, String userToken){
        UserEntity userEntity = userDao.findByUid(userToken);
        if(userEntity == null){
            return Response.Builder.buildFail("用户不存在");
        }
        ApplicationFormEntity applicationFormEntity = applicationFormDao.findByUseId(userEntity.getUseId());
        if(applicationFormEntity!=null){
            if(!applicationFormEntity.isStatus()){
                return Response.Builder.buildFail("已有内容提交等待审核，请勿重复提交");
            }
        }
        applicationFormEntity = new ApplicationFormEntity();
        applicationFormEntity.setUseId(userEntity.getUseId());
        applicationFormEntity.setApplicationType("个人认证");
        applicationFormEntity.setInfo1("真实姓名:" + request.realName);
        applicationFormEntity.setInfo2("身份证:" + request.identityCard);
        applicationFormEntity.setInfo3("地区:" + request.region);
        applicationFormEntity.setInfo4("行业:" + request.industry);
        applicationFormEntity.setInfo5("职业:" + request.occupation);
        applicationFormDao.save(applicationFormEntity);
        return Response.Builder.buildOk("您的申请已发送成功，正在受理中~~~");
    }

    public Response<String> applyEnterprise(EnterpriseAuthenticationRequest request, String userToken){
        UserEntity userEntity = userDao.findByUid(userToken);
        if(userEntity == null){
            return Response.Builder.buildFail("用户不存在");
        }
        ApplicationFormEntity applicationFormEntity = applicationFormDao.findByUseId(userEntity.getUseId());
        if(applicationFormEntity!=null){
            if(!applicationFormEntity.isStatus()){
                return Response.Builder.buildFail("已有内容提交等待审核，请勿重复提交");
            }
        }
        applicationFormEntity = new ApplicationFormEntity();
        applicationFormEntity.setUseId(userEntity.getUseId());
        applicationFormEntity.setApplicationType("企业认证");
        applicationFormEntity.setInfo1("企业名称:" + request.enterpriseName);
        applicationFormEntity.setInfo2("所属行业:" + request.industryE);
        applicationFormEntity.setInfo3("地区:" + request.regionE);
        applicationFormEntity.setInfo4("企业注册时间:" + request.registerTime);
        applicationFormEntity.setInfo5("企业规模:" + request.size);
        applicationFormEntity.setInfo6("社会信用代码:" + request.socialCreditCode);
        applicationFormEntity.setInfo7("企业代表人姓名:" + request.representativeName);
        applicationFormEntity.setInfo8("企业代表人职位:" + request.representativePosition);
        applicationFormDao.save(applicationFormEntity);
        return Response.Builder.buildOk("您的申请已发送成功，正在受理中~~~");
    }

    public Response<ProjectModel> applyProject(ApplyProjectRequest request,String userToken){
        UserEntity userEntity = userDao.findByUid(userToken);
        if(userEntity == null){
            return Response.Builder.buildFail("用户不存在");
        }
        int size = projectDao.findByUseId(userEntity.getUseId()).size();
        logger.info(String.valueOf(size));
        if(userEntity.getAuthentication() == null){
            if(size>0){
                return Response.Builder.buildFail("未认证用户最多只能创建1个项目");
            }
        }
        if(userEntity.getAuthentication().equals("个人认证")){
            if(size>2){
                return Response.Builder.buildFail("个人认证用户最多只能创建3个项目");
            }
        }
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setUseId(userEntity.getUseId());
        projectEntity.setApiUid(UUIDUtil.generateUUID());
        projectEntity.setAppId(AppIdUtil.getRandomAppId());
        projectEntity.setProjectName(request.projectName);
        projectEntity.setApiSecret(request.apiSecret);
        projectEntity.setUsageQuantity(500);
        projectDao.save(projectEntity);
        ProjectModel projectModel = UserUtil.toProjectModel(projectEntity);
        projectModel.userProjectNumber = size+1;
        return Response.Builder.buildOk(projectModel);
    }
}
