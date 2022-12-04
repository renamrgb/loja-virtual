package com.github.renamrgb.lojavirtual.domain.product;

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

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
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
    @JoinColumn(name = "product_id")
    private Product product;
}
