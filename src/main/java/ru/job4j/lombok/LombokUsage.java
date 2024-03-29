package ru.job4j.lombok;

public class LombokUsage {
    public static void main(String[] args) {
        var bird = new BirdData();
        bird.setAge(1);
        System.out.println(bird);
        Category category = new Category(1);
        System.out.println(category.getId());
        Permission permission = Permission.of()
                .id(1)
                .name("Max")
                .accessBy("Read")
                .accessBy("Write")
                .build();
        System.out.println(permission);
    }
}
