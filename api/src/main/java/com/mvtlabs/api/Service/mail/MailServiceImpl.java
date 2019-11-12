package com.mvtlabs.api.Service.mail;

import com.mvtlabs.api.core.http.mail.MailVerificationRequest;
import com.mvtlabs.api.core.http.Response;
import com.mvtlabs.api.dao.UserDao;
import com.mvtlabs.api.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class MailServiceImpl implements MailService {
    private Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);
    @Resource
    UserDao userDao;
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    RedisTemplate redisTemplate;

    public Response<String> sendVerificationCode(MailVerificationRequest request){
        //判断是否存在
        UserEntity userEntity = userDao.findByMail(request.mailAddress);
        if(userEntity != null){
            return Response.Builder.buildFail("该邮箱已被用户注册");
        }
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("xuyan1998@vip.qq.com");
        simpleMailMessage.setSubject("这是一个验证码的标题");
        simpleMailMessage.setTo(request.mailAddress);
        Random random = new Random();
        String code = String.valueOf(random.nextInt(900000)+100000);
        simpleMailMessage.setText("验证码为:   " + code +"   ,有效期为5分钟，请尽快注册。");
        javaMailSender.send(simpleMailMessage);
        RedisSerializer redisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setValueSerializer(redisSerializer);
        redisTemplate.opsForValue().set(request.mailAddress,code);
        redisTemplate.expire(request.mailAddress,5, TimeUnit.MINUTES);
        if(redisTemplate.hasKey(request.mailAddress)){
            logger.info("验证码:" + code + "已经写入redis中");
        }else{
            logger.info("写入redis失败");
        }
        return Response.Builder.buildOk("成功发送验证码");
    }
}
