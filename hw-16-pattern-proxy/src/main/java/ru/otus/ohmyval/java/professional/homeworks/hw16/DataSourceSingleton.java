package ru.otus.ohmyval.java.professional.homeworks.hw16;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public final class DataSourceSingleton {
    private String url;
    private Connection connection;
    private Statement statement;
    private static final DataSourceSingleton INSTANCE = new DataSourceSingleton();

    public static DataSourceSingleton getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public void connect(String url) throws SQLException {
        connection = DriverManager.getConnection(url);
        statement = connection.createStatement();
        System.out.println("Установлено соединение с БД: " + url);
    }

    public void close() {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("От БД отключились");
    }
}
