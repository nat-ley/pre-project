package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    private static UserService userService = new UserServiceImpl();
    public static void main(String[] args) {
        userService.createUsersTable();
        System.out.println("Таблица создана!");
        userService.saveUser("Ivan","Ivanov", (byte) 16);
        System.out.println("User с именем – Ivan добавлен в базу данных");
        userService.saveUser("Inna","Sidorova", (byte) 24);
        System.out.println("User с именем – Inna добавлен в базу данных");
        userService.saveUser("Yan","Petrov", (byte) 36);
        System.out.println("User с именем – Yan добавлен в базу данных");
        userService.saveUser("Tany","King", (byte) 40);
        System.out.println("User с именем – Tany добавлен в базу данных");

        userService.getAllUsers().forEach(System.out::println);

        userService.cleanUsersTable();

        userService.dropUsersTable();

    }
}
