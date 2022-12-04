package com.github.renamrgb.lojavirtual.domain.brand;

import com.github.renamrgb.lojavirtual.domain.brand.request.BrandRequestResource;
import com.github.renamrgb.lojavirtual.domain.product.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "BRAND")
@SequenceGenerator(name = "SEQ_BRAND", initialValue = 1, allocationSize = 20)
public class Brand implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_BRAND")
    private Long id;
    @Column(name = "NAME", length = 60)
    private String name;
    @OneToMany(mappedBy="brand")
    private Set<Product> products;

    public Brand(BrandRequestResource data) {
        this.name = data.name();
    }

    public void update(BrandRequestResource data) {
        this.name = data.name();
    }
}