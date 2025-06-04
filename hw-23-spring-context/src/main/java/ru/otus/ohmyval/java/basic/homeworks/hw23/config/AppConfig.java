package ru.otus.ohmyval.java.basic.homeworks.hw23.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.otus.ohmyval.java.basic.homeworks.hw23.sevices.Cart;
import ru.otus.ohmyval.java.basic.homeworks.hw23.sevices.ProductRepository;
import ru.otus.ohmyval.java.basic.homeworks.hw23.sevices.impl.CartImpl;


@Configuration
public class AppConfig {

    @Bean
    @Scope("prototype")
    public Cart cart(ProductRepository productRepository) {
        return new CartImpl(productRepository);
    }
}
