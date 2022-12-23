package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

public class StartUI {
    private final Output out;

    public StartUI(Output out) {
        this.out = out;
    }

    public void init(Input input, Store tracker, List<UserAction> actions) {
        boolean run = true;
        while (run) {
            this.showMenu(actions);
            int select = input.askInt("Select: ");
            if (select < 0 || select >= actions.size()) {
                out.println("Wrong input, you can select: 0 .. " + (actions.size() - 1));
                continue;
            }
            UserAction action = actions.get(select);
            run = action.execute(input, tracker);
        }
    }

    private void showMenu(List<UserAction> actions) {
        out.println("Menu.");
        for (int index = 0; index < actions.size(); index++) {
            out.println(index + ". " + actions.get(index).name());
        }
    }

    /*
    public static void main(String[] args) {
        Output out = new ConsoleOutput();
        Input input = new ValidateInput(out, new ConsoleInput());
        Store tracker = new MemTracker();
        List<UserAction> actions = new ArrayList<>();
        actions.add(new CreateAction(out));
        actions.add(new ShowAllAction(out));
        actions.add(new EditAction(out));
        actions.add(new DeleteAction(out));
        actions.add(new FindByIdAction(out));
        actions.add(new FindByNameAction(out));
        actions.add(new ExitAction(out));
        new StartUI(out).init(input, tracker, actions);
    }
    */

/*
    public static void main(String[] args) {
        Output out = new ConsoleOutput();
        Input input = new ValidateInput(out, new ConsoleInput());
        try (SqlTracker tracker = new SqlTracker()) {
            tracker.init();
            List<UserAction> actions = List.of(
                    new CreateAction(out),
                    new ShowAllAction(out),
                    new EditAction(out),
                    new DeleteAction(out),
                    new FindByIdAction(out),
                    new FindByNameAction(out),
                    new ExitAction(out)
            );
            new StartUI(out).init(input, tracker, actions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
*/

    public static void main(String[] args) {
        Output out = new ConsoleOutput();
        Input input = new ValidateInput(out, new ConsoleInput());
        try (HbmTracker tracker = new HbmTracker()) {
            List<UserAction> actions = List.of(
                    new CreateAction(out),
                    new ShowAllAction(out),
                    new EditAction(out),
                    new DeleteAction(out),
                    new FindByIdAction(out),
                    new FindByNameAction(out),
                    new ExitAction(out)
            );
            new StartUI(out).init(input, tracker, actions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
