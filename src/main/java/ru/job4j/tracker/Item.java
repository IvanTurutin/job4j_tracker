package ru.job4j.tracker;

import lombok.Data;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Data
public class Item {    private int id;
    private String name;
    private LocalDateTime create = LocalDateTime.now().withNano(3);
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy HH:mm:ss");

    public Item() {
    }

    public Item(int id, String name, LocalDateTime create) {
        this.id = id;
        this.name = name;
        this.create = create.withNano(3);
    }

    public Item(String name) {
        this();
        this.name = name;
    }

    public Item(int id, String name) {
        this(name);
        this.id = id;
    }

}
