package ru.job4j.tracker;

import lombok.Data;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Data
public class Item {    private int id;
    private String name;
    private LocalDateTime create;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy HH:mm:ss");

    public Item() {
        this.create = LocalDateTime.now().withNano(3);
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

/*

public class Item {
    private int id;
    private String name;
    private LocalDateTime create;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy HH:mm:ss");

    public Item() {
        this.create = LocalDateTime.now().withNano(3);
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreate() {
        return create;
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id
                + ", name='" + name + '\''
                + ", create=" + create.format(FORMATTER) + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return id == item.id && Objects.equals(name, item.name) && Objects.equals(create, item.create);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, create);
    }
}*/
