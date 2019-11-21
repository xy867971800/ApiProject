package com.galaxy.demo.httpPackage;

import org.apache.commons.codec.binary.Base64;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class ProjectClient {
    private String appId;
    private String appSecret;
    public ProjectClient(IdentifyEntity identifyEntity){
        this.appId = identifyEntity.getAppId();
        this.appSecret = identifyEntity.getApiSecret();
    }
    public String doCompare(String path1,String path2){
        byte[] imageByte1 = new byte[0];
        byte[] imageByte2 = new byte[0];
        String imageBase1 = null;
        String imageBase2 = null;
        try {
            imageByte1 = read(path1);
            imageBase1 = new String(Base64.encodeBase64(imageByte1),"UTF-8");
            imageByte2 = read(path2);
            imageBase2 = new String(Base64.encodeBase64(imageByte2),"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String result = "";
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            // 设置 url
            URL realUrl = new URL("http://localhost/user/test/compare");
            URLConnection connection = realUrl.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
            // 设置 header
            httpURLConnection.setRequestProperty("Content-Type", "application/json;charset=utf8");
            httpURLConnection.setRequestProperty("appId" , URLEncoder.encode(appId,"utf-8"));
            httpURLConnection.setRequestProperty("appSecret" , URLEncoder.encode(appSecret,"utf-8"));
            // 设置请求 body
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            out = new PrintWriter(httpURLConnection.getOutputStream());
            // 保存body
            out.print(imageBase1+"和"+imageBase2);
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

    public String doTest(String path){
        byte[] imageByte = new byte[0];
        String imageBase = null;
        try {
            imageByte = read(path);
            imageBase = new String(Base64.encodeBase64(imageByte),"UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }
        String result = "";
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            // 设置 url
            URL realUrl = new URL("http://localhost/user/test/test");
            URLConnection connection = realUrl.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
            // 设置 header
            httpURLConnection.setRequestProperty("Content-Type", "application/json;charset=utf8");
            httpURLConnection.setRequestProperty("appId" , URLEncoder.encode(appId,"utf-8"));
            httpURLConnection.setRequestProperty("appSecret" , URLEncoder.encode(appSecret,"utf-8"));
            // 设置请求 body
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            out = new PrintWriter(httpURLConnection.getOutputStream());
            // 保存body
            out.print(imageBase);
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
        return result;
    }


    private  byte[] read(String filePath) throws IOException {

        InputStream in = new FileInputStream(filePath);
        byte[] data = inputStream2ByteArray(in);
        in.close();
        return data;
    }
    private  byte[] inputStream2ByteArray(InputStream in) throws IOException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 4];
        int n = 0;
        while ((n = in.read(buffer)) != -1) {
            out.write(buffer, 0, n);
        }
        return out.toByteArray();
    }
}
