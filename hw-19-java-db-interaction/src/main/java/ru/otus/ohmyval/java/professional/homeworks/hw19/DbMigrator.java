package ru.otus.ohmyval.java.professional.homeworks.hw19;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class DbMigrator {
    private DataSource dataSource;

    public DbMigrator(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void migrate(String fileName) {
        try (InputStream inputStream = DbMigrator.class.getClassLoader().getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            StringBuilder dbInit = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                dbInit.append(line).append("\n");
            }
            dataSource.getStatement().executeUpdate(dbInit.toString());

        } catch (Exception e) {
            throw new ORMException("Что-то пошло не так при создании таблицы");
        }
    }
}
