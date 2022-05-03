package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

public class MemTest {
    public static void main(String[] args) {
        int amount = 1000000;
        String[] strings = generateInput(4);
        Output out = new ConsoleOutput();
        Input in = new StubInput(strings);
        Store tracker = new MemTracker();
        List<UserAction> actions = new ArrayList<>();
        actions.add(new MultiCreateAction(out, amount));
        actions.add(new ShowAllAction(out));
        actions.add(new MultiDeleteAction(out, amount));
        actions.add(new ExitAction(out));
        new StartUI(out).init(in, tracker, actions);
    }

    private static String[] generateInput(int menu) {
        String[] str = new String[menu];
        for (int i = 0; i < menu; i++) {
            str[i] = String.valueOf(i);
        }
        return str;
    }
}
