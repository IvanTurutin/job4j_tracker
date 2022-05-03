package ru.job4j.tracker;

public class MultiDeleteAction implements UserAction {
    private final Output out;
    private final int amount;

    public MultiDeleteAction(Output out, int amount) {
        this.out = out;
        this.amount = amount;
    }

    @Override
    public String name() {
        return "Delete item";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        for (int i = 1; i < amount + 1; i++) {
            out.println("=== Delete item ===");
            /*int id = input.askInt("Enter id: ");*/
            if (tracker.delete(i)) {
                out.println("Заявка удалена успешно.");
            } else {
                out.println("Ошибка удаления заявки.");
            }
        }
        return true;
    }
}
