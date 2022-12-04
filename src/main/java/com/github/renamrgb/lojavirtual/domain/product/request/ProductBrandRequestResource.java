package com.github.renamrgb.lojavirtual.domain.product.request;

import jakarta.validation.constraints.NotNull;

public record ProductBrandRequestResource(
        @NotNull Long id,
        String name
) {
}
