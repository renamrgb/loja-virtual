package com.github.renamrgb.lojavirtual.domain.category;

import com.github.renamrgb.lojavirtual.domain.category.request.CategoryRequestResource;
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
@Table(name = "CATEGORY")
@SequenceGenerator(name = "SEQ", sequenceName = "SEQ_CATEGORY", initialValue = 1, allocationSize = 20)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_BRAND")
    private Long id;
    @Column(name = "NAME", length = 60)
    private String name;

    public Category(CategoryRequestResource data) {
        this.name = data.name();
    }

    public void update(CategoryRequestResource data) {
        this.name = data.name();
    }
}
