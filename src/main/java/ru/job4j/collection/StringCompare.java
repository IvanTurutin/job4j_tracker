package ru.job4j.collection;

import java.util.Arrays;
import java.util.Comparator;

public class StringCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int leftLength = left.length();
        int rightLength = right.length();
        int length = Math.min(leftLength, rightLength);
        if (leftLength == rightLength) {
            for (int i = 0; i < length; i++) {
                if (left.charAt(i) != right.charAt(i)) {
                    return Character.compare(left.charAt(i), right.charAt(i));
                }
            }
        } else {
            for (int i = 0; i < length; i++) {
                if (left.charAt(i) != right.charAt(i)) {
                    return Character.compare(left.charAt(i), right.charAt(i));
                }
            }
            return leftLength > rightLength ? 1 : -1;
        }
        return 0;
    }
}