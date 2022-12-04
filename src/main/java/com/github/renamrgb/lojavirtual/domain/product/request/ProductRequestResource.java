package com.github.renamrgb.lojavirtual.domain.product.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.Set;

public record ProductRequestResource(
        @NotBlank @Size(min = 5, max = 60) String title,
        @NotBlank @Size(min = 5, max = 5000) String description,
        @NotBlank @Size(min = 3, max = 12) String sku,
        String ean,
        String warrantyTime,
        @NotNull @PositiveOrZero BigDecimal costPrice,
        @NotNull @PositiveOrZero BigDecimal salePrice,
        @NotNull @Valid ProductCategoryRequestResource category,
        @NotNull @Valid ProductBrandRequestResource brand,
        Set<ProductImageRequestResource> imagens,
        Set<ProductAttributesRequestResource> attributes
) {
}
