package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ItemAscByNameTest {

    @Test
    public void whenAscSort() {
        List<Item> items = new ArrayList<>();
        Item item1 = new Item(1, "Book");
        Item item2 = new Item(2, "Pen");
        Item item3 = new Item(3, "Notebook");
        items.add(item1);
        items.add(item2);
        items.add(item3);
        Collections.sort(items, new ItemAscByName());
        List<Item> expected = new ArrayList<>();
        expected.add(item1);
        expected.add(item3);
        expected.add(item2);
        assertThat(expected, is(items));
    }
}