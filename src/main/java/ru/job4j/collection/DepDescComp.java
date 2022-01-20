package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        String[] o1Arr = o1.split("/");
        String[] o2Arr = o2.split("/");
        if (!o1Arr[0].equals(o2Arr[0])) {
            return o2Arr[0].compareTo(o1Arr[0]);
        }
        return o1.compareTo(o2);
    }
}
