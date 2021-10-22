package cn.blue.phoenix.entity;

import java.io.Serializable;

public class Result implements Serializable {

    private Integer code;
    private String message;

    public Result() {
        this.code = 200;
        this.message = "Success!";
    }

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
