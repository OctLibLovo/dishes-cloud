package com.etoak.common.exception;

public class ParamException extends RuntimeException{
    public ParamException(String message){
        super(message);
    }
}
/**
 * if(username存在){throw new ParamException("用户已存在");}
 */
