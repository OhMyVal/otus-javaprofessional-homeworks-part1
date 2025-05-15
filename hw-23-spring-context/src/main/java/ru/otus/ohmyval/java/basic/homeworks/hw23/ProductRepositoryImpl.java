package ru.otus.ohmyval.java.basic.homeworks.hw23;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class ProductRepositoryImpl {
    private static Logger logger = LoggerFactory.getLogger(ProductRepositoryImpl.class);
    private List<Product> productList;

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
    }

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
