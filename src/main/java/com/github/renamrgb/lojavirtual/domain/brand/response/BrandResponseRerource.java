package com.github.renamrgb.lojavirtual.domain.brand.response;

import com.github.renamrgb.lojavirtual.domain.brand.Brand;

public record BrandResponseRerource(
        Long id,
        String name
) {
    public BrandResponseRerource(Brand data) {
        this(data.getId(), data.getName());
    }
}
