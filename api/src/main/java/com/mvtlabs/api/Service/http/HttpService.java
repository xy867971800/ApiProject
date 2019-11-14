package com.mvtlabs.api.Service.http;

import com.mvtlabs.api.core.http.Response;

import javax.servlet.http.HttpServletRequest;

public interface HttpService {
    Response<String> parseRequestTest(HttpServletRequest request);//测试
    String parseRequest(HttpServletRequest request);

}
