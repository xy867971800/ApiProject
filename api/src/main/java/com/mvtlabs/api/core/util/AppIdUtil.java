package com.mvtlabs.api.core.util;

import java.util.Random;

public class AppIdUtil {
    public static String getRandomAppId(){
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for(int i=0; i < 8;i++){
            int number = random.nextInt(2);
            System.out.println(number);
            switch (number){
                case 0:
                    sb.append(String.valueOf((char)Math.round(Math.random()*25+97)));
                    break;
                case 1:
                    sb.append(new Random().nextInt(10));
                    break;
            }
        }
        return sb.toString();
    }
}
