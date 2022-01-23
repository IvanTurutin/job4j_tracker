package ru.job4j.lambda;

import java.util.function.Function;
import java.util.function.Supplier;

public class ScopeInside {
    public static void main(String[] args) {
        int[] number = {1, 2, 3};
        int total = 0;
        for (int i : number) {
            total = add(total, (sum) -> i + sum);
        }
        System.out.println(total);
    }

    private static Integer add(Supplier<Integer> calc) {
        return calc.get();
    }

    private static Integer add(Integer total, Function<Integer, Integer> calc) {
        return calc.apply(total);
    }

}