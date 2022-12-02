package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Alex", "Rudchenko", (byte) 23);
        userService.saveUser("Mihail", "Voronov", (byte) 25);
        userService.saveUser("Ekaterina", "Samoylova", (byte) 19);
        userService.saveUser("Denis", "Dubov", (byte) 25);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();

        //userService.removeUserById(1);
    }
}
