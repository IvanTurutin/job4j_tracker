package ru.job4j.oop;

public class Wolf {
    public void tryEat(Ball ball) {
        System.out.println("Колобок встретил волка.");
        ball.tryRun(false);
    }

}
