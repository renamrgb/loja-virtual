package com.github.renamrgb.lojavirtual.application.services.category;

import com.github.renamrgb.lojavirtual.application.MessageHandler;
import com.github.renamrgb.lojavirtual.application.services.exceptions.ResourceNotFoundException;
import com.github.renamrgb.lojavirtual.domain.category.Category;
import com.github.renamrgb.lojavirtual.domain.category.request.CategoryRequestResource;
import com.github.renamrgb.lojavirtual.domain.category.response.CategoryResponseResource;
import com.github.renamrgb.lojavirtual.infra.repositories.category.CategoryRepository;
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
@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional
    public CategoryResponseResource save(CategoryRequestResource resource) {
        Category category = new Category(resource);
        category = categoryRepository.save(category);
        return new CategoryResponseResource(category);
    }

    @Transactional(readOnly = true)
    public CategoryResponseResource getById(Long id) {
        Optional<Category> optBrand = categoryRepository.findById(id);
        Category category = optBrand.orElseThrow();
        return new CategoryResponseResource(category);
    }

    @Transactional(readOnly = true)
    public List<CategoryResponseResource> getAll() {
        List<Category> all = categoryRepository.findAll();
        return all
                .stream()
                .map(CategoryResponseResource::new)
                .collect(Collectors.toList());
    }


    @Transactional
    public CategoryResponseResource update(Long id, CategoryRequestResource resource) {
        try {
            Category category = categoryRepository.getReferenceById(id);
            category.update(resource);
            return new CategoryResponseResource(category);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(String.format(MessageHandler.RESOURCE_NOT_FOUND.getMessage(), id));
        } catch (Exception e) {
            log.error("Erro não esperado {}", e);
            throw e;
        }
    }

    public void delete(Long id) {
        try {
            categoryRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(String.format(MessageHandler.RESOURCE_NOT_FOUND.getMessage(), id));
        } catch (Exception e) {
            log.error("Erro não esperado {}", e);
            throw e;
        }
    }
}
