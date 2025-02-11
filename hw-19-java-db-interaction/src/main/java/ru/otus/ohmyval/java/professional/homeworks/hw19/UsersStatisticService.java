package ru.otus.ohmyval.java.professional.homeworks.hw19;

public class UsersStatisticService {
    private UsersDao usersDao;

    public UsersStatisticService(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    public void businessLogicMethod(Long id) {
        User user = usersDao.getUserById(id).get();
        // ... какая-то обработка данных по юзеру
        System.out.println(user);
    }
}
