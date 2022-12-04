package com.github.renamrgb.lojavirtual.domain.brand.request;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record BrandRequestResource(
        Long id,
        @NotBlank @Length(max = 60) String name
) {
}
