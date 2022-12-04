package com.github.renamrgb.lojavirtual.domain.product.request;

import jakarta.validation.constraints.NotNull;

public record ProductCategoryRequestResource(
        @NotNull Long id,
        String name
) {
}
