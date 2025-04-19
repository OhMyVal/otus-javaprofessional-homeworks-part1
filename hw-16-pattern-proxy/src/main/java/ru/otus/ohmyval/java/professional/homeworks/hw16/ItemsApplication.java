package ru.otus.ohmyval.java.professional.homeworks.hw16;

import java.sql.SQLException;

public class ItemsApplication {
    public static void main(String[] args) {
        DataSourceSingleton dataSourceSingleton = null;
        try {
            System.out.println("Сервер запущен");
            dataSourceSingleton = DataSourceSingleton.getInstance();
            dataSourceSingleton.connect("jdbc:h2:file:./db;MODE=PostgreSQL");

            ItemsDao itemsDao = new ItemsDao(dataSourceSingleton);
            ItemsService itemsService = new ItemsService(itemsDao);

            DbMigrator dbMigrator = new DbMigrator(dataSourceSingleton);
            dbMigrator.migrate("dbinit.sql");

//            System.out.println(itemsDao.getAllItems());
//            itemsDao.save(new Item(null, "milk", 100));
//            itemsDao.save(new Item(null, "bread", 150));
//            itemsDao.save(new Item(null, "tea", 50));
//            System.out.println(itemsDao.getAllItems());

            System.out.println(itemsDao.getAllItems());
            itemsService.saveItems();
            System.out.println(itemsDao.getAllItems());
            itemsService.increasePrice();
            System.out.println(itemsDao.getAllItems());

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

