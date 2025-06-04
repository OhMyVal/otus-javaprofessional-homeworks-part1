package ru.otus.ohmyval.java.professional.homeworks.hw19;

import java.sql.SQLException;

public class MockChatServer {
    public static void main(String[] args) {
        DataSource dataSource = null;
        try {
            System.out.println("Сервер чата запущен");
            dataSource = new DataSource("jdbc:h2:file:./db;MODE=PostgreSQL");
            dataSource.connect();

            UsersDao usersDao = new UsersDao(dataSource);

            DbMigrator dbMigrator = new DbMigrator(dataSource);
            dbMigrator.migrate("dbinit.sql");

            AbstractRepository<User> usersRepository = new AbstractRepository<>(dataSource, User.class);

            System.out.println(usersRepository.findAll(User.class));

            usersRepository.save(new User(null, "A", "A", "A"));
            usersRepository.save(new User(null, "B", "B", "B"));
            usersRepository.save(new User(null, "C", "C", "C"));

            System.out.println(usersRepository.findAll(User.class));

            System.out.println(usersRepository.findById(3L, User.class));
            usersRepository.deleteById(2L);
            usersRepository.update(new User(null, "D", "D", "D"), 3L);

            System.out.println(usersRepository.findAll(User.class));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (dataSource != null) {
                dataSource.close();
            }
            System.out.println("Сервер чата завершил свою работу");
        }
    }
}
