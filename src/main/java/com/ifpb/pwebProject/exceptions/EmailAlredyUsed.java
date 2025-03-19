package com.ifpb.pwebProject.exceptions;

public class EmailAlredyUsed extends RuntimeException {
    public EmailAlredyUsed(String message) {
        super(message);
    }
}
