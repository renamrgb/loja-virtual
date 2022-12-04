package com.github.renamrgb.lojavirtual.application;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MessageHandler {
    RESOURCE_NOT_FOUND("Recurso com id %s não encontrado."),
    RESOURCE_NOT_FOUND_AND_NAME_RESOURCE("Recurso %s com id %s não encontrado."),
    INVALID_PRICE("O Preço de custo não pode ser maior que o preço de venda"),
    DATABASE_VIOLATION_RELATION_ENTITY("Não é possivel remover %s de id %s pois existe %s associadas.");


    private final String message;
}
