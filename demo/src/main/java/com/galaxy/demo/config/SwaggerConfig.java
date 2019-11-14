package com.galaxy.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration//表示这是一个配置文件，让spring加载此配置
@EnableSwagger2
@EnableWebMvc
//@ComponentScan(basePackages = {"com.yange.demo1.controller"})
public class SwaggerConfig {
    @Bean
    public Docket customDocket(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("xy","www.baidu.com","xuyan1998@vip.qq.com");
        return new ApiInfoBuilder()
                .title("言哥专属api")
                .description("这是个api接口")
                .contact(contact)
                .version("1.1.0")
                .build();
    }
}