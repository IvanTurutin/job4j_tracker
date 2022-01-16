package ru.job4j.collection;

import java.util.Comparator;

public class LexSort implements Comparator<String> {

    @Override
    public int compare(String left, String right) {
        String[] leftArr = left.split("\\.");
        String[] rightArr = right.split("\\.");
        int leftNum = Integer.parseInt(leftArr[0]);
        int rightNum = Integer.parseInt(rightArr[0]);
        return Integer.compare(leftNum, rightNum);
    }
}