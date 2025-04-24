package ru.otus.ohmyval.java.professional.homeworks.hw16;

import java.sql.SQLException;

public class ItemsServiceProxy {
    private DataSourceSingleton dataSourceSingleton;
    private ItemsService itemsService;

    public ItemsServiceProxy(DataSourceSingleton dataSourceSingleton, ItemsService itemsService) {
        this.dataSourceSingleton = dataSourceSingleton;
        this.itemsService = itemsService;
    }

    public void saveItemsTransaction() throws SQLException {
        try {
            dataSourceSingleton.getConnection().setAutoCommit(false);
            itemsService.saveItems();
            dataSourceSingleton.getConnection().setAutoCommit(true);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dataSourceSingleton.getConnection().rollback();
            dataSourceSingleton.getConnection().setAutoCommit(true);
        }
    }

    public void increasePriceTransaction() throws SQLException {
        try {
            dataSourceSingleton.getConnection().setAutoCommit(false);
            itemsService.increasePrice();
            dataSourceSingleton.getConnection().setAutoCommit(true);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dataSourceSingleton.getConnection().rollback();
            dataSourceSingleton.getConnection().setAutoCommit(true);
        }
    }
}
