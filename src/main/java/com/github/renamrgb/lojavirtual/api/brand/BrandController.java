package com.github.renamrgb.lojavirtual.api.brand;

import com.github.renamrgb.lojavirtual.application.services.brand.BrandService;
import com.github.renamrgb.lojavirtual.domain.brand.request.BrandRequestResource;
import com.github.renamrgb.lojavirtual.domain.brand.response.BrandResponseRerource;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/brands", produces = MediaType.APPLICATION_JSON_VALUE)
public class BrandController {
    private final BrandService brandService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public BrandResponseRerource save(@RequestBody @Valid BrandRequestResource resource) {
        return brandService.save(resource);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<BrandResponseRerource> getAll() {
        return brandService.getAll();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public BrandResponseRerource getById(@PathVariable Long id) {
        return brandService.getById(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public BrandResponseRerource update(@PathVariable Long id, @RequestBody BrandRequestResource resource) {
        return brandService.update(id, resource);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        brandService.delete(id);
    }
}