package com.galaxy.demo.test;

import com.galaxy.demo.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Api(value = "测试用", description = "测试用")
public class TestController {

    @Autowired
    PostService postService;

    @ApiOperation(value = "发送一个Http请求测试")
    @PostMapping("/cxknmsl")
    public String test(){
        //HttpUtil.doPost("http://localhost/user/test/test");
        //HttpUtil.doPost1("http://localhost/user/test/test","chicken u are too pretty");
        HttpUtil.doPost2("http://localhost/user/test/test","菜鸡菜鸡你太美");
        return "ok";
    }

    @ApiOperation(value = "正式的一个http请求")
    @PostMapping("/jntm")
    public String doPost(MultipartFile file1,MultipartFile file2){
        //return postService.postSend(file1,file2);
        return postService.postSend(file1,file2);
    }


}
