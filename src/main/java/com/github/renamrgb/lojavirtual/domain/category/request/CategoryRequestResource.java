package com.github.renamrgb.lojavirtual.domain.category.request;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CategoryRequestResource(
        Long id,
        @NotBlank @Length(max = 60) String name
) {
}
