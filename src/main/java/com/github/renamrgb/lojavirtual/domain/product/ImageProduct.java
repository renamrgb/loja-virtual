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

import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "PRODUCT_IMAGE")
@SequenceGenerator(name = "SEQ_PRODUCT_IMAGE", initialValue = 1, allocationSize = 20)
public class ImageProduct implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PRODUCT_IMAGE")
    private Long id;
    private String link;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
