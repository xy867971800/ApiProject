package com.galaxy.demo.service;


import com.galaxy.demo.httpPackage.IdentifyEntity;
import com.galaxy.demo.httpPackage.ProjectClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PostService {
    public String postSend(MultipartFile mFile1,MultipartFile mFile2){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS");
        Date date = new Date();
        String time = sdf.format(date);
        String path1 = "C:\\Users\\XY\\Pictures\\Tibet\\"+"文件1"+time+".jpg";
        String path2 = "C:\\Users\\XY\\Pictures\\Tibet\\"+"文件2"+time+".jpg";
        File file1 = new File(path1);
        File file2 = new File(path2);
        OutputStream out1= null;
        OutputStream out2= null;
        try{
            out1 = new FileOutputStream(file1);
            out2 = new FileOutputStream(file2);
            byte[] ss1 = mFile1.getBytes();
            byte[] ss2 = mFile2.getBytes();
            for(int i=0;i<ss1.length;i++){
                out1.write(ss1[i]);
            }
            for(int i=0;i<ss2.length;i++){
                out2.write(ss2[i]);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(out1 != null){
                try {
                    out1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(out2 != null){
                try {
                    out2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if(!file1.exists()){
            try {
                file1.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(!file2.exists()){
            try {
                file2.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        IdentifyEntity identifyEntity = new IdentifyEntity();
        identifyEntity.setApiSecret("vae123");
        identifyEntity.setAppId("3b4a34vx");
        ProjectClient projectClient = new ProjectClient(identifyEntity);
        projectClient.doPost(path1,path2);
        return "ok";
    }

//    public String sendPost(String path1,String path2){
//        IdentifyEntity identifyEntity = new IdentifyEntity();
//        identifyEntity.setApiSecret("3b4a34vx");
//        identifyEntity.setAppId("vae123");
//        ProjectClient projectClient = new ProjectClient(identifyEntity);
//        projectClient.doPost(path1,path2);
//        return "ok";
//    }
}
