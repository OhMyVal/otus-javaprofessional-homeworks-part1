package ru.otus.ohmyval.java.professional.homeworks.hw19;

import java.sql.SQLException;

public class BonusService {
    private DataSource dataSource;

    public BonusService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void createBonus(String login, int amount) {
        // dataSource.getStatement()...
    }
}
