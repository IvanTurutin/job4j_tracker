package ru.job4j.oop;

public class Hare {
    public void tryEat(Ball ball) {
        System.out.println("Колобок встретил зайца.");
        ball.tryRun(false);
    }
}
