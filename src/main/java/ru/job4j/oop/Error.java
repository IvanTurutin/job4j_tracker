package ru.job4j.oop;

public class Error {
    private boolean active;
    private int status;
    private String message;

    public Error(boolean active, int status, String message) {
        this.active = active;
        this.status = status;
        this.message = message;
    }

    public Error() {
    }

    public void printInfo() {
        System.out.println(active);
        System.out.println(status);
        System.out.println(message);
    }

    public static void main(String[] args) {
        Error errorDefault = new Error();
        Error error1 = new Error(true, 3, "Статус 3");
        Error error2 = new Error(false, 2, "Статус 2");
        errorDefault.printInfo();
        error1.printInfo();
        error2.printInfo();

    }

}
