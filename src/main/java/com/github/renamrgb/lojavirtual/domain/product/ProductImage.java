package com.github.renamrgb.lojavirtual.domain.product;

import com.github.renamrgb.lojavirtual.domain.product.request.ProductImageRequestResource;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "PRODUCT_IMAGE")
@SequenceGenerator(name = "SEQ_PRODUCT_IMAGE", initialValue = 1, allocationSize = 20)
public class ProductImage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PRODUCT_IMAGE")
    private Long id;
    private String link;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public ProductImage(ProductImageRequestResource data, Product product) {
        this.link = data.link();
        this.product = product;
    }
}
