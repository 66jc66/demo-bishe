package com.xyxy.pojo;

/**
 * Description: 封装后台返回的结果
 * Author: xry
 * CreateTime: 2021/8/4 22:30
 * Version: 1.0
 */
public class ResultInfo {
    /*响应编码*/
    private Integer code = 200;
    /*响应消息*/
    private String msg = "success";
    /*数据总条数*/
    private Integer count;
    /*响应数据*/
    private Object result;

    public ResultInfo() {
    }

    public ResultInfo(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultInfo(Integer code, String msg, Object result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }

    public ResultInfo(Integer code, String msg, Integer count, Object result) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.result = result;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ResultInfo{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", count=" + count +
                ", result=" + result +
                '}';
    }
}
