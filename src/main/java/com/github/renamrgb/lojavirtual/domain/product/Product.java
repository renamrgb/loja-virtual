package com.github.renamrgb.lojavirtual.domain.product;

import com.github.renamrgb.lojavirtual.domain.brand.Brand;
import com.github.renamrgb.lojavirtual.domain.category.Category;
import com.github.renamrgb.lojavirtual.domain.product.request.ProductAttributesRequestResource;
import com.github.renamrgb.lojavirtual.domain.product.request.ProductImageRequestResource;
import com.github.renamrgb.lojavirtual.domain.product.request.ProductRequestResource;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "PRODUCT")
@SequenceGenerator(name = "SEQ_PRODUCT", initialValue = 1, allocationSize = 20)
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PRODUCT")
    private Long id;
    private String title;
    private String description;
    private String sku;
    private String ean;
    private String warrantyTime;
    private BigDecimal costPrice;
    private BigDecimal salePrice;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ProductImage> images = new HashSet<>();
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ProductAttributes> attributes = new HashSet<>();

    public Product(ProductRequestResource resource, Category category, Brand brand) {
        this.title = resource.title();
        this.description = resource.description();
        this.sku = resource.sku();
        this.ean = resource.ean();
        this.warrantyTime = resource.warrantyTime();
        this.costPrice = resource.costPrice();
        this.salePrice = resource.salePrice();
        this.category = category;
        this.brand = brand;

        for (ProductImageRequestResource r : resource.imagens()) {
            ProductImage productImage = new ProductImage(r, this);
            this.getImages().add(productImage);
        }

        for (ProductAttributesRequestResource r : resource.attributes()) {
            ProductAttributes productAttributes = new ProductAttributes(r, this);
            this.attributes.add(productAttributes);
        }

    }
}
