package com.bank.resona.perdania.backofficebe.exception;

import lombok.Data;

@Data
public class InternalServerErrorException extends RuntimeException{

    private Integer code;

    public InternalServerErrorException(String message, Integer code){
        super(message);
        this.code = code;
    }
}
