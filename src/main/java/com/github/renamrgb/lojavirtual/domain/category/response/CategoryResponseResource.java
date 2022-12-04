package com.github.renamrgb.lojavirtual.domain.category.response;

import com.github.renamrgb.lojavirtual.domain.category.Category;

public record CategoryResponseResource(
        Long id,
        String name
) {
    public CategoryResponseResource(Category data) {
        this(data.getId(), data.getName());
    }
}
