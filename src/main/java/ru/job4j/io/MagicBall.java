package ru.job4j.io;

import java.util.Scanner;
import java.util.Random;

public class MagicBall {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Я великий Оракул. Что ты хочешь узнать? ");
        System.out.println("Задай вопрос, ответ на который может быть да или нет.");
        String quest = input.nextLine();
        int rnd = new Random().nextInt(3);
        System.out.println(rnd);
        if (rnd == 0) {
            System.out.println("Да");
        } else if (rnd == 1) {
            System.out.println("нет");
        } else {
            System.out.println("Может быть");
        }
    }
}
