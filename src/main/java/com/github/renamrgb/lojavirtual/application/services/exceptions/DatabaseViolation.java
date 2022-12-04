package com.github.renamrgb.lojavirtual.application.services.exceptions;

public class DatabaseViolation extends RuntimeException {
    public DatabaseViolation(String message) {
        super(message);
    }
}
