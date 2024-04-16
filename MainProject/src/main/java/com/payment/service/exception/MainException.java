package com.payment.service.exception;

public class MainException extends RuntimeException {

    private Integer code;

    public MainException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public MainException(String message) {
        super(message);
    }

    public Integer getCode() {
        return code;
    }

}
