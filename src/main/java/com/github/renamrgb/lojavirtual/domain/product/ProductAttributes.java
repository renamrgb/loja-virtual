package com.github.renamrgb.lojavirtual.domain.product;

import com.github.renamrgb.lojavirtual.domain.product.request.ProductAttributesRequestResource;
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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "PRODUCT_ATTRIBUTES")
@SequenceGenerator(name = "SEQ_PRODUCT_ATTRIBUTES", initialValue = 1, allocationSize = 20)
public class ProductAttributes {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PRODUCT_ATTRIBUTES")
    private Long id;
    private String attibute;
    private String valueAttibute;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public ProductAttributes(ProductAttributesRequestResource data, Product product) {
        this.attibute = data.attribute();
        this.valueAttibute = data.value();
        this.product = product;
    }
}
