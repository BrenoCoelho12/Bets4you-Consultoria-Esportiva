package com.desenvolvimento.pibetting.service.exception;

public class ImpossivelApagarEntidadeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ImpossivelApagarEntidadeException(String msg) {
        super(msg);
    }
}
