package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StartUITest {
    @Test
    public void whenAddItem() {
        String[] answers = {"Fix PC", "Test1"};
        Input input = new StubInput(answers);
        Tracker tracker = new Tracker();
        StartUI.createItem(input, tracker);
        Item created = tracker.findAll()[0];
        Item expected = new Item("Fix PC");
        assertThat(created.getName(), is(expected.getName()));
    }

    @Test
    public void whenAddItem2() {
        String[] answers = {"Fix PC", "Test1"};
        Input input = new StubInput(answers);
        Tracker tracker = new Tracker();
        StartUI.createItem(input, tracker);
        StartUI.createItem(input, tracker);
        Item created = tracker.findAll()[1];
        Item expected = new Item("Test1");
        assertThat(created.getName(), is(expected.getName()));
    }

    @Test
    public void whenEditItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("new item");
        tracker.add(item);
        String[] answers = {String.valueOf(item.getId()), "edited item"};
        StartUI.editItem(new StubInput(answers), tracker);
        Item edited = tracker.findById(item.getId());
        assertThat(edited.getName(), is("edited item"));
    }

    @Test
    public void whenDeleteItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("new item");
        tracker.add(item);
        String[] answers = {"1"};
        StartUI.deleteItem(new StubInput(answers), tracker);
        Item deleted = tracker.findById(Integer.parseInt(answers[0]));
        assertNull(deleted);
    }

    @Test
    public void whenDeleteItem2() {
        Tracker tracker = new Tracker();
        Item item = new Item("new item");
        tracker.add(item);
        tracker.add(item);
        String[] answers = {"1"};
        StartUI.deleteItem(new StubInput(answers), tracker);
        Item deleted = tracker.findById(2);
        assertNotNull(deleted);
    }
}