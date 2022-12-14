package com.github.renamrgb.lojavirtual.domain.brand.response;

import com.github.renamrgb.lojavirtual.domain.brand.Brand;

public record BrandResponseResource(
        Long id,
        String name
) {
    public BrandResponseResource(Brand data) {
        this(data.getId(), data.getName());
    }
}
