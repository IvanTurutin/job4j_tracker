package ru.job4j.tracker;

import lombok.Data;


import javax.persistence.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "items")
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private LocalDateTime date = LocalDateTime.now().withNano(3);
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy HH:mm:ss");

    public Item() {
    }

    public Item(int id, String name, LocalDateTime create) {
        this.id = id;
        this.name = name;
        this.date = create.withNano(3);
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
