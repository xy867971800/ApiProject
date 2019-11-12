package com.mvtlabs.api.config.Excutor;

import org.springframework.context.annotation.Bean;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExcutorConfig {

    @Bean
    public ExecutorService getAsyncExecutor() {
        // 新建一个任务执行器
        return Executors.newFixedThreadPool(8);
    }

}
