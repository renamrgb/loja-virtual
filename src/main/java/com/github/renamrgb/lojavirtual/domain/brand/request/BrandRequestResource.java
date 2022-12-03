package com.github.renamrgb.lojavirtual.domain.brand.request;

import jakarta.validation.constraints.NotBlank;

public record BrandRequestResource(
        @NotBlank String name
) {
}
