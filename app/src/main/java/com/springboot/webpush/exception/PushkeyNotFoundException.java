package com.springboot.webpush.exception;

public class PushkeyNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PushkeyNotFoundException() {
        super("PushKey not updated");
    }

}
