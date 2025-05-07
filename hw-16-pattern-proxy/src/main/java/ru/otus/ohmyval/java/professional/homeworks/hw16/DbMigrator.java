package ru.otus.ohmyval.java.professional.homeworks.hw16;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class DbMigrator {
    private DataSourceSingleton dataSourceSingleton;

    public DbMigrator(DataSourceSingleton dataSourceSingleton) {
        this.dataSourceSingleton = dataSourceSingleton;
    }

    public void migrate(String fileName) {
        try (InputStream inputStream = DbMigrator.class.getClassLoader().getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            StringBuilder dbInit = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                dbInit.append(line).append("\n");
            }
            dataSourceSingleton.getStatement().executeUpdate(dbInit.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
