package ru.job4j.tracker;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.toone.Role;
import ru.job4j.toone.User;
import ru.job4j.toone.UserMessenger;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HbmTrackerTest {

    private static HbmTracker store;
    private static List<User> users;
    private static User user;
    private static User user2;

    @BeforeAll
    public static void initStore() {
        store = new HbmTracker();

        UserMessenger userMessenger = new UserMessenger();
        userMessenger.setMessenger("Telegramm");
        userMessenger.setIdentify("Tg");
        UserMessenger userMessenger2 = new UserMessenger();
        userMessenger2.setMessenger("Vkontakte");
        userMessenger2.setIdentify("Vk");
        List<UserMessenger> messengers = List.of(userMessenger2, userMessenger);
        List<UserMessenger> messengers2 = List.of(userMessenger);

        Role role = new Role();
        role.setName("Admin");
        Role role2 = new Role();
        role2.setName("Noob");

        user = new User();
        user.setName("User1");
        user.setRole(role);
        user.setMessengers(messengers);
        user2 = new User();
        user2.setName("User2");
        user2.setRole(role2);
        user2.setMessengers(messengers2);
        users = List.of(user, user2);
    }

    @AfterAll
    public static void closeConnection() {
        store.close();
    }

    @Test
    public void whenAdd() {
        Item item = new Item();
        item.setName("Item1");
        item.setParticipates(List.of(user, user2));
        store.add(item);
        Item itemDb = store.findById(item.getId());
        System.out.println(itemDb);
        assertThat(itemDb).isNotNull();
        assertThat(item.getId()).isNotEqualTo(0);
        assertThat(itemDb.getName()).isEqualTo("Item1");
        assertThat(itemDb.getParticipates()).isNotEmpty().hasSize(2).contains(user, user2);
    }

    @Test
    public void whenReplace() {
        Item item = new Item();
        item.setName("Item1");
        item.setParticipates(List.of(user, user2));
        store.add(item);
        item.setName("Item1 updated");
        item.setParticipates(List.of(user));
        store.replace(item.getId(), item);
        Item itemDb = store.findById(item.getId());
        assertThat(itemDb.getName()).isEqualTo("Item1 updated");
        assertThat(itemDb.getParticipates()).isNotEmpty().hasSize(1).contains(user);
    }

    @Test
    public void whenDelete() {
        Item item = new Item();
        item.setName("Item1");
        item.setParticipates(List.of(user, user2));
        store.add(item);
        boolean rslt = store.delete(item.getId());
        assertThat(rslt).isTrue();
        assertThat(store.findById(item.getId())).isNull();
    }

    @Test
    public void whenFindAll() {
        store.truncateTable();
        Item item = new Item();
        item.setName("Item1");
        item.setParticipates(List.of(user, user2));
        store.add(item);
        Item item2 = new Item();
        item2.setName("Item2");
        item2.setParticipates(List.of(user));
        store.add(item2);
        List<Item> items = store.findAll();
        items.forEach(System.out::println);
        assertThat(items).isNotEmpty().hasSize(2).contains(item, item2);
    }

    @Test
    public void testFindByName() {
        store.truncateTable();
        Item item = new Item();
        item.setName("Item1");
        item.setParticipates(List.of(user, user2));
        store.add(item);
        Item item2 = new Item();
        item2.setName("Ntem2");
        item2.setParticipates(List.of(user));
        store.add(item2);
        List<Item> items1 = store.findByName("tem");
        List<Item> items2 = store.findByName("Item1");
        List<Item> items3 = store.findByName("Item3");
        assertThat(items1).isNotEmpty().hasSize(2).contains(item, item2);
        assertThat(items2).isNotEmpty().hasSize(1).contains(item);
        assertThat(items3).isEmpty();
    }
}