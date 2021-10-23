package com.mrsydar.kubl.engine.service.exceptions;

public class HashingException extends RuntimeException {
    public HashingException(String m, Exception e) {
        super(m, e);
    }
}
