package ru.otus.ohmyval.java.basic.homeworks.hw23.sevices;

import ru.otus.ohmyval.java.basic.homeworks.hw23.Product;

import java.util.List;

public interface Cart {
    public List<Product> getCart();

    public List<Product> addProductById(int id);

    public List<Product> deleteProductById(int id);
}
