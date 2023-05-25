package com.wzz.utils;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "Result", description = "请求结果集")
@Data
public class Result implements Serializable {
    private int code;
    private Object data;
    private String token;
    private String msg;
    //当前页
    private long pageNum;

    //总页数
    private long total;

    //每页条数
    private long pageSize;


    public static Result success(int code, String msg, Object data, int page, int size, String token, int total) {
        Result r = new Result();
        r.setToken(token);
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        r.setPageNum(page);
        r.setTotal(total);
        r.setPageSize(size);
        return r;
    }

    public static Result success(Object data, int page, int size, String token, int total) {
        return success(200, "", data, page, size, token, total);
    }





    //重新整合数据
    public static Result user_success1(int code, String msg, Object data, String token) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        r.setToken(token);
        return r;
    }

    //重新封装数据请求
    public static Result user_success(int code, String msg, Object data, String token) {
        return user_success1(200, msg, data, token);
    }

//    ---------------------------------------

    public static Result fail(int code, String msg, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

//    public static Result fail(String msg) {
//        return fail(400, msg, null);
//    }
//
//    public static Result fail(String msg, Object data) {
//        return fail(400, msg, data);
//    }

    //-----------------------------------------------------------------------
//    public static Result failname(String msg) {
//        return fail(422, msg, null);
//    }

    public static Result failname(int code, String msg, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }


}
