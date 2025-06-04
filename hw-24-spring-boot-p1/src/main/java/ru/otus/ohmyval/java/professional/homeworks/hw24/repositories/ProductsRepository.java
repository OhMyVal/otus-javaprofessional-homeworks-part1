package ru.otus.ohmyval.java.professional.homeworks.hw24.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.ohmyval.java.professional.homeworks.hw24.entities.Product;
import ru.otus.ohmyval.java.professional.homeworks.hw24.storage.ProductsStorage;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductsRepository {
    private ProductsStorage productsStorage;

    public ProductsStorage getProductsStorage() {
        return productsStorage;
    }

    public ProductsRepository(ProductsStorage productsStorage) {
        this.productsStorage = productsStorage;
    }

    public List<Product> findAll() {
        return productsStorage.getProductList();
    }

    public Optional<Product> findById(Long id) {
        return productsStorage.getProductById(id);
    }

    public Product save(Product newProduct) {
        return productsStorage.saveNewProduct(newProduct);
    }

}
