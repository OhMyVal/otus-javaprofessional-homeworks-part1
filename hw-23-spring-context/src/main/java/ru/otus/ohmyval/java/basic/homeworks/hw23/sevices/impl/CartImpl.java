package ru.otus.ohmyval.java.basic.homeworks.hw23.sevices.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.ohmyval.java.basic.homeworks.hw23.Product;
import ru.otus.ohmyval.java.basic.homeworks.hw23.sevices.Cart;
import ru.otus.ohmyval.java.basic.homeworks.hw23.sevices.ProductRepository;

import java.util.ArrayList;
import java.util.List;


public class CartImpl implements Cart {
    private static Logger logger = LoggerFactory.getLogger(CartImpl.class);
    private ProductRepository productRepository;
    private List<Product> cart;

    @Override
    public List<Product> getCart() {
        return cart;
    }

    public CartImpl(ProductRepository productRepository) {
        List<Product> cart = new ArrayList<>();
        this.cart = cart;
        this.productRepository = productRepository;
        logger.info("Создали новую корзину");
    }

    @Override
    public List<Product> addProductById(int id) {
        if (productRepository.getProductById(id) == null) {
            logger.info("В ProductRepository нет продукта с id = " + id);
            return null;
        }
        if (cart.contains(productRepository.getProductById(id))) {
            logger.info("В корзине уже есть продукт с id = " + id);
            return cart;
        }
        cart.add(productRepository.getProductById(id));
        logger.info("Добавили в корзину продукт с id = " + id);
        return cart;
    }

    @Override
    public List<Product> deleteProductById(int id) {
        if (productRepository.getProductById(id) == null) {
            logger.info("В ProductRepository нет продукта с id = " + id);
            return cart;
        }
        if (cart.contains(productRepository.getProductById(id))) {
            cart.remove(productRepository.getProductById(id));
            logger.info("Удалили из корзины продукт с id = " + id);
            return cart;
        }
        logger.info("В корзине нет продукта с id = " + id);
        return cart;
    }
}
