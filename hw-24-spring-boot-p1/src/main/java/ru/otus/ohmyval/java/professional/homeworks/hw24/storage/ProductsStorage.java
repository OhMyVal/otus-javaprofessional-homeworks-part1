package ru.otus.ohmyval.java.professional.homeworks.hw24.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.otus.ohmyval.java.professional.homeworks.hw24.entities.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class ProductsStorage {
    private static Logger logger = LoggerFactory.getLogger(ProductsStorage.class);
    private List<Product> productList;

    public List<Product> getProductList() {
        logger.info("list of all products");
        return productList;
    }

    public ProductsStorage() {
        List<Product> productList = new ArrayList<>();
        this.productList = productList;
        logger.info("New product list is created");
    }

    public Optional<Product> getProductById(Long id) {
        for (int i = 0; i < productList.size(); i++) {
            if (Objects.equals(productList.get(i).getId(), id)) {
                return Optional.of(productList.get(i));
            }
        }
        logger.info("In list there's no product with id = " + id);
        return Optional.empty();
    }

    public Product saveNewProduct(Product newProduct) {
        if (productList.contains(newProduct)) {
            logger.info("List already contains" + newProduct.toString());
            return null;
        }
        productList.add(newProduct);
        logger.info(newProduct.toString() + " is added to list");
        return newProduct;
    }
}
