package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Трекер работает с таблицей с параметрами:
 * create table item (
 *     id serial primary key,
 *     name varchar(255),
 * 	   date timestamp
 * );
 */

public class SqlTracker implements Store, AutoCloseable {

    private Connection cn;
    private String table;

    public void init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
            table = config.getProperty("table");
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    @Override
    public Item add(Item item) {
        Timestamp timestampFromLDT = Timestamp.valueOf(item.getCreate());
        String query = String.format("insert into %s (name, date) values (?, ?)", table);
        try (PreparedStatement ps = cn.prepareStatement(
                query,
                Statement.RETURN_GENERATED_KEYS
        )) {
            ps.setString(1, item.getName());
            ps.setTimestamp(2, timestampFromLDT);
            ps.execute();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    item.setId(generatedKeys.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        boolean rsl = false;
        Timestamp timestampFromLDT = Timestamp.valueOf(item.getCreate());
        String query = String.format("update %s set name = ?, date = ? where id = ?", table);
        if (findById(id) != null) {
            try (PreparedStatement ps = cn.prepareStatement(query)) {
                ps.setString(1, item.getName());
                ps.setTimestamp(2, timestampFromLDT);
                ps.setInt(3, id);
                ps.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rsl = true;
        }
        return rsl;
    }

    @Override
    public boolean delete(int id) {
        boolean rsl = false;
        String query = String.format("delete from %s where id = ?", table);
        if (findById(id) != null) {
            try (PreparedStatement ps = cn.prepareStatement(query)) {
                ps.setInt(1, id);
                ps.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rsl = true;
        }
        return rsl;
    }

    @Override
    public List<Item> findAll() {
        String query = String.format("select * from %s", table);
        return selectQuery(query);
    }

    @Override
    public List<Item> findByName(String key) {
        String query = String.format("select * from %s where name = '%s'", table, key);
        return selectQuery(query);
    }

    @Override
    public Item findById(int id) {
        Item item = null;
        String query = String.format("select * from %s where id = %d", table, id);
        List<Item> items = selectQuery(query);
        if (!items.isEmpty()) {
            item = items.get(0);
        }
        return item;
    }

    private List<Item> selectQuery(String query) {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement ps = cn.prepareStatement(query)) {
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    items.add(new Item(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getTimestamp("date").toLocalDateTime()
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }
}