package ru.otus.ohmyval.java.professional.homeworks.hw19;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AbstractRepository<T> {
    private DataSource dataSource;
    private PreparedStatement psInsert;
    private PreparedStatement psSelectId;
    private List<Field> cachedFields;

    private Field idField;

    public AbstractRepository(DataSource dataSource, Class<T> cls) {
        this.dataSource = dataSource;
        this.prepareInsert(cls);
    }

    public void save(T entity) {
        try {
            for (int i = 0; i < cachedFields.size(); i++) {
                psInsert.setObject(i + 1, cachedFields.get(i).get(entity));
            }
            psInsert.executeUpdate();
        } catch (Exception e) {
            throw new ORMException("Что-то пошло не так при сохранении: " + entity);
        }
    }

    private void prepareInsert(Class cls) {
        if (!cls.isAnnotationPresent(RepositoryTable.class)) {
            throw new ORMException("Класс не предназначен для создания репозитория, не хватает аннотации @RepositoryTable");
        }
        String tableName = ((RepositoryTable) cls.getAnnotation(RepositoryTable.class)).title();
        StringBuilder query = new StringBuilder("insert into ");
        query.append(tableName).append(" (");
        // 'insert into users ('
        cachedFields = Arrays.stream(cls.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(RepositoryField.class))
                .filter(f -> !f.isAnnotationPresent(RepositoryIdField.class))
                .collect(Collectors.toList());
        for (Field f : cachedFields) { // TODO заменить на использование геттеров
            f.setAccessible(true);
        }
        for (Field f : cachedFields) {
            query.append(f.getName()).append(", ");
        }
        // 'insert into users (login, password, nickname, '
        query.setLength(query.length() - 2);
        query.append(") values (");
        // 'insert into users (login, password, nickname) values ('
        for (Field f : cachedFields) {
            query.append("?, ");
        }
        query.setLength(query.length() - 2);
        query.append(");");
        // 'insert into users (login, password, nickname) values (?, ?, ?);'
        try {
            psInsert = dataSource.getConnection().prepareStatement(query.toString());
        } catch (SQLException e) {
            throw new ORMException("Не удалось проинициализировать репозиторий для класса " + cls.getName());
        }
    }

    public Optional<User> getUserById(Long id) {
        try (ResultSet rs = dataSource.getStatement().executeQuery("select * from users where id = " + id)) {
            if (rs.next() != false) {
                return Optional.of(new User(rs.getLong("id"), rs.getString("login"), rs.getString("password"), rs.getString("nickname")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    private void prepareSelectId(Class cls) {
        if (!cls.isAnnotationPresent(RepositoryTable.class)) {
            throw new ORMException("Класс не предназначен для работы с репозиторием, не хватает аннотации @RepositoryTable");
        }
        String tableName = ((RepositoryTable) cls.getAnnotation(RepositoryTable.class)).title();
        StringBuilder query = new StringBuilder("select * from ");
        query.append(tableName).append(" where ");
        // 'select * from users where '
        try {
            cachedFields = Arrays.stream(cls.getDeclaredFields())
                    .filter(f -> f.isAnnotationPresent(RepositoryIdField.class))
                    .collect(Collectors.toList());
            if (cachedFields.size() != 1){
                throw new ORMException ("Некорректно размечены поля аннотацией id");
            }
            idField = cachedFields.get(0);
        } catch (Exception e){
            throw new ORMException ("Нет поля с аннотацией id");
        }
        idField.setAccessible(true); // TODO заменить на использование геттеров
        query.append(idField.getName()).append(" = ?");
        // 'select * from users where id = ?'
        try {
            psSelectId = dataSource.getConnection().prepareStatement(query.toString());
        } catch (SQLException e) {
            throw new ORMException("Не удалось выполнить выборку по id для класса " + cls.getName());
        }
    }

}
