package com.demo.bolian.security.demo.web.controller;

import com.demo.bolian.security.demo.exception.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice //该注解是处理其他controller抛出的异常的
public class ControllerExceptionHandler {

    @ExceptionHandler(UserNotExistException.class)//任何一个controller抛出该注解中的异常时，都会转到这个方法里进行处理
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String ,Object> handleUserNotExistException(UserNotExistException ex){
        Map<String,Object> result = new HashMap<>();
        result.put("id",ex.getId());
        result.put("message",ex.getMessage());
        return result;
    }

}
