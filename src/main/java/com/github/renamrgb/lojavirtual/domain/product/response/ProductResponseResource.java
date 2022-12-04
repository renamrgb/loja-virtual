package com.github.renamrgb.lojavirtual.domain.product.response;

import com.github.renamrgb.lojavirtual.domain.brand.response.BrandResponseResource;
import com.github.renamrgb.lojavirtual.domain.category.response.CategoryResponseResource;
import com.github.renamrgb.lojavirtual.domain.product.Product;
import com.github.renamrgb.lojavirtual.domain.product.ProductAttributes;
import com.github.renamrgb.lojavirtual.domain.product.ProductImage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class ProductResponseResource implements Serializable {
    private Long id;
    private String title;
    private String description;
    private String sku;
    private String ean;
    private String warrantyTime;
    private BigDecimal costPrice;
    private BigDecimal salePrice;
    private CategoryResponseResource category;
    private BrandResponseResource brand;
    private Set<ProductImageResponseResource> imagens = new HashSet<>();
    private Set<ProductAttributesResponseResource> attributes = new HashSet<>();

    public ProductResponseResource(Product data) {
        this.id = data.getId();
        this.title = data.getTitle();
        this.description = data.getDescription();
        this.sku = data.getSku();
        this.ean = data.getEan();
        this.warrantyTime = data.getWarrantyTime();
        this.costPrice = data.getCostPrice();
        this.salePrice = data.getSalePrice();
        this.category = new CategoryResponseResource(data.getCategory());
        this.brand = new BrandResponseResource(data.getBrand());

        for (ProductImage p : data.getImages()) {
            this.imagens.add(new ProductImageResponseResource(p.getId(), p.getLink()));
        }

        for (ProductAttributes p : data.getAttributes()) {
            this.attributes.add(new ProductAttributesResponseResource(p.getId(), p.getAttibute(), p.getValueAttibute()));
        }
    }
}
