package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory factory = Util.getSessionFactory();

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("""
                    CREATE TABLE if not exists User(
                            id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(120),
                            lastName VARCHAR(120),
                            age TINYINT
                    );
                    """).executeUpdate();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS User").executeUpdate();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(new User(name, lastName, age));
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }

    }

    @Override
    public void removeUserById(long id) {
            Transaction transaction = null;
            try (Session session = factory.openSession()) {
                transaction = session.beginTransaction();
                session.delete(session.get(User.class,id));
                session.getTransaction().commit();
            } catch (RuntimeException e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                throw e;
            }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            List<User> users = session.createQuery("from User", User.class).list();
            session.getTransaction().commit();
            return users;
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("DELETE FROM User").executeUpdate();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }
}
