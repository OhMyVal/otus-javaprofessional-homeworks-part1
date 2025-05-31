package ru.otus.ohmyval.java.professional.homeworks.hw24.controllers;

import hw24.dtos.ProductDto;
import hw24.entities.Product;
import hw24.services.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductsController {

    private final ProductsService productsService;

    private static final Function<Product, ProductDto> ENTITY_TO_DTO = i -> new ProductDto(i.getId(), i.getTitle(), i.getPrice());

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productsService.getAllProducts().stream().map(ENTITY_TO_DTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return productsService.getProductById(id).map(ENTITY_TO_DTO).get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto createNewProduct(@RequestBody ProductDto createProductDto) {
        Product newProduct = productsService.createNewProduct(createProductDto);
        return new ProductDto(newProduct.getId(), newProduct.getTitle(), newProduct.getPrice());
    }

}
