package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String command = "CREATE TABLE IF NOT EXISTS Users(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(25), lastName VARCHAR(25), age TINYINT)";
        try(Session session = Util.getSessionFactory().getCurrentSession()) {

            session.beginTransaction();
            session.createSQLQuery(command).addEntity(User.class).executeUpdate();
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        String command = "DROP TABLE IF EXISTS Users";
        try(Session session = Util.getSessionFactory().getCurrentSession()) {

            session.beginTransaction();
            session.createSQLQuery(command).addEntity(User.class).executeUpdate();
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try(Session session = Util.getSessionFactory().getCurrentSession()) {

            session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try(Session session = Util.getSessionFactory().getCurrentSession()) {

            session.beginTransaction();
            session.createQuery("delete User where id = " + id).executeUpdate();
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();

        try(Session session = Util.getSessionFactory().getCurrentSession()) {

            session.beginTransaction();
            allUsers = session.createQuery("from User").getResultList();
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return allUsers;
    }

    @Override
    public void cleanUsersTable() {
        String command = "TRUNCATE Users";
        try(Session session = Util.getSessionFactory().getCurrentSession()) {

            session.beginTransaction();
            session.createSQLQuery(command).addEntity(User.class).executeUpdate();
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
