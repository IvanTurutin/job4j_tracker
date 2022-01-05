package ru.job4j.tracker;

public class ConsoleOutput implements Output {
    @Override
    public void println(Object obj) {
        System.out.println(obj);
    }

    @Override
    public void print(Object obj) {
        System.out.print(obj);
    }
}
