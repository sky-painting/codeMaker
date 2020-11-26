package com.coderman.codemaker.utils;

/**
 * description: ResultDto <br>
 * date: 2020/7/6 21:22 <br>
 * author: coderman <br>
 * version: 1.0 <br>
 */
public class ResultDto {
    private int code;
    private String msg;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultDto setErrorCodeMsg(String msg){
        this.code = -1;
        this.msg = msg;
        return this;
    }

}
