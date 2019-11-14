package com.mvtlabs.api.Service.http;

import com.alibaba.fastjson.JSONObject;
import com.mvtlabs.api.core.http.Response;
import com.mvtlabs.api.dao.ProjectDao;
import com.mvtlabs.api.entity.ProjectEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Base64;

@Service
public class HttpServiceImpl implements HttpService {

    @Resource
    private ProjectDao projectDao;

    public Response<String> parseRequestTest(HttpServletRequest request){
        BufferedReader br = null;
        String str = "";
        String listString = "";
        try {
            br = request.getReader();
            while ((str = br.readLine()) != null) {
                listString += str;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] strArr = listString.split("和");
        System.err.println(strArr[0]);
        System.out.println(strArr[1]);
        base64toFile(strArr[0],strArr[1]);
        return Response.Builder.buildOk("cxknmsl");
    }
    private void base64toFile(String str1,String str2){
        File file1 = null;
        File file2 = null;
        BufferedOutputStream bos1 = null;
        BufferedOutputStream bos2 = null;
        FileOutputStream fos1 = null;
        FileOutputStream fos2 = null;
        try {
            byte[] ss1 = Base64.getDecoder().decode(str1);
            byte[] ss2 = Base64.getDecoder().decode(str2);
            file1 = new File("./"+"文件1.jpg");
            file2 = new File("./"+"文件2.jpg");
            fos1 = new FileOutputStream(file1);
            fos2 = new FileOutputStream(file2);
            bos1 = new BufferedOutputStream(fos1);
            bos2 = new BufferedOutputStream(fos2);
            bos1.write(ss1);
            bos2.write(ss2);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos1 != null) {
                try {
                    bos1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos2 != null) {
                try {
                    bos2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos1 != null) {
                try {
                    fos1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos2 != null) {
                try {
                    fos2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String parseRequest(HttpServletRequest request){
        String requestAppId = request.getHeader("appId");
        String requestAppSecret = request.getHeader("appSecret");
        ProjectEntity projectEntity = projectDao.findByAppId(requestAppId);
        if(projectEntity == null){
            return Response.Builder.buildFail("实体类不存在").toString();
        }
        if(!requestAppSecret.equals(projectEntity.getApiSecret())){
            return Response.Builder.buildFail("密码错误").toString();
        }
        if(projectEntity.getUsageQuantity() < 1){
            return Response.Builder.buildFail("项目的使用量不足").toString();
        }
        BufferedReader br = null;
        String str = "";
        String listString = "";
        try {
            br = request.getReader();
            while ((str = br.readLine()) != null) {
                listString += str;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String result = doPost(listString);
        int quality = projectEntity.getUsageQuantity();
        quality--;
        projectEntity.setUsageQuantity(quality);
        projectDao.save(projectEntity);
        return result;
    }

    private String doPost(String base64Code){
        String result = "";
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            // 设置 url
            URL realUrl = new URL("http://120.77.148.57:19001/tuodi/face/compare/api");
            URLConnection connection = realUrl.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
            // 设置 header
            httpURLConnection.setRequestProperty("Content-Type", "application/json;charset=utf8");
            // 设置请求 body
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            out = new PrintWriter(httpURLConnection.getOutputStream());
            // 保存body
            out.print(base64Code);
            // 发送body
            out.flush();
            if (HttpURLConnection.HTTP_OK != httpURLConnection.getResponseCode()) {
                System.out.println("Http 请求失败，状态码：" + httpURLConnection.getResponseCode());
                return null;
            }
            // 获取响应body
            in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            return null;
        }
        System.err.println(result);
        return result;
    }
}
