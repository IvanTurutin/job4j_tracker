package ru.job4j.tracker;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class SqlTrackerTest {

    private static Connection connection;

    @BeforeClass
    public static void initConnection() {
        try (InputStream in = SqlTrackerTest.class.getClassLoader().getResourceAsStream("test.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @AfterClass
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @After
    public void wipeTable() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("delete from items")) {
            statement.execute();
        }
    }

    @Test
    public void whenSaveItemAndFindByGeneratedIdThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection, "items");
        Item item = tracker.add(new Item("item"));
        assertThat(tracker.findById(item.getId()), is(item));
    }

    @Test
    public void whenReplaceAndFindByName() {
        SqlTracker tracker = new SqlTracker(connection, "items");
        Item item = tracker.add(new Item("item"));
        Item item2 = new Item("item2");
        tracker.replace(item.getId(), item2);
        assertThat(tracker.findByName(item2.getName()).get(0).getName(), is(item2.getName()));
        assertThat(tracker.findByName(item2.getName()).get(0).getDate(), is(item2.getDate()));
        assertThat(tracker.findByName(item2.getName()).get(0).getId(), is(item.getId()));
    }

    @Test
    public void whenFindByName() {
        SqlTracker tracker = new SqlTracker(connection, "items");
        Item item = tracker.add(new Item("item"));
        Item item2 = tracker.add(new Item("item2"));
        Item item3 = tracker.add(new Item("item2"));
        assertThat(tracker.findByName(item2.getName()), is(List.of(item2, item3)));
    }

    @Test
    public void whenDelete() {
        SqlTracker tracker = new SqlTracker(connection, "items");
        Item item = tracker.add(new Item("item"));
        tracker.delete(item.getId());
        assertThat(tracker.findById(item.getId()), is(nullValue()));
    }

    @Test
    public void whenFindAll() {
        SqlTracker tracker = new SqlTracker(connection, "items");
        Item item = tracker.add(new Item("item"));
        Item item2 = tracker.add(new Item("item2"));
        assertThat(tracker.findAll(), is(List.of(item, item2)));
    }
}