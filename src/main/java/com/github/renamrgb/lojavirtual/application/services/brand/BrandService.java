package com.github.renamrgb.lojavirtual.application.services.brand;

import com.github.renamrgb.lojavirtual.application.MessageHandler;
import com.github.renamrgb.lojavirtual.application.services.exceptions.ResourceNotFoundException;
import com.github.renamrgb.lojavirtual.domain.brand.Brand;
import com.github.renamrgb.lojavirtual.domain.brand.request.BrandRequestResource;
import com.github.renamrgb.lojavirtual.domain.brand.response.BrandResponseRerource;
import com.github.renamrgb.lojavirtual.infra.repositories.brand.BrandRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
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
    public List<BrandResponseRerource> getAll() {
        List<Brand> all = brandRepository.findAll();
        return all
                .stream()
                .map(BrandResponseRerource::new)
                .collect(Collectors.toList());
    }


    @Transactional
    public BrandResponseRerource update(Long id, BrandRequestResource resource) {
        try {
            Brand brand = brandRepository.getReferenceById(id);
            brand.update(resource);
            return new BrandResponseRerource(brand);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(String.format(MessageHandler.RESOURCE_NOT_FOUND.getMessage(), id));
        } catch (Exception e) {
            log.error("Erro não esperado {}", e);
            throw e;
        }
    }

    public void delete(Long id) {
        try {
            brandRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(String.format(MessageHandler.RESOURCE_NOT_FOUND.getMessage(), id));
        } catch (Exception e) {
            log.error("Erro não esperado {}", e);
            throw e;
        }
    }
}