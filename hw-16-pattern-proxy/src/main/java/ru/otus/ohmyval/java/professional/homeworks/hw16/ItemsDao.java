package ru.otus.ohmyval.java.professional.homeworks.hw16;

public class ItemsDao {
    private DataSourceSingleton dataSourceSingleton;

    public ItemsDao(DataSourceSingleton dataSourceSingleton) {
        this.dataSourceSingleton = dataSourceSingleton;
    }
}
