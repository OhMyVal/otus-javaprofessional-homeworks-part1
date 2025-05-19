package ru.otus.ohmyval.java.basic.homeworks.hw23;

import java.util.List;

public interface Cart {
    public List<Product> getCart();
    public List<Product> addProductById(int id);
    public List<Product> deleteProductById(int id);
}
