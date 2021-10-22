package cn.blue.phoenix.controller;

import cn.blue.phoenix.entity.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<Result> error(Exception e) {
        e.printStackTrace();
        System.out.println("Error");
        return new ResponseEntity<>(new Result(503, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
