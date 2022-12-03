package com.github.renamrgb.lojavirtual.api.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ValidatorError extends StandardError {
    List<FieldMessage> details = new ArrayList<>();

    public void addErro(String field, String message) {
        details.add(new FieldMessage(field, message));
    }
}
