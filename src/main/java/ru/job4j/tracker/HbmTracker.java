package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HbmTracker implements Store, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private static final Logger LOG = LoggerFactory.getLogger(HbmTracker.class.getName());
    private static final String LOG_MESSAGE = "Exception in UserRepository";

    public static final String ITEM_MODEL = "Item";
    public static final String NAME = "fName";
    public static final String DATE = "fDate";
    public static final String ID = "fID";
    public static final String REPLACE_STATEMENT = String.format(
            "UPDATE %s SET name = :%s, date = :%s WHERE id = :%s",
            ITEM_MODEL, NAME, DATE, ID
    );
    public static final String DELETE_STATEMENT = String.format(
            "DELETE %s WHERE id = :%s",
            ITEM_MODEL, ID
    );
    public static final String FIND_ALL_STATEMENT = String.format("from %s", ITEM_MODEL);
    public static final String FIND_ALL_ORDER_BY_ID_STATEMENT = FIND_ALL_STATEMENT + " order by id";
    public static final String KEY = "fKey";
    public static final String FIND_BY_LOGIN_LIKE_STATEMENT = FIND_ALL_STATEMENT
            + String.format(" where name like :%s", KEY);
    public static final String FIND_BY_ID_STATEMENT = FIND_ALL_STATEMENT + String.format(" where id = :%s", ID);

    @Override
    public Item add(Item item) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
        } catch (Exception e) {
            LOG.error(LOG_MESSAGE, e);
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        boolean replace = false;
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery(REPLACE_STATEMENT)
                    .setParameter(NAME, item.getName())
                    .setParameter(ID, id)
                    .executeUpdate();
            session.getTransaction().commit();
            replace = true;
        } catch (Exception e) {
            LOG.error(LOG_MESSAGE, e);
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return replace;
    }

    @Override
    public boolean delete(int id) {
        boolean delete = false;
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery(DELETE_STATEMENT)
                    .setParameter(ID, id)
                    .executeUpdate();
            session.getTransaction().commit();
            delete = true;
        } catch (Exception e) {
            LOG.error(LOG_MESSAGE, e);
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return delete;
    }

    @Override
    public List<Item> findAll() {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            Query<Item> query = session.createQuery(FIND_ALL_ORDER_BY_ID_STATEMENT, Item.class);
            session.getTransaction().commit();
            return query.list();
        } catch (Exception e) {
            LOG.error(LOG_MESSAGE, e);
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return new ArrayList<>();
    }

    @Override
    public List<Item> findByName(String key) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            Query<Item> query = session.createQuery(FIND_BY_LOGIN_LIKE_STATEMENT, Item.class);
            query.setParameter(KEY, "%" + key + "%");
            session.getTransaction().commit();
            return query.list();
        } catch (Exception e) {
            LOG.error("Exception in UserRepository", e);
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return new ArrayList<>();
    }

    @Override
    public Item findById(int id) {
        Item item = null;
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            Query<Item> query = session.createQuery(FIND_BY_ID_STATEMENT, Item.class);
            query.setParameter(ID, id);
            session.getTransaction().commit();
            item = query.uniqueResult();
        } catch (Exception e) {
            LOG.error("Exception in UserRepository", e);
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return item;
    }

    @Override
    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}