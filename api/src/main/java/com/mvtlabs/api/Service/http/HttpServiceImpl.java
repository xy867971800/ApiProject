package com.mvtlabs.api.Service.http;

import com.mvtlabs.api.core.http.Response;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class HttpServiceImpl implements HttpService {

    public Response<String> parseRequest(HttpServletRequest request) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder("");
        try {
            br = request.getReader();
            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.err.println("获取的请求体内容为:" + sb.toString());
            return Response.Builder.buildOk(sb.toString());
        }
    }
}
