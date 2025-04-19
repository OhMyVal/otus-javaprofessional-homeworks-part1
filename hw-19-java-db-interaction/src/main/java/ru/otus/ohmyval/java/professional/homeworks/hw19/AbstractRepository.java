package ru.otus.ohmyval.java.professional.homeworks.hw19;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.reflect.Array.newInstance;

public class AbstractRepository<T> {
    private DataSource dataSource;
    private PreparedStatement psInsert;
    private PreparedStatement psSelectId;
    private PreparedStatement psSelectAll;
    private PreparedStatement psUpdate;
    private List<Field> cachedFieldsNoId;
    private List<Field> allCachedFields;
    private Field idField;
//    private Map<Field, Object> allCachedFieldsValue = new HashMap<>();

    public AbstractRepository(DataSource dataSource, Class<T> cls) {
        this.dataSource = dataSource;
        this.prepareInsert(cls);
        this.prepareSelectId(cls);
        this.prepareSelectAll(cls);
//        this.prepareUpdate(cls);
    }

    public void save(T entity) {
        try {
            for (int i = 0; i < cachedFieldsNoId.size(); i++) {
                psInsert.setObject(i + 1, cachedFieldsNoId.get(i).get(entity));
            }
            psInsert.executeUpdate();
        } catch (Exception e) {
            throw new ORMException("Что-то пошло не так при сохранении: " + entity);
        }
    }

    public Optional<T> findById(Long id, Class<T> cls) {
        try {
            Constructor<T> constructor = cls.getDeclaredConstructor();
            T entity = constructor.newInstance();
            int paramPosition = 1;
            psSelectId.setString(paramPosition, String.valueOf(id));
            ResultSet rs = psSelectId.executeQuery();
            if (rs.next() != false) {
                for (int i = 0; i < allCachedFields.size(); i++) {
                    allCachedFields.get(i).set(entity, rs.getObject(allCachedFields.get(i).getName()));
//                    allCachedFieldsValue.put(allCachedFields.get(i), rs.getObject(allCachedFields.get(i).getName()));
                }
            }
//            for (int i = 0; i < allCachedFields.size(); i++) {
//                allCachedFields.get(i).set(entity,allCachedFieldsValue.get(allCachedFields.get(i)));
//            }
            return Optional.of(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<T> findAll(Class<T> cls) {
        List<T> result = new ArrayList<>();
        try {
            Constructor<T> constructor = cls.getDeclaredConstructor();
            T entity = constructor.newInstance();
            ResultSet rs = psSelectAll.executeQuery();
            while (rs.next() != false) {
            for (int i = 0; i < allCachedFields.size(); i++) {
                    allCachedFields.get(i).set(entity, rs.getObject(allCachedFields.get(i).getName()));
                }
                result.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.unmodifiableList(result);
    }

    private void prepareInsert(Class cls) {
        if (!cls.isAnnotationPresent(RepositoryTable.class)) {
            throw new ORMException("Класс не предназначен для создания репозитория, не хватает аннотации @RepositoryTable");
        }
        String tableName = ((RepositoryTable) cls.getAnnotation(RepositoryTable.class)).title();
        StringBuilder query = new StringBuilder("insert into ");
        query.append(tableName).append(" (");
        // 'insert into users ('
        cachedFieldsNoId = Arrays.stream(cls.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(RepositoryField.class))
                .filter(f -> !f.isAnnotationPresent(RepositoryIdField.class))
                .collect(Collectors.toList());
        for (Field f : cachedFieldsNoId) { // TODO заменить на использование геттеров
            f.setAccessible(true);
        }
        for (Field f : cachedFieldsNoId) {
            query.append(f.getName()).append(", ");
        }
        // 'insert into users (login, password, nickname, '
        query.setLength(query.length() - 2);
        query.append(") values (");
        // 'insert into users (login, password, nickname) values ('
        for (Field f : cachedFieldsNoId) {
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
    private void prepareSelectId(Class cls) {
        if (!cls.isAnnotationPresent(RepositoryTable.class)) {
            throw new ORMException("Класс не предназначен для работы с репозиторием, не хватает аннотации @RepositoryTable");
        }
        String tableName = ((RepositoryTable) cls.getAnnotation(RepositoryTable.class)).title();
        StringBuilder query = new StringBuilder("select * from ");
        query.append(tableName).append(" where ");
        // 'select * from users where '
        try {
            allCachedFields = Arrays.stream(cls.getDeclaredFields())
                    .filter(f -> f.isAnnotationPresent(RepositoryIdField.class) || f.isAnnotationPresent(RepositoryField.class))
                    .collect(Collectors.toList());
            for (Field f : allCachedFields) { // TODO заменить на использование геттеров
                f.setAccessible(true);
            }
            List<Field> cachedIdField = Arrays.stream(cls.getDeclaredFields())
                    .filter(f -> f.isAnnotationPresent(RepositoryIdField.class))
                    .toList();
            if (cachedIdField.size() != 1) {
                throw new ORMException("Некорректно размечены поля аннотацией id");
            }
            idField = cachedIdField.get(0);
        } catch (Exception e) {
            throw new ORMException("Нет поля с аннотацией id");
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

    private void prepareSelectAll(Class cls) {
        if (!cls.isAnnotationPresent(RepositoryTable.class)) {
            throw new ORMException("Класс не предназначен для работы с репозиторием, не хватает аннотации @RepositoryTable");
        }
        String tableName = ((RepositoryTable) cls.getAnnotation(RepositoryTable.class)).title();
        StringBuilder query = new StringBuilder("select * from ");
        query.append(tableName);
        // 'select * from users'
        try {
            psSelectAll = dataSource.getConnection().prepareStatement(query.toString());
        } catch (SQLException e) {
            throw new ORMException("Не удалось выполнить всю выборку для класса " + cls.getName());
        }
    }
    private void prepareUpdate(Class cls) {
        if (!cls.isAnnotationPresent(RepositoryTable.class)) {
            throw new ORMException("Класс не предназначен для создания репозитория, не хватает аннотации @RepositoryTable");
        }
        String tableName = ((RepositoryTable) cls.getAnnotation(RepositoryTable.class)).title();
        StringBuilder query = new StringBuilder("update ");
        query.append(tableName).append(" set (");
        // 'update users set ('
        for (Field f : cachedFieldsNoId) {
            query.append(f.getName()).append(", ");
        }
        // 'update users set (login, password, nickname, '
        query.setLength(query.length() - 2);
        query.append(") values (");
        // 'update users set (login, password, nickname) values ('
        for (Field f : cachedFieldsNoId) {
            query.append("?, ");
        }
        query.setLength(query.length() - 2);
        query.append(") where ");
        // 'update users set (login, password, nickname) values (?, ?, ?) where '
        query.append(idField.getName()).append(" = ?");
        // 'update users set (login, password, nickname) values (?, ?, ?) where id = ?'
        try {
            psUpdate = dataSource.getConnection().prepareStatement(query.toString());
        } catch (SQLException e) {
            throw new ORMException("Не удалось создать запрос на обновление для класса " + cls.getName());
        }
    }

}
