package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        try(Connection connection = Util.getConnection()) {
            PreparedStatement preparedStatement = null;
            String command = "CREATE TABLE IF NOT EXISTS Users(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(25), lastName VARCHAR(25), age TINYINT)";
            preparedStatement = connection.prepareStatement(command);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {

        try(Connection connection = Util.getConnection()) {
            PreparedStatement preparedStatement = null;
            String command = "DROP TABLE IF EXISTS Users";
            preparedStatement = connection.prepareStatement(command);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {

        try(Connection connection = Util.getConnection()) {
            PreparedStatement preparedStatement = null;
            String command = "INSERT INTO Users (name, lastName, age) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(command);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        User user = new User();

        try(Connection connection = Util.getConnection()) {

            PreparedStatement preparedStatement = null;
            String command = "DELETE FROM Users WHERE id = ?";
            preparedStatement = connection.prepareStatement(command);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {

        List<User> usersTable = new ArrayList<>();

        try(Connection connection = Util.getConnection()) {

            String command = "SELECT id, name, lastName, age FROM users";
            PreparedStatement preparedStatement = connection.prepareStatement(command);
            ResultSet resultSet = preparedStatement.executeQuery(command);

            while (resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                usersTable.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usersTable;
    }

    public void cleanUsersTable() {

        try(Connection connection = Util.getConnection()) {

            PreparedStatement preparedStatement = null;
            String command = "TRUNCATE Users";
            preparedStatement = connection.prepareStatement(command);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
