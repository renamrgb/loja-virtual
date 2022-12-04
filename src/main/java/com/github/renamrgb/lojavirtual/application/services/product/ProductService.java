package com.github.renamrgb.lojavirtual.application.services.product;

import com.github.renamrgb.lojavirtual.application.MessageHandler;
import com.github.renamrgb.lojavirtual.application.services.exceptions.InvalidePriceException;
import com.github.renamrgb.lojavirtual.application.services.exceptions.ResourceNotFoundException;
import com.github.renamrgb.lojavirtual.domain.brand.Brand;
import com.github.renamrgb.lojavirtual.domain.category.Category;
import com.github.renamrgb.lojavirtual.domain.product.Product;
import com.github.renamrgb.lojavirtual.domain.product.request.ProductRequestResource;
import com.github.renamrgb.lojavirtual.domain.product.request.ProductUpdateRequestResource;
import com.github.renamrgb.lojavirtual.domain.product.response.ProductResponseResource;
import com.github.renamrgb.lojavirtual.infra.repositories.brand.BrandRepository;
import com.github.renamrgb.lojavirtual.infra.repositories.category.CategoryRepository;
import com.github.renamrgb.lojavirtual.infra.repositories.product.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;

    @Transactional
    public ProductResponseResource save(ProductRequestResource resource) {
        Product product = translateTo(resource);
        product = productRepository.save(product);
        return new ProductResponseResource(product);
    }

    @Transactional(readOnly = true)
    public ProductResponseResource getById(Long id) {
        Optional<Product> optProduct = productRepository.findById(id);
        Product product = optProduct
                .orElseThrow(() -> new ResourceNotFoundException(String.format(MessageHandler.RESOURCE_NOT_FOUND.getMessage(), id)));
        return new ProductResponseResource(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductResponseResource> getAll(Pageable pageable) {
        Page<Product> all = productRepository.findAll(pageable);
        return all.map(ProductResponseResource::new);
    }

    @Transactional
    public ProductResponseResource update(Long id, ProductUpdateRequestResource resource) {
        try {
            Product product = productRepository.getReferenceById(id);
            prepareToUpdate(product, resource);
            product = productRepository.save(product);
            return new ProductResponseResource(product);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(String.format(MessageHandler.RESOURCE_NOT_FOUND.getMessage(), id));
        }
    }

    public void delete(Long id) {
        try {
            productRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(String.format(MessageHandler.RESOURCE_NOT_FOUND.getMessage(), id));
        } catch (Exception e) {
            log.error("Erro não esperado {}", e);
            throw e;
        }
    }

    private void prepareToUpdate(Product product, ProductUpdateRequestResource resource) {
        isValidPrice(resource.costPrice(), resource.salePrice());
        if (!Objects.equals(product.getCategory().getId(), resource.category().id())) {
            Category category = getCategoryOrThrow(resource.category().id());
            product.setCategory(category);
        }

        if (!Objects.equals(product.getBrand().getId(), resource.brand().id())) {
            Brand brand = getBrandOrThrow(resource.brand().id());
            product.setBrand(brand);
        }

        product.setTitle(resource.title());
        product.setEan(resource.ean());
        product.setCostPrice(resource.costPrice());
        product.setSalePrice(resource.salePrice());
        product.setDescription(resource.description());
        product.setWarrantyTime(resource.warrantyTime());
    }

    private Product translateTo(ProductRequestResource resource) {
        Category category = getCategoryOrThrow(resource.category().id());
        Brand brand = getBrandOrThrow(resource.brand().id());
        isValidPrice(resource.costPrice(), resource.salePrice());
        return new Product(resource, category, brand);
    }

    private Category getCategoryOrThrow(Long id) {
        return categoryRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format(MessageHandler.RESOURCE_NOT_FOUND_AND_NAME_RESOURCE.getMessage(),
                                        "Category", id)));
    }

    private Brand getBrandOrThrow(Long id) {
        return brandRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format(MessageHandler.RESOURCE_NOT_FOUND_AND_NAME_RESOURCE.getMessage(),
                                        "Brand", id)));
    }


    private void isValidPrice(BigDecimal costPrice, BigDecimal salePrice) {
        if (costPrice.compareTo(salePrice) > 0) {
            throw new InvalidePriceException(String.format(MessageHandler.INVALID_PRICE.getMessage()));
        }
    }

}
