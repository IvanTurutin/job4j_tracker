package ru.job4j.lambda;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Functions {

    public static List<Double> diapason(int start, int end, Function<Double, Double> func) {
        List<Double> diap = new ArrayList<>();
        for (int i = start; i < end; i++) {
            Double num = Double.valueOf(i);
            diap.add(func.apply(num));
        }
        return diap;
    }

}
