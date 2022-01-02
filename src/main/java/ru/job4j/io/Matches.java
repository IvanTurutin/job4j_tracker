package ru.job4j.io;

import java.util.Scanner;

public class Matches {
    public static void main(String[] args) {
        System.out.println("Игра 11.");
        int count = 11;
        boolean turn = true;
        turn = game(count, turn);
        if (!turn) {
            System.out.println("Выиграл первый игрок");
        } else {
            System.out.println("Выиграл второй игрок");
        }
    }

    public static boolean game(int count, boolean turn) {
        Scanner input = new Scanner(System.in);
        while (count > 0) {
            String player = turn ? "Первый игрок" : "Второй игрок";
            System.out.println(player + " введите число от 1 до 3:");
            int matches = Integer.parseInt(input.nextLine());
            if (matches <= 3 && matches > 0 && matches <= count) {
                turn = !turn;
                count -= matches;
                System.out.println("Осталось " + count + " спичек.");
            } else {
                System.out.println("Неверное число");
            }
        }
        return turn;
    }
}
