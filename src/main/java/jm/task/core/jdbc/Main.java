package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl us = new UserServiceImpl();

        us.createUsersTable();

        us.saveUser("Finn", "Smith", (byte) 32);
        System.out.println("User с именем Finn добавлен в базу данных");
        us.saveUser("Elena", "San", (byte) 21);
        System.out.println("User с именем Elena добавлен в базу данных");
        us.saveUser("Donna", "Tart", (byte) 45);
        System.out.println("User с именем Donna добавлен в базу данных");
        us.saveUser("Alexey", "Smirnov", (byte) 76);
        System.out.println("User с именем Alexey добавлен в базу данных");

        System.out.println(us.getAllUsers());

        us.cleanUsersTable();

        us.dropUsersTable();

    }
}
