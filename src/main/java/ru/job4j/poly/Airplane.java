package ru.job4j.poly;

public class Airplane implements Vehicle {
    @Override
    public void move() {
        System.out.println("по воздуху.");
    }

    @Override
    public void engine() {
        System.out.println("ш-ш-ш-ш-ш.");
    }
}
