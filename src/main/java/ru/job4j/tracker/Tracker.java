package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

public class Tracker {
    private final List<Item> items = new ArrayList<>();
    private int ids = 1;

    public Item add(Item item) {
        item.setId(ids++);
        items.add(item);
        return item;
    }

    public Item findById(int id) {
        for (Item item : items) {
            if (item != null && item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public List<Item> findAll() {
        return items;
    }

    public List<Item> findByName(String key) {
        List<Item> rsl = new  ArrayList<>();
        for (Item item : items) {
            if (item != null && item.getName().equals(key)) {
                rsl.add(item);
            }
        }
        return rsl;
    }

    public boolean replace(int id, Item item) {
        Item i = findById(id);
        if (i != null) {
            item.setId(id);
            items.set(items.indexOf(i), item);
            return true;
        }
        return false;
    }

    public boolean delete(int id) {
        Item i = findById(id);
        if (i != null) {
            items.remove(i);
            return true;
        }
        return false;
    }
}