package com.bank.resona.perdania.backofficebe.exception;

import lombok.Data;

@Data
public class UnAuthorizeException extends RuntimeException{

    private Integer code;

    public UnAuthorizeException(String message, Integer code){
        super(message);
        this.code = code;
    }
}
