package ru.job4j.inheritance;

public class JSONReport extends TextReport {

    public String generate(String name, String body) {
        String s = System.lineSeparator();
        return "{" + s
                + "\t\"name\" : \"" + name + "\"," + s
                + "\t\"body\" : \"" + body + "\"" + s
                + "}";
    }
}
