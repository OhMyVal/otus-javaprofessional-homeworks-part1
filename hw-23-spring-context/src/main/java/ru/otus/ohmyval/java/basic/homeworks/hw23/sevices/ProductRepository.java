package ru.otus.ohmyval.java.basic.homeworks.hw23.sevices;

import ru.otus.ohmyval.java.basic.homeworks.hw23.Product;

import java.util.List;

public interface ProductRepository {
    public List<Product> getProductList();

    public Product getProductById(int id);
}
