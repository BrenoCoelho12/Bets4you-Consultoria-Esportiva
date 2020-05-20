package com.desenvolvimento.bets4you.service.exception;

public class ImpossivelApagarEntidadeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ImpossivelApagarEntidadeException(String msg) {
        super(msg);
    }
}
