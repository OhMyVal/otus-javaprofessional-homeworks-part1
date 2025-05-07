package ru.otus.ohmyval.java.professional.homeworks.hw16;

import java.sql.Connection;
import java.sql.SQLException;

public class ItemsServiceProxy {
    private DataSourceSingleton dataSourceSingleton;
    private ItemsService itemsService;

    public ItemsServiceProxy(DataSourceSingleton dataSourceSingleton, ItemsService itemsService) {
        this.dataSourceSingleton = dataSourceSingleton;
        this.itemsService = itemsService;
    }

    public void saveItemsTransaction() throws SQLException {
        Connection connection = dataSourceSingleton.getConnection();
        try {
            connection.setAutoCommit(false);
            itemsService.saveItems();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.rollback();
            connection.setAutoCommit(true);
        }
    }

    public void increasePriceTransaction() throws SQLException {
        Connection connection = dataSourceSingleton.getConnection();
        try {
            connection.setAutoCommit(false);
            itemsService.increasePrice();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.rollback();
            connection.setAutoCommit(true);
        }
    }
}
