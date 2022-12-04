package com.github.renamrgb.lojavirtual.infra.repositories.category;

import com.github.renamrgb.lojavirtual.domain.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
