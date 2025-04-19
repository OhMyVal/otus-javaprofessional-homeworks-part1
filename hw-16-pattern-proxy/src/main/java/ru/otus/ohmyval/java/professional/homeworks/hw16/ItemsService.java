package ru.otus.ohmyval.java.professional.homeworks.hw16;

import java.sql.SQLException;
import java.util.List;

public class ItemsService {
   private ItemsDao itemsDao;

    public ItemsService(ItemsDao itemsDao) {
        this.itemsDao = itemsDao;
    }
    public void saveItems() throws SQLException {
        for (int i = 0; i < 10; i++) {
            itemsDao.save(new Item(null, "milk" + (i + 1), 100 + 10 * (i + 1)));
        }
    }
    public void increasePrice() throws SQLException {
        List<Item> result = itemsDao.getAllItems();
        for (int i = 0; i < result.size(); i++) {
            itemsDao.updatePrice(result.get(i));
        }
    }
}
