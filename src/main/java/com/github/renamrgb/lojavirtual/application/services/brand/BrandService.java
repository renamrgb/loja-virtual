package com.github.renamrgb.lojavirtual.application.services.brand;

import com.github.renamrgb.lojavirtual.domain.brand.Brand;
import com.github.renamrgb.lojavirtual.domain.brand.request.BrandRequestResource;
import com.github.renamrgb.lojavirtual.domain.brand.response.BrandResponseRerource;
import com.github.renamrgb.lojavirtual.infra.repositories.brand.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;

    @Transactional
    public BrandResponseRerource save(BrandRequestResource resource) {
        Brand brand = new Brand(resource);
        brand = brandRepository.save(brand);
        return new BrandResponseRerource(brand);
    }

    @Transactional(readOnly = true)
    public BrandResponseRerource getById(Long id) {
        Optional<Brand> optBrand = brandRepository.findById(id);
        Brand brand = optBrand.orElseThrow();
        return new BrandResponseRerource(brand);
    }

    @Transactional(readOnly = true)
    public Page<BrandResponseRerource> getAll(Pageable page) {
        Page<Brand> all = brandRepository.findAll(page);
        return all.map(BrandResponseRerource::new);
    }


    @Transactional
    public BrandResponseRerource update(Long id, BrandRequestResource resource) {
       try {
           Brand brand = brandRepository.getReferenceById(id);
           brand.update(resource);
           return new BrandResponseRerource(brand);
       } catch (Exception e) {
           throw e;
       }
    }

    public void delete(Long id) {
        try {
            brandRepository.deleteById(id);
        }catch (Exception e) {
            throw e;
        }
    }
}