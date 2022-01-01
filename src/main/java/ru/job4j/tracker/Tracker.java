package ru.job4j.tracker;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Tracker {
    private final Item[] items = new Item[100];
    private int ids = 1;
    private int size = 0;

    public Item add(Item item) {
        item.setId(ids++);
        items[size++] = item;
        return item;
    }

    public Item findById(int id) {
        Item rsl = null;
        for (int index = 0; index < size; index++) {
            Item item = items[index];
            if (item.getId() == id) {
                rsl = item;
                break;
            }
        }
        return rsl;
    }

    public Item[] findAll() {
        int count = 0;
        Item[] itemsWithoutNull = new Item[size];
        for (int i = 0; i < size; i++) {
            if (items[i] != null) {
                itemsWithoutNull[count++] = items[i];
            }
        }
        itemsWithoutNull = Arrays.copyOf(itemsWithoutNull, count);
        return itemsWithoutNull;
    }

    public Item[] findByName(String key) {
        Item[] itemsWithoutNull = new Item[size];
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (items[i].getName().equals(key)) {
                itemsWithoutNull[count++] = items[i];
            }
        }
        itemsWithoutNull = Arrays.copyOf(itemsWithoutNull, count);
        return itemsWithoutNull;
    }
}