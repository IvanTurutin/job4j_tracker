package ru.job4j.lambda;

import java.util.function.Function;
import java.util.function.Supplier;

public class ScopeInside {
    public static void main(String[] args) {
        int[] number = {1, 2, 3};
        int total = 0;
        for (int i : number) {
            int sum = total;
            total = add(() -> sum + i);
        }
        System.out.println(total);
    }

    private static Integer add(Supplier<Integer> calc) {
        return calc.get();
    }
}