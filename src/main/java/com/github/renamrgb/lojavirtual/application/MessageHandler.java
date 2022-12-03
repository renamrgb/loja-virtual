package com.github.renamrgb.lojavirtual.application;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MessageHandler {
    RESOURCE_NOT_FOUND("Recurso com id %s não encontrado.");

    private final String message;
}
