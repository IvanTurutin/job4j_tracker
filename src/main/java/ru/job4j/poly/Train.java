package ru.job4j.poly;

public class Train implements Vehicle {
    @Override
    public void move() {
        System.out.println("по рельсам.");
    }

    @Override
    public void engine() {
        System.out.println("чух-чух-чух.");
    }
}
