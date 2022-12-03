package com.github.renamrgb.lojavirtual.domain.brand;

import com.github.renamrgb.lojavirtual.domain.brand.request.BrandRequestResource;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "BRAND")
@SequenceGenerator(name = "SEQ", sequenceName = "SEQ_BRAND", initialValue = 1, allocationSize = 20)
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_BRAND")
    private Long id;
    @Column(name = "NAME", length = 60, unique = true)
    private String name;

    public Brand(BrandRequestResource data) {
        this.name = data.name();
    }

    public void update(BrandRequestResource data) {
        this.name = data.name();
    }
}