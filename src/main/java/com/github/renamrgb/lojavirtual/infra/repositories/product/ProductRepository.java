package com.github.renamrgb.lojavirtual.infra.repositories.product;

import com.github.renamrgb.lojavirtual.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
