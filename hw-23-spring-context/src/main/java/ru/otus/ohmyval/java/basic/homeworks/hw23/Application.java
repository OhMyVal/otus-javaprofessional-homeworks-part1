package ru.otus.ohmyval.java.basic.homeworks.hw23;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.ohmyval.java.basic.homeworks.hw23.sevices.Cart;


@ComponentScan
public class Application {
    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        Cart cart = context.getBean(Cart.class);

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
