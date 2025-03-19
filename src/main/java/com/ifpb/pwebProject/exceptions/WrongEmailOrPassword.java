package com.ifpb.pwebProject.exceptions;

public class WrongEmailOrPassword extends RuntimeException {
    public WrongEmailOrPassword(String message) {
        super(message);
    }
}
