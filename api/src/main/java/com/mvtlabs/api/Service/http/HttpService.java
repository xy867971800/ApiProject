package com.mvtlabs.api.Service.http;

import com.mvtlabs.api.core.http.Response;

import javax.servlet.http.HttpServletRequest;

public interface HttpService {
    Response<String> parseRequest(HttpServletRequest request);
}
