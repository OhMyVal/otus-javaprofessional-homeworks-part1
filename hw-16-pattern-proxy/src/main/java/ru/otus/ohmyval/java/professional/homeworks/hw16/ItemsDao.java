package ru.otus.ohmyval.java.professional.homeworks.hw16;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemsDao {
    private DataSourceSingleton dataSourceSingleton;

    public ItemsDao(DataSourceSingleton dataSourceSingleton) {
        this.dataSourceSingleton = dataSourceSingleton;
    }

    public void save(Item item) throws SQLException {
        dataSourceSingleton.getStatement().executeUpdate(String.format("insert into items (title, price) values ('%s', '%s');", item.getTitle(), item.getPrice()));
    }

    public List<Item> getAllItems() {
        List<Item> result = new ArrayList<>();
        try (ResultSet rs = dataSourceSingleton.getStatement().executeQuery("select * from items")) {
            while (rs.next() != false) {
                result.add(new Item(rs.getLong("id"), rs.getString("title"), rs.getInt("price")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.unmodifiableList(result);
    }
}
