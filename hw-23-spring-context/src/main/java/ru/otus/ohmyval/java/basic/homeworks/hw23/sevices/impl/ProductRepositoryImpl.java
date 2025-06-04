package ru.otus.ohmyval.java.basic.homeworks.hw23.sevices.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.otus.ohmyval.java.basic.homeworks.hw23.Product;
import ru.otus.ohmyval.java.basic.homeworks.hw23.sevices.ProductRepository;

import java.util.ArrayList;
import java.util.List;


@Component
public class ProductRepositoryImpl implements ProductRepository {
    private static Logger logger = LoggerFactory.getLogger(ProductRepositoryImpl.class);
    private List<Product> productList;

    @Override
    public List<Product> getProductList() {
        logger.info("Полный список продуктов");
        return productList;
    }

    public ProductRepositoryImpl() {
        List<Product> productList = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            productList.add(new Product(i, "milk" + i, 100 + (i * 10)));
        }
        this.productList = productList;
        logger.info("Создали новый список продуктов");
        logger.info(getProductList().toString());
    }

    @Override
    public Product getProductById(int id) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId() == id) {
                return productList.get(i);
            }
        }
        logger.info("В списке продуктов нет продукта с id = " + id);
        return null;
    }
}
