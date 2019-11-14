package com.galaxy.demo.test;





import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class HttpUtil {
    public static String doPost(String url){
        String result = "";//用来存储返回的报文
        BufferedReader in = null;//用于读取返回报文所创建的流
        try {
            URL realUrl = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)realUrl.openConnection();
            httpURLConnection.setRequestMethod("POST");//设置请求方法
            httpURLConnection.setConnectTimeout(10000);//设置连接超时时间
            httpURLConnection.setReadTimeout(20000);//设置连接后的响应超时时间
            httpURLConnection.setRequestProperty("Connect","Keep-Alive");//客户端服务端保持连接
            httpURLConnection.setRequestProperty("confirm","cxknmsl");
            //建立实际的连接
            httpURLConnection.connect();
            //获取响应报文
            if(200 == httpURLConnection.getResponseCode()){
                in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String line;
                while ((line = in.readLine()) != null){
                    result += line;
                }
            }
            System.out.println(result);
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static String doPost1(String realUrl,String postContent){
        String responseContent = "";
        PrintWriter pw = null;
        BufferedReader br = null;
        try{
            //获取URL
            URL url = new URL(realUrl);
            //使用URL获取URLConnection
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            //设置相应的属性，交互模式
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("confirm","cxknmsl");
            //是否使用URL进行输入输出
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            //使用IO流
            pw = new PrintWriter(httpURLConnection.getOutputStream());
            pw.write(postContent);
            pw.flush();
            //获取响应报文
            br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line = null;
            while((line = br.readLine()) !=  null){
                responseContent += line;
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(br != null)
                    br.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        System.err.println(responseContent);
        return responseContent;
    }

    public static String doPost2(String url, String body){
        String result = "";
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            // 设置 url
            URL realUrl = new URL(url);
            URLConnection connection = realUrl.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
            // 设置 header
            httpURLConnection.setRequestProperty("Content-Type", "application/json;charset=utf8");
            httpURLConnection.setRequestProperty("confirm" , URLEncoder.encode("蔡徐坤鸡你太美","utf-8"));
            // 设置请求 body
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            out = new PrintWriter(httpURLConnection.getOutputStream());
            // 保存body
            out.print(body);
            // 发送body
            out.flush();
            System.err.println("发送的body为:" + body);
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
