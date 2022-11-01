package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Boris", "Lesnoy", (byte) 54);
        System.out.println("User с именем Boris добавлен в базу данных");
        userService.saveUser("Martha", "Jones", (byte) 24);
        System.out.println("User с именем Martha добавлен в базу данных");
        userService.saveUser("John", "Smith", (byte) 30);
        System.out.println("User с именем John добавлен в базу данных");
        userService.saveUser("Elena", "San", (byte) 54);
        System.out.println("User с именем Elena добавлен в базу данных");

        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();

        userService.dropUsersTable();

    }
}
