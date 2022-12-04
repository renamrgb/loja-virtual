package com.github.renamrgb.lojavirtual.api.product;

import com.github.renamrgb.lojavirtual.application.services.product.ProductService;
import com.github.renamrgb.lojavirtual.domain.product.request.ProductRequestResource;
import com.github.renamrgb.lojavirtual.domain.product.request.ProductUpdateRequestResource;
import com.github.renamrgb.lojavirtual.domain.product.response.ProductResponseResource;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ProductResponseResource save(@RequestBody @Valid ProductRequestResource resource) {
        return productService.save(resource);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ProductResponseResource getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Page<ProductResponseResource> getAll(Pageable pageable) {
        return productService.getAll(pageable);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ProductResponseResource update (@PathVariable Long id,
                                           @RequestBody @Valid ProductUpdateRequestResource resource) {
        return productService.update(id, resource);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }
}
