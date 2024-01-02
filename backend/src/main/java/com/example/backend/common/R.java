package com.example.backend.common;

import lombok.Data;

@Data
public class R<T> {

    private Integer code;
    private String message;
    private T data;

    public R() {}

    public static <T> R<T> success(T data) {
        R<T> result = new R<>();
        result.setCode(Code.REQUEST_SUCCEED);
        result.setMessage("请求成功");
        result.setData(data);
        return result;
    }

    public static <T> R<T> success(String message, T data) {
        R<T> result = new R<>();
        result.setCode(Code.REQUEST_SUCCEED);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static R success(String message) {
        R result = new R();
        result.setCode(Code.REQUEST_SUCCEED);
        result.setMessage(message);
        return result;
    }

    public static R fail(String message) {
        R result = new R();
        result.setCode(Code.REQUEST_FAILURE);
        result.setMessage(message);
        return result;
    }

    public static R warn(String message) {
        R result = new R();
        result.setCode(Code.REQUEST_SUCCEED);
        result.setMessage(message);
        return result;
    }

    public static R error(Integer code, String msg) {
        R result = new R();
        result.setCode(code);
        result.setMessage(msg);
        return result;
    }
}