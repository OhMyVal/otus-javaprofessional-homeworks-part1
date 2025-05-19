package ru.otus.ohmyval.java.basic.homeworks.hw23;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {
    private static Logger logger = LoggerFactory.getLogger(Application.class);
    public static void main(String[] args) {
        ProductRepository productRepository = new ProductRepositoryImpl();
        logger.info(productRepository.getProductList().toString());
        Cart cart = new CartImpl(productRepository);
        cart.addProductById(2);
        cart.addProductById(2);
        cart.addProductById(5);
        cart.addProductById(7);
        cart.addProductById(3);
        cart.addProductById(49);
        cart.deleteProductById(2);
        cart.deleteProductById(1);
        cart.deleteProductById(22);
        logger.info(cart.getCart().toString());
    }
}
