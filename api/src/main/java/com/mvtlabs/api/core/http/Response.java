package com.mvtlabs.api.core.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {
    @ApiModelProperty(value = "执行结果, 0: 执行成功, 1: 执行失败, 2: token错误，需要重新登录, -1: 服务器端错误",required = true)
    public int code;
    @ApiModelProperty(value = "执行结果文字",required = true)
    public String message;
    @ApiModelProperty("服务端返回的数据")
    public T data;

    public static final int RESULT_CODE_OK = 0;
    public static final String RESULT_TEXT_OK_DEFAULT = "执行成功";
    public static final int RESULT_CODE_FAIL = 1;
    public static final String RESULT_TEXT_FAIL_DEFAULT = "执行失败";
    //Token错误，需要重新登录
    public static final int RESULT_CODE_FAIL_TOKEN = 2;
    public static final int RESULT_CODE_SERVER_ERROR = -1;
    public static final String RESULT_TEXT_SERVER_ERROR = "服务器端错误";

    public Response<T> setData(T data){
        this.data = data;
        return this;
    }

    public Response<T> buildFail() {
        return build(RESULT_CODE_FAIL,RESULT_TEXT_FAIL_DEFAULT);
    }

    public Response<T> buildFail(String resultText) {
        return build(RESULT_CODE_FAIL,resultText);
    }

    public Response<T> buildOk() {
        return build(RESULT_CODE_OK,RESULT_TEXT_OK_DEFAULT);
    }

    public Response<T> buildOk(String resultText){
        return build(RESULT_CODE_OK, resultText);
    }
    public Response<T> build(int code,String message) {
        this.code = code;
        this.message = message;
        return this;
    }

    public static class Builder {
        public static <T> Response<T> buildFail(String resultText){
            return new Response<T>().buildFail(resultText);
        }

        public static <T> Response<T> buildFail(){
            return new Response<T>().buildFail(RESULT_TEXT_FAIL_DEFAULT);
        }

        public static <T> Response<T> buildOk(){
            return new Response<T>().buildOk();
        }

        public static <T> Response<T> buildOk(T data){
            return new Response<T>().buildOk().setData(data);
        }

        public static <T> Response<T> build(int code,String message){
            return new Response<T>().build(code,message);
        }

        public static <T> Response<T> build(boolean success){
            return success ? buildOk() : buildFail();
        }
    }

    public String toString(){
        return "Response{" +
                "code=" + code +
                ", message=" + message +'\'' +
                ", data" + data +
                "}";
    }







}
