package ru.otus.ohmyval.java.professional.homeworks.hw24.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.ohmyval.java.professional.homeworks.hw24.dtos.ProductDto;
import ru.otus.ohmyval.java.professional.homeworks.hw24.entities.Product;
import ru.otus.ohmyval.java.professional.homeworks.hw24.repositories.ProductsRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductsService {
    private final ProductsRepository productsRepository;

    public List<Product> getAllProducts() {
        return productsRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productsRepository.findById(id);
    }

    public Product createNewProduct(ProductDto productDto) {
        Product product = new Product(productDto.id(), productDto.title(), productDto.price());
        return productsRepository.save(product);
    }
}
