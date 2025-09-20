package ru.otus.ohmyval.java.professional.homeworks.hw26;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.otus.ohmyval.java.professional.homeworks.hw26.entities.Product;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class HibernateApp {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("configs/hibernate.cfg.xml")
                .buildSessionFactory();
        Session session = null;
        try {
            String sql = Files.lines(Paths.get("hw-26-jpa-hibernate/src/main/resources/dbinit.sql")).collect(Collectors.joining(" "));
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();

            session = factory.getCurrentSession();
            session.beginTransaction();
            Product product = session.get(Product.class, 1L);
//            product.getClients().clear();
            System.out.println(product);
            System.out.println(product.getClients());
            session.getTransaction().commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            factory.close();
            if (session != null) {
                session.close();
            }
        }
    }
}

