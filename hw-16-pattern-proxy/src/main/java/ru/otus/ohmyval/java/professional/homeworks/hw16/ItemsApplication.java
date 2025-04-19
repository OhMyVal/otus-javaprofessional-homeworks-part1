package ru.otus.ohmyval.java.professional.homeworks.hw16;

import java.sql.SQLException;

public class ItemsApplication {
    public static void main(String[] args) {
        DataSourceSingleton dataSourceSingleton = null;
        try {
            System.out.println("Сервер запущен");
            dataSourceSingleton = DataSourceSingleton.getInstance();
            dataSourceSingleton.connect("jdbc:h2:file:./db;MODE=PostgreSQL");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (dataSourceSingleton != null) {
                dataSourceSingleton.close();
            }
            System.out.println("Сервер завершил свою работу");
        }
    }
}

