package ru.job4j.tracker;

public class MultiCreateAction implements UserAction {
    private final Output out;
    private final int amount;

    public MultiCreateAction(Output out, int amount) {
        this.out = out;
        this.amount = amount;
    }

    @Override
    public String name() {
        return "Add new Item";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        for (int i = 0; i < amount; i++) {
            out.println("=== Create a new Item ===");
            /*String name = input.askStr("Enter name: ");*/
            String name = String.format("Name%d", i);
            Item item = new Item(name);
            tracker.add(item);
            out.println("Добавленная заявка: " + item);
        }
        return true;
    }
}
